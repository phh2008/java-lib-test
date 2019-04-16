package com.phh.test.classload;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.classload
 * @date 2019/3/17
 */
public class 类的加载时机 {

    public static void main(String[] args) {
        System.out.println(SubClass.value);
        SubClass[] arr = new SubClass[2];
        //
        System.out.println(ConstClass.HELLO_WORLD);
    }

    static class SupClass {
        static {
            System.out.println("supclass......");
        }

        public static int value = 123;
    }

    static class SubClass extends SupClass {
        static {
            System.out.println("subclass......");
        }
    }

    static class ConstClass {
        static {
            System.out.println("类的加载时机.class init");
        }

        public static final String HELLO_WORLD = "hello world";
    }


}
