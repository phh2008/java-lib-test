package com.phh.test.base;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2019/8/13
 */
public class Test2 {

    private static Test2 intance = new Test2();
    //非静态变量
    private int a = 2;
    private int b = 0;

    private double c = 8.2;

    {
        c = 3.3;
    }

    //定议变量初始值与非静态代码块都要比构造函数先执行

    private Test2() {
        this.a++;
        this.b++;
    }

    public static class Main {
        public static void main(String[] args) {
            Test2 test = Test2.intance;
            System.out.println(test.a);
            System.out.println(test.b);
            System.out.println(test.c);
        }
    }


}
