package com.phh.test.thread;

import java.io.IOException;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.thread
 * @date 2019/3/19
 */
public class Test {

    /**
     * 守护线程的特性
     */
    @org.junit.Test
    public void test1() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //thread.setDaemon(true);
        thread.start();
    }

    public static void main(String[] args) throws IOException {
        Test test = new Test();
        test.test1();
        System.out.println(Thread.currentThread().isDaemon());
    }

}
