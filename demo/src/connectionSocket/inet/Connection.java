/*
 *  Copyright (C) 2007
 *
 *  This program is free software; you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License as published by the Free
 *  Software Foundation; either version 2 of the License, or (at your option)
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 *  or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 *  for more details.
 */

package connectionSocket.inet;

import java.io.*;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Generic bidirectional connection between client and server
 */
public class Connection {

    /**
     * Peer Host
     * Non null in case if it's a client connection
     */
    private String host;

    /**
     * Peer port
     * != 0 in case if it's a client connection
     */
    private int port;

    /**
     * Connection state
     */
    private boolean open;

    /**
     * The socket for this connection.
     */
    private Socket socket;

    /**
     * The connection ID
     */
    private int id;

    /**
     * Receiver thread
     */
    private Thread receiver;

    /**
     * Sender thread
     */
    private Thread sender;

    /**
     * Queue of <code>Packets</code> to send
     */
    private SendQueue sendQueue = new SendQueue();

    /**
     * Connection listeners list
     */
    private Vector<ConnectionListener> connectionListeners = new Vector<ConnectionListener>();

    /**
     * Creates new client (connection from client to server) connection
     * @param host target host
     * @param port target port
     * @param id connection ID
     */

    public Connection(String host, int port, int id) {
        this.host = host;
        this.port = port;
        this.id = id;
    }

    /**
     * Creates new Server connection
     * @param socket accepted socket
     * @param id connection ID
     */
    public Connection(Socket socket, int id) {
        this.socket = socket;
        this.id = id;
    }

    /**
     * Returns <code>true</code> if it's the Server connection
     * @return <code>true</code> if it's the Server connection
     */
    public boolean isServer() {
        return host == null;
    }

