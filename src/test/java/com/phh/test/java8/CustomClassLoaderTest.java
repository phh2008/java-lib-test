package com.phh.test.java8;

import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.java8
 * @date 2019/2/26
 */
public class CustomClassLoaderTest {

    @Test
    public void test() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassloader classloader = new MyClassloader("D:\\workspace_idea\\spring\\lib-test\\target\\test-classes");
        Class<?> clazz = classloader.findClass("com.phh.test.java8.BitSetTest");
        if (clazz != null) {
            Object instance = clazz.newInstance();
            Method m = clazz.getMethod("test", null);
            m.invoke(instance, null);

            System.out.println(clazz.getClassLoader().toString());
        }
    }

    @Test
    public void test2() {
        String aaa = "com.phh.test";
        System.out.println(aaa.replaceAll("\\.", Matcher.quoteReplacement(File.separator)));
        //不加Matcher.quoteReplacement会报错
    }

}