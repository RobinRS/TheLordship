package de.robinschleser.the12lords.networking.packets;

import de.robinschleser.the12lords.networking.processors.PacketSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PingPacket implements Packet {

    String name;

    @Override
    public void write(PacketSerializer packet) throws Exception {

    }

    @Override
    public void read(PacketSerializer packet) throws Exception {

    }
}
