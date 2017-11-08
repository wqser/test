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

import java.net.Socket;

/**
 * Connections factory. Creates the Client/Server connections
 */
public class ConnectionFactory {

    private static ConnectionFactory instance  = new ConnectionFactory();

    private ConnectionFactory() {
    }

    /**
     * Returns the factory instance
     * @return the factory instance
     */
    public static ConnectionFactory getInstance() {
        return instance;
    }

    /**
     * Creates new Client (Client-Server) connection
     * @param host server host
     * @param port server port
     * @param id connection ID
     * @return new client (client-server) connection
     */
    public Connection createClientConnection(String host, int port, int id) {
        //return new ObjectStreamConnection(host, port, id);
        return new Connection(host, port, id);
    }

    /**
     * Creates new Server coinnection
     * @param socket socket to read/write
     * @param id connection ID
     * @return new Server coinnection
     */
    public Connection createServerConnection(Socket socket, int id) {
        //return new ObjectStreamConnection(socket, id);
        return new Connection(socket, id);
    }
}