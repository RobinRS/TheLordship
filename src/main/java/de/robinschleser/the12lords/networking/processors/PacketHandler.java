package de.robinschleser.the12lords.networking.processors;

import de.robinschleser.the12lords.Starter;
import de.robinschleser.the12lords.networking.packets.Packet;
import de.robinschleser.the12lords.networking.packets.PingPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class PacketHandler extends SimpleChannelInboundHandler<Packet> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet in) throws Exception {
        if (in instanceof PingPacket) {
            System.out.println("Current Ping: " + (System.currentTimeMillis() - Starter.pingSend + " MS.") );
            Thread.sleep(500);
            Starter.getClient().sendPacket(new PingPacket("Client"));
            Starter.pingSend = System.currentTimeMillis();
        }
    }
}
