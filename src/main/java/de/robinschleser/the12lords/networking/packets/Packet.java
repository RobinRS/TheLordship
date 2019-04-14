package de.robinschleser.the12lords.networking.packets;

import de.robinschleser.the12lords.networking.processors.PacketSerializer;

public interface Packet {

    public void write(PacketSerializer packet) throws Exception;
    public void read(PacketSerializer packet) throws Exception;
    public String toString();

}
