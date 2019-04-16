package com.phh.test.java8;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.java8
 * @date 2019/2/26
 */
public class MyClassloader extends ClassLoader {

    private String classPath;

    public MyClassloader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] buff = fromFile(name);
        if (buff != null) {
            return defineClass(name, buff, 0, buff.length);
        }
        return super.findClass(name);
    }

    private byte[] fromFile(String className) {
        String filePath = classPath + File.separator + className.replace('.', File.separatorChar) + ".class";
        FileInputStream in = null;
        try {
            in = new FileInputStream(filePath);
            byte[] buff = new byte[in.available()];
            in.read(buff);
            return buff;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

}
