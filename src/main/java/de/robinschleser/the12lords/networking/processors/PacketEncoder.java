package de.robinschleser.the12lords.networking.processors;

import de.robinschleser.the12lords.networking.NetworkClient;
import de.robinschleser.the12lords.networking.packets.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        int id = NetworkClient.getPackets().indexOf(packet.getClass());
        if(id == -1)
            throw new NullPointerException("Invalid packet recived! " + packet.getClass().getSimpleName());
        PacketSerializer serializer = new PacketSerializer(byteBuf);
        serializer.writeVarInt(id);
        packet.write(serializer);

    }

}
