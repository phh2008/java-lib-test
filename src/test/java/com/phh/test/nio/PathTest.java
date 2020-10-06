package com.phh.test.nio;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        Path path = Paths.get("/home/", "/temp5", "2023/");
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

    @Test
    public void test3() throws IOException {
        Path root = Paths.get("/home");
        Path sub = Paths.get("temp6/2020"); // /temp6/2020
        Path fullPath = root.resolve(sub);
        if (!Files.exists(fullPath)) {
            Files.createDirectories(fullPath);
        }
        System.out.println("full path: " + fullPath.toAbsolutePath());
        System.out.println("root path: " + root.toAbsolutePath());
        System.out.println("sub path: " + sub.toAbsolutePath());
    }

    @Test
    public void test4(){

        File file=new File("/xxx//aaa/ccc\\bbb/d");
        System.out.println(file.toPath().toAbsolutePath().normalize().toString());
        System.out.println(file.toPath().toFile().getPath());

    }


}
