package com.phh.test.nio;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2019/8/1
 */
public class FileChannelTest {


    @Test
    public void test1() throws IOException {
        String path = "E:\\idea-phh\\my-github\\java-lib-test\\src\\test\\java\\com\\phh\\test\\nio\\";
        FileInputStream in = new FileInputStream(new File(path + "file.text"));
        FileOutputStream out = new FileOutputStream(new File(path + "file2.text"));
        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();
        //创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            //读
            int eof = inChannel.read(buffer);
            if (eof == -1) {
                break;
            }
            //切换模式，写
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }
        inChannel.close();
        outChannel.close();
        in.close();
        out.close();
    }

}
