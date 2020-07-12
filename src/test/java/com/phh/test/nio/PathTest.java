package com.phh.test.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/12
 */
public class PathTest {

    @Test
    public void test() throws IOException {
        Path path = Paths.get( "/home/", "/temp5", "2023/");
        if (!Files.exists(path)) {
            Path path2 = Files.createDirectories(path);
            System.out.println(path2.toAbsolutePath().normalize());
        }
        System.out.println(path.toAbsolutePath());
    }

    @Test
    public void test1() throws IOException {
        Path path = Paths.get("E:/", "home", "temp", "2020", "xxx-2020-07-12.txt");
        if (!Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        System.out.println(path.toAbsolutePath());
        System.out.println(Files.probeContentType(path));
    }

    @Test
    public void test2() {
        File file = new File("/home/temp2/2021");
        System.out.println(file.toPath().toAbsolutePath());
        if (!file.exists()) {
            file.mkdirs();
        }
    }


}
