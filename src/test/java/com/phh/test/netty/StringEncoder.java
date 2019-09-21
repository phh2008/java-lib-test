package com.phh.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2019/8/14
 */
public class StringEncoder extends MessageToByteEncoder<String> {


    @Override
    protected void encode(ChannelHandlerContext ctx, String s, ByteBuf byteBuf) throws Exception {
        System.out.println(s);
        byteBuf.writeCharSequence(s, Charset.forName("UTF-8"));
        ctx.writeAndFlush(byteBuf);
    }
}
