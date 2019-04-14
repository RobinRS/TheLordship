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
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

import java.util.Arrays;
import java.util.List;

public class NetworkClient {

    private EventLoopGroup group;
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

    public int connect() {
        Bootstrap b = new Bootstrap().group(new NioEventLoopGroup());
        b.channel(NioSocketChannel.class);
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
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
                return 2;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 3;
        }

        if (c.isSuccess()) {
            channel = c.channel();
            c.channel().closeFuture().addListener(channelFuture -> {
                System.out.println("Lost connection to cloud...");
            });
            System.out.println("Connectet to Server: " + host + " on port " + port);
            return 0;
        } else {
            System.out.println("Failed!");
            return 1;
        }
    }

    public void sendPacket(Packet packet) {
        channel.writeAndFlush(packet, channel.voidPromise());
    }

    public static List<Class<? extends Packet>> getPackets() {
        return packets;
    }
}
