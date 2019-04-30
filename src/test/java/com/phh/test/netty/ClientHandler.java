package com.phh.test.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.netty
 * @date 2019/4/23
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyProtoBean proto = (MyProtoBean) msg;
        System.out.println("客户端收到>>>>:" + proto.getContent());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String content = "hello ,I am from server 结束！";
        MyProtoBean bean = new MyProtoBean((byte) 1, (byte) 2, content.getBytes("UTF-8").length, content);
        ctx.writeAndFlush(bean);
    }
}
