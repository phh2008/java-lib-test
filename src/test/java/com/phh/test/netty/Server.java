package com.phh.test.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.netty
 * @date 2019/4/23
 */
public class Server {

    private static final int MAX_FRAME_LENGTH = 1024 * 1024;  //最大长度
    private static final int LENGTH_FIELD_LENGTH = 4;  //长度字段所占的字节数
    private static final int LENGTH_FIELD_OFFSET = 2;  //长度偏移
    private static final int LENGTH_ADJUSTMENT = 0;
    private static final int INITIAL_BYTES_TO_STRIP = 0;
    private EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private EventLoopGroup workGroup = new NioEventLoopGroup();
    private Channel channel;

    public void start() {
        ServerBootstrap server = new ServerBootstrap();
        server.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new MyProtoDecoder(MAX_FRAME_LENGTH,
                                LENGTH_FIELD_OFFSET,
                                LENGTH_FIELD_LENGTH,
                                LENGTH_ADJUSTMENT,
                                INITIAL_BYTES_TO_STRIP, false))
                                .addLast(new ServerHandler())
                                .addLast(new MyProtoEncoder());
                    }
                });
        ChannelFuture future = server.bind(6666);
        future.syncUninterruptibly();
        channel = future.channel();
        future.addListener(f -> {
            if (f.isSuccess()) {
                System.out.println("connection established");
            } else {
                System.out.println("connection attempt failed " + f.cause().getMessage());
            }
        });
    }

    public void close() {
        if (channel != null) {
            channel.close();
        }
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }


}
