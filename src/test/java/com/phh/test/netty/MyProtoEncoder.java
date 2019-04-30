package com.phh.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.netty
 * @date 2019/4/23
 */
public class MyProtoEncoder extends MessageToByteEncoder<MyProtoBean> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MyProtoBean msg, ByteBuf out) throws Exception {
        if (msg == null) {
            throw new RuntimeException("消息为空");
        }
        out.writeByte(msg.getType());
        out.writeByte(msg.getFlag());
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent().getBytes("UTF-8"));

    }

}
