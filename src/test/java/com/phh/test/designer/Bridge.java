package com.phh.test.designer;

import org.junit.Test;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/3/13
 */
public class Bridge {

    //桥接模式，主要是分离抽象与实现

    public interface SourceAble {
        void method();
    }

    public class SourceSub1 implements SourceAble {

        @Override
        public void method() {
            //do something
        }
    }

    public class SourceSub2 implements SourceAble {

        @Override
        public void method() {
            //do something
        }
    }

    public abstract class AbstractBridge {
        SourceAble source;

        public AbstractBridge(SourceAble source) {
            this.source = source;
        }

        public void method() {
            this.source.method();
        }
    }

    public class MyBridge extends AbstractBridge {

        public MyBridge(SourceAble source) {
            super(source);
        }

        @Override
        public void method() {
            super.method();
        }
    }

    @Test
    public void test() {
        SourceAble source1 = new SourceSub1();
        MyBridge bridge = new MyBridge(source1);
        bridge.method();
    }

}
