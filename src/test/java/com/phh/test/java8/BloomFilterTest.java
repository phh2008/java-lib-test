package com.phh.test.java8;

import java.util.BitSet;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.java8
 * @date 2019/2/23
 */
public class BloomFilterTest {

    private static final int SIZE = 1 << 24;
    private BitSet bitSet = new BitSet(SIZE);
    //8位种子
    private static final int seeds[] = new int[]{3, 5, 7, 9, 11, 13, 17, 19};
    private Hash[] hashs = new Hash[8];

    public static void main(String[] args) {
        BloomFilterTest bloomFilter = new BloomFilterTest();
        //添加测试数据
        bloomFilter.add("phh@163.com");
        bloomFilter.add("phh@164.com");
        bloomFilter.add("phh@165.com");
        bloomFilter.add("phh@166.com");
        bloomFilter.add("phh@166.com");
        //验证是否存在
        System.out.println(bloomFilter.contains("phh@165.com"));
        System.out.println(bloomFilter.contains("phh@168.com"));
    }

    public BloomFilterTest() {
        for (int i = 0; i < seeds.length; i++) {
            hashs[i] = new Hash(seeds[i]);
        }
    }

    /**
     * 添加数据到bitSet
     *
     * @param value
     */
    public void add(String value) {
        for (Hash hash : hashs) {
            bitSet.set(hash.getHash(value));
        }
    }

    /**
     * 验证数据是否存在
     *
     * @param value
     * @return
     */
    public boolean contains(String value) {
        boolean exist = true;
        for (Hash hash : hashs) {
            exist &= bitSet.get(hash.getHash(value));
        }
        return exist;
    }

    /**
     * hash算法
     */
    class Hash {
        private int seed = 0;

        public Hash(int seed) {
            this.seed = seed;
        }

        public int getHash(String value) {
            int hash = 0;
            int len = value.length();
            for (int i = 0; i < len; i++) {
                hash = hash * seed + value.charAt(i);
            }
            return hash & (SIZE - 1);
        }
    }

}
