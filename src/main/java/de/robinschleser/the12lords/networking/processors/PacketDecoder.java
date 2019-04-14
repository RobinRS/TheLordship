package de.robinschleser.the12lords.networking.processors;

import de.robinschleser.the12lords.networking.NetworkClient;
import de.robinschleser.the12lords.networking.packets.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        PacketSerializer serilizer = new PacketSerializer(byteBuf);
        int id = serilizer.readVarInt();
        Class<? extends Packet> packetClazz = NetworkClient.getPackets().get(id);
        if(packetClazz == null) {
            throw new NullPointerException("packet with id " + id + " not existing!");
        }
        Packet packet = packetClazz.newInstance();
        packet.read(serilizer);
        out.add(packet);
    }

}
