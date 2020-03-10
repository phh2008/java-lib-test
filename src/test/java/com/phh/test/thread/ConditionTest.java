package com.phh.test.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/1/10
 */
public class ConditionTest {

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        new Thread(() -> {
            lock.lock();//请求锁
            try {
                System.out.println(Thread.currentThread().getName() + "==》进入等待");
                condition.await();//设置当前线程进入等待
                System.out.println(Thread.currentThread().getName() + "==》继续执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "==》释放锁");
                lock.unlock();//释放锁
            }
        }).start();

        new Thread(() -> {
            lock.lock();//请求锁
            try {
                System.out.println(Thread.currentThread().getName() + "=》进入");
                Thread.sleep(2000);//休息2秒
                condition.signal();//随机唤醒等待队列中的一个线程
                System.out.println(Thread.currentThread().getName() + "休息结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "==》释放锁");
                lock.unlock();//释放锁
            }
        }).start();
    }

}
