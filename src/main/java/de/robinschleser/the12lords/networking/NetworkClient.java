package de.robinschleser.the12lords.networking;

import de.robinschleser.the12lords.networking.packets.Packet;
import de.robinschleser.the12lords.networking.packets.PingPacket;
import de.robinschleser.the12lords.networking.processors.PacketDecoder;
import de.robinschleser.the12lords.networking.processors.PacketEncoder;
import de.robinschleser.the12lords.networking.processors.PacketHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

import java.util.Arrays;
import java.util.List;

public class NetworkClient {

    private static Channel channel;

    private String host;
    private int port;

    private static final List<Class<? extends Packet>> packets = Arrays.asList(
            PingPacket.class
    );

    public NetworkClient(String ip, int port) {
        this.host = ip;
        this.port = port;
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        Bootstrap b = new Bootstrap().group(new NioEventLoopGroup());
        b.channel(NioSocketChannel.class);
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) {
                socketChannel.pipeline()
                        .addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4))
                        .addLast(new PacketDecoder())
                        .addLast(new LengthFieldPrepender(4))
                        .addLast("packet-encoder", new PacketEncoder())
                        .addLast("packet-handler", new PacketHandler());
            }
        });
        ChannelFuture c = b.connect(host, port);
        try {
            if (!c.await(200000)) {
                return;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }

        if (c.isSuccess()) {
            channel = c.channel();
            c.channel().closeFuture().addListener(channelFuture -> System.out.println("Lost connection to server..."));
            System.out.println("Connected to server: " + host + " on port " + port);
        } else {
            System.out.println("Failed!");
        }
    }

    public void sendPacket(Packet packet) {
        channel.writeAndFlush(packet, channel.voidPromise());
    }

    public static List<Class<? extends Packet>> getPackets() {
        return packets;
    }
}
