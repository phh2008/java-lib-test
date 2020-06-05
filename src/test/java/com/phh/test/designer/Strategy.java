package com.phh.test.designer;

import org.junit.Test;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/3/13
 */
public class Strategy {

    //算法
    public interface Algorithm {
        int calc(int a, int b);
    }

    public class Plus implements Algorithm {

        @Override
        public int calc(int a, int b) {
            return a + b;
        }
    }

    public class Multiply implements Algorithm {

        @Override
        public int calc(int a, int b) {
            return a * b;
        }
    }

    public class Context {

        private Algorithm algorithm;

        public Context(Algorithm algorithm) {
            this.algorithm = algorithm;
        }

        public int calc(int a, int b) {
            return algorithm.calc(a, b);
        }
    }

    @Test
    public void test() {

        System.out.println(new Context(new Plus()).calc(11, 22));
        System.out.println(new Context(new Multiply()).calc(11, 22));

    }


}
