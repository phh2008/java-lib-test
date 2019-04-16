package com.phh.test.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.java8
 * @date 2019/2/22
 */
public class BitSetTest {


    @Test
    public void test() {
        BitSet bs = new BitSet(100);
        List<Integer> rand = new ArrayList<>(1000);
        Random random = new Random();
        Stream.iterate(1, i -> i + 1).limit(100).forEach(i -> {
            int tmp = random.nextInt(1000);
            rand.add(tmp);
            bs.set(tmp);
        });
        System.out.println("产生100个随机数：" + rand);
        List<Integer> notRandom = new ArrayList<>();
        Stream.iterate(1, i -> i + 1).limit(1000).forEach(e -> {
            if (!bs.get(e)) {
                notRandom.add(e);
            }
        });
        System.out.println("1-1000不在随机数中的有：" + notRandom);
    }

}
