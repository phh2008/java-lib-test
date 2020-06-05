package com.phh.test.designer;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/3/13
 */
public class Adapter {

    //适配器模式，有三种方式：类的适配，对象适配，接口适配

    public class Source {
        public void method1() {
            System.out.println("this is original method!");
        }
    }

    public interface Targetable {
        /* 与原类中的方法相同 */
        void method1();

        /* 新类的方法 */
        void method2();
    }

    //1:类的适配方式
    public class Adapter1 extends Source implements Targetable {

        @Override
        public void method2() {
            System.out.println("this is the targetable method!");
        }
    }

    //2：对象适配方式
    public class Adapter2 implements Targetable {

        private Source source;

        public Adapter2(Source source) {
            this.source = source;
        }

        @Override
        public void method1() {
            this.source.method1();
        }

        @Override
        public void method2() {
            System.out.println("this is the targetable method!");
        }
    }

    //3：接口适配方式
    public abstract class Adapter3 implements Targetable {

        @Override
        public void method1() {
        }

        @Override
        public void method2() {
        }
    }

    public class Source1Sub extends Adapter3 {
        @Override
        public void method1() {
            //do something
        }
    }

    public class Source2Sub extends Adapter3 {
        @Override
        public void method2() {
            //do something
        }
    }

}
