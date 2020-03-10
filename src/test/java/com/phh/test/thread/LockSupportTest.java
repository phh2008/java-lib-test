package com.phh.test.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/1/11
 */
public class LockSupportTest {


    public static void main(String[] args) throws InterruptedException {
        String a = new String("A");
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("睡觉");
                LockSupport.park(a);
                System.out.println("起床");
            }
        });
        t.setName("A-Name");
        t.start();
        Thread.sleep(300000);
        System.out.println("妈妈喊我起床");
        LockSupport.unpark(t);
    }


}
