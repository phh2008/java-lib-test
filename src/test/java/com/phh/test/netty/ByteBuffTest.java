package com.phh.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.netty
 * @date 2019/4/19
 */
public class ByteBuffTest {

    Charset utf8 = Charset.forName("UTF-8");

    @Test
    public void test() {
        ByteBuf buff = Unpooled.copiedBuffer("Netty in action rocks!", utf8);
        System.out.println((char) buff.readByte());
        System.out.println(buff.readerIndex());
        System.out.println(buff.writerIndex());
        System.out.println(buff.capacity());
    }

}
