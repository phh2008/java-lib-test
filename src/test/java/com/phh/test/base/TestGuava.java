package com.phh.test.base;

import com.google.common.util.concurrent.Striped;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/5/13
 */
public class TestGuava {


    private Striped<Lock> striped = Striped.lazyWeakLock(1024);

    @Test
    public void testStriped() throws InterruptedException {

        for (int i = 1; i < 4; i++) {
            int j = i;
            new Thread() {
                @Override
                public void run() {
                    Lock lock = striped.get("test_key");
                    try {
                        if (lock.tryLock(3, TimeUnit.SECONDS)) {
                            try {
                                System.out.println("i=" + j);
                                TimeUnit.SECONDS.sleep(10);
                                int a=1/0;
                            } finally {
                                lock.unlock();
                                System.out.println("失放锁了，i=" + j);
                            }
                        } else {
                            System.out.println("未获取到锁，请稍后，i=" + j);
                        }
                        System.out.println("over.........");
                    } catch (Exception e) {
                        System.out.print("异常了 i=" + j);
                        System.out.println("  Exception :" + e);
                    }
                }
            }.start();
        }
        TimeUnit.SECONDS.sleep(100000);
    }

}
