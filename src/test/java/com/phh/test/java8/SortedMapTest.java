package com.phh.test.java8;

import org.junit.Test;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.java8
 * @date 2019/2/24
 */
public class SortedMapTest {


    @Test
    public void test() {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(6, "F");
        treeMap.put(5, "E");
        treeMap.put(1, "A");
        treeMap.put(2, "B");
        treeMap.put(3, "C");
        treeMap.put(4, "D");
        System.out.println(treeMap.tailMap(3)); //{3=C, 4=D, 5=E, 6=F}
        System.out.println(treeMap.headMap(4)); //{1=A, 2=B, 3=C}
        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.lastEntry());
        SortedMap<Integer, String> sortedMap = treeMap.tailMap(5);
        System.out.println(sortedMap);
    }

}
