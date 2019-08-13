package com.phh.test.base;

/**
 * 类加载地过程分析
 *
 * @author phh
 * @version V1.0
 * @date 2019/8/8
 */
public class ClassLoadAnalysis {

    //静太成员与实例成员初始化时机不一样，实例成员是在创建实例的时候触发初始化
    private static ClassLoadAnalysis singleTon = new ClassLoadAnalysis();
    public static int count1;
    public static int count2 = 0;

    static {
        //静态变量赋值与静态代码块都是在类初化才会执行的，且是有先后顺序的
        //count2 = 3;
    }

    private ClassLoadAnalysis() {
        count1++;
        count2++;
    }

    public static ClassLoadAnalysis getInstance() {
        return singleTon;
    }

    public static class Test {
        public static void main(String[] args) {
            ClassLoadAnalysis singleTon = ClassLoadAnalysis.getInstance();
            System.out.println("count1=" + singleTon.count1);
            System.out.println("count2=" + singleTon.count2);
        }
    }

}
