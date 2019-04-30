package com.phh.test.java8;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.java8
 * @date 2019/4/25
 */
public class StreamTest {


    @Test
    public void test() {

        ThreadLocalRandom random = ThreadLocalRandom.current();

        IntStream.generate(() -> random.nextInt(10))
                .limit(10)
                .forEach(i -> {
                    System.out.println(i);
                });

    }

    @Test
    public void test2(){

        IntStream flow = IntStream.rangeClosed(0, 100);

        flow.skip(101).limit(50).forEach(i->{
            System.out.println(i);
        });
    }

}
