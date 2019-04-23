package com.phh.test.java8;

import org.junit.Test;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.java8
 * @date 2019/4/16
 */
public class ReferenceTest {

    @Test
    public void test2() throws InterruptedException {

        String a = new String("words");
        SoftReference<String> ref = new SoftReference<>(a);
        a = null;
        System.gc();
        Thread.sleep(5000);
        System.out.println(ref.get());
    }

    @Test
    public void test3() throws InterruptedException {
        ReferenceQueue<String> queue = new ReferenceQueue<>();
        String a = new String("words");
        WeakReference<String> ref = new WeakReference<>(a, queue);
        a = null;
        System.gc();
        Thread.sleep(5000);
        Reference<? extends String> tmp = queue.poll();
        System.out.println(tmp != null ? tmp.get() : "none");
        System.out.println(ref.get());
    }

}