    /**
     * Opens the connection
     * @return <code>true</code> on success, <code>false</code> otherwise
     */
    public synchronized boolean open() {
        if (!open) {
            if (socket == null) {
                try {
                    socket = new Socket(host, port);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
            open = true;
            initThreads();
        }
        return true;
    }

    public boolean isOpen(){
    	return open;
    }

    /**
     * Closes the socket and shuts down the receiver and sender threads
     */
    public void close() {
        synchronized (this) {
            sendQueue.finish();
            receiver = null;
            sender = null;
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                System.err.print( "Error closing connection #" ); //$NON-NLS-1$
                System.err.print( getId() );
                System.err.print( ": " ); //$NON-NLS-1$
                System.err.println( e.getMessage() );
                // We don't need a full stack trace... we're
                // just closing the connection anyway.
                //e.printStackTrace();
            } catch (NullPointerException ex) {
                //never initialized, poor thing
            }
            socket = null;
            open = false;
        }
        processConnectionEvent(new DisconnectedEvent(this));
    }

    /**
     * Returns the connection ID
     * @return the connection ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the connection ID
     * @param id new connection ID
     * Be careful with this...
     */
    public void setId(int id) {
        this.id = id;
    }

    public String getInetAddress() {
        if (socket != null) {
            return socket.getInetAddress().toString();
        }
		return "Unknown";
    }

    public String getLocalIpAddress() {
    	if(socket != null) {
    		return socket.getLocalSocketAddress().toString();
    	}

    	return "Unknown";
    }

    /**
     * Adds a packet to the send queue to be send on a seperate thread.
     */
    public void send(Packet packet) {
    	sendQueue.addPacket(packet);
    }

    /**
     * Send packet now; This is the blocking call.
     */
    public synchronized void sendNow(Packet packet) {
    	synchronized (this) {
	        try {
	        	sendNetworkPacket(packet);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
    	}
    }

    /**
     * Returns <code>true</code> if there are pending packets
     * @return <code>true</code> if there are pending packets
     */
    public boolean hasPending() {
        return sendQueue.hasPending();
    }

    /**
     * Adds the specified connection listener to receive
     * connection events from connection.
     *
     * @param listener the connection listener.
     */
    public void addConnectionListener(ConnectionListener listener) {
        connectionListeners.addElement(listener);
    }

    /**
     * Removes the specified connection listener.
     *
     * @param listener the connection listener.
     */
    public void removeConnectionListener(ConnectionListener listener) {
        connectionListeners.removeElement(listener);
    }

    /**
     * Returns the the connection type abbrevation (client/server) that used
     * in the debug messages and so on.
     * @return
     */
    protected String getConnectionTypeAbbrevation() {
        return isServer()?"Server:":"Client:"; //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Returns an input stream
     * @return an input stream
     * @throws IOException
     */
    protected InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }

    /**
     * Returns an output stream
     * @return an output stream
     * @throws IOException
     */
    protected OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    /**
     * Initializes the sender and receiver threads
     */
    private void initThreads() {

        Runnable receiverRunnable = new Runnable() {
            public void run() {
                while (receiver == Thread.currentThread()) {
                	Packet packet = null;

                    try {
						packet = readNetworkPacket();
						if (packet != null) {
	                    	processPacket(packet);
	                    }
					} catch (IOException e) {
						e.printStackTrace();
						close();
					} catch (Exception e) {
						e.printStackTrace();
					}
                }
            }

            protected void processPacket(Packet packet) throws Exception {
                if (packet != null) {
                    processConnectionEvent(new PacketReceivedEvent(Connection.this, packet));
                }

            }
        };
        receiver = new Thread(receiverRunnable, "Packet Receiver (" + getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$

        Runnable senderRunable = new Runnable() {
            public void run() {
                while (sender == Thread.currentThread()) {
                    Packet packet = sendQueue.getPacket();
                    if (packet != null) {
                        try {
                            processPacket(packet);
                        } catch (IOException e) {
        						e.printStackTrace();
                        }catch (Exception e) {
                            close();
                        }
                    }
                }
            }

            protected void processPacket(Packet packet) throws Exception {
                sendNow(packet);
            }
        };
        sender = new Thread(senderRunable, "Packet Sender (" + getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$

        sender.start();
        receiver.start();
    }

    private Packet readNetworkPacket() throws IOException {
    	DataInputStream in = new DataInputStream(getInputStream());
    	Packet packet = null;
        byte[] data = new byte[1024];
        int index = 0;

    	while(true) {
    		data[index++] = in.readByte();
    		if(index >= 2 && (data[index - 2] == 0x0D) && (data[index - 1] == 0x0A)) {
    			packet = new Packet(data, index);
    			return packet;
    		}
    		if(index >= 1024) {
    			index = 0;
    		}
    	}
    }

    private void sendNetworkPacket(Packet packet) throws IOException {
    	DataOutputStream out = new DataOutputStream(getOutputStream());

        out.write(packet.getData().getBytes());
    }

    private static class SendQueue {

        private Vector<Packet> queue = new Vector<Packet>();
        private boolean finished = false;

        public synchronized void addPacket(Packet packet) {
            queue.addElement(packet);
            notifyAll();
        }

        public synchronized void finish() {
            queue.removeAllElements();
            finished = true;
            notifyAll();
        }

        /**
         * Waits for a packet to appear in the queue and then returns it.
         * @return the first available packet in the queue
         */
        public synchronized Packet getPacket() {
            Packet packet = null;
            while (!hasPending() && !finished) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                }
            }
            if (!finished) {
                packet = queue.elementAt(0);
                queue.removeElementAt(0);
            }
            return packet;
        }
        /**
         * Returns true if this connection has pending data
         */
        public synchronized boolean hasPending() {
            return queue.size() > 0;
        }
    }

    /**
     * Processes game events occurring on this
     * connection by dispatching them to any registered
     * GameListener objects.
     *
     * @param event the game event.
     */
    protected void processConnectionEvent(ConnectionEvent event) {
        for (Enumeration<ConnectionListener> e = connectionListeners.elements(); e.hasMoreElements();) {
            ConnectionListener l = (ConnectionListener) e.nextElement();
            switch (event.getType()) {
            case ConnectionEvent.CONNECTED:
                l.connected((ConnectedEvent)event);
                break;
            case ConnectionEvent.DISCONNECTED:
                l.disconnected((DisconnectedEvent)event);
                break;
            case ConnectionEvent.PACKET_RECEIVED:
                l.packetReceived((PacketReceivedEvent)event);
                break;
            }
        }
    }
}
