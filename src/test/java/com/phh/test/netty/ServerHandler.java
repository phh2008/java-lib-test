package com.phh.test.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.netty
 * @date 2019/4/23
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyProtoBean proto = (MyProtoBean) msg;
        try {
            //简单打印
            System.out.println("server handler>>>>>>:" + proto.getContent());
            proto.setContent("这是服务端响应内容哈哈我回复你了");
            proto.setLength(proto.getContent().getBytes("UTF-8").length);
            ctx.channel().writeAndFlush(proto);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
