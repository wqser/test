package connectionSocket.inet;



import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;


public class Server extends Thread {


	private ServerSocket serverSocket;
	private int connectionCounter = 0;
	private Vector<Connection> connections = new Vector<Connection>();

	private ConnectionListenerAdapter connectionListener = new ConnectionListenerAdapter() {
        public void disconnected(DisconnectedEvent e) {
            Connection conn = e.getConnection();

            // write something in the log
            System.out.println("Server: connection " + conn.getId() + " disconnected");

            connections.removeElement(conn);

        }

        public void packetReceived(final PacketReceivedEvent e) {
        	Server.this.handle(e.getConnection().getId(), e.getPacket());
        }
    };

    protected synchronized void handle(int connId, Packet packet) {
        if (packet == null) {
            System.out.println("server.connection.handle: got null packet");
            return;
        }
        System.out.print(packet.getData());
        for (Connection con:connections) {
            System.out.print(con.getId()+"----"+con.getInetAddress()+""+con.getLocalIpAddress());
        }

    }

	public Server() throws IOException {
        serverSocket = new ServerSocket(8898);
    }

	public void run() {
        Thread currentThread = Thread.currentThread();

        while (this == currentThread) {
            try {
                Socket s = serverSocket.accept();

                int id = getFreeConnectionId();

                Connection conn = ConnectionFactory.getInstance()
                        .createServerConnection(s, id);
                conn.addConnectionListener(connectionListener);
                conn.open();
                connections.addElement(conn);
            } catch (InterruptedIOException iioe) {
                // ignore , just SOTimeout blowing..
            } catch (IOException ex) {

            }
        }
    }

	private Connection getConnection(int connId) {
        for (Enumeration<Connection> i = connections.elements(); i
                .hasMoreElements();) {
            final Connection conn = (Connection) i.nextElement();

            if (conn.getId() == connId) {
                return conn;
            }
        }
        return null;
    }

	public int getFreeConnectionId() {
        while (getConnection(connectionCounter) != null) {
            connectionCounter++;
        }
        return connectionCounter++;
    }

    public void close(){
        try {
            for(Connection conn:connections){
                conn.close();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPacket(String str,int id){
        for(Connection conn : connections){
            if(conn.open()==true&&conn.isServer()) {
                Packet packet = new Packet(str.getBytes(), str.length());
                conn.send(packet);
                conn.close();
            }
        }
    }

//    public static void main(String arg[]){
//        try {
//            new Server().start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
      public Vector<Connection> getConnections(){
          return this.connections;
      }
}
