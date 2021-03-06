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


/**
 * Instances of this class are sent when packet received
 */
public class PacketReceivedEvent extends ConnectionEvent {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Packet packet;

    /**
     * Constructs connection event
     * @param source The object on which the Event initially occurred.
     * @param packet The received packet
     */
    public PacketReceivedEvent(Object source, Packet packet) {
        super(source, PACKET_RECEIVED);
        this.packet = packet;
    }

    /**
     * Returns the received packet
     * @return the received packet
     */
    public Packet getPacket() {
        return packet;
    }
}
