package com.phh.test.algorithm;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.algorithm
 * @date 2019/3/17
 */
public class LeetcodeTest {
    /**
     * https://leetcode-cn.com
     * 算法题练习
     */


    /**
     * 获取字符串中最长不重复的子串
     * 例如：pwwkew 中的 wke
     */
    @Test
    public void test1() {

        //String str = "aaabbbaabceadaeeeafafa";
        //String str = "aaaaaaaaa";
        String str = "pwwkew";
        //方式一：通过滑动窗口实现
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0, len = str.length();
        int[] point = new int[]{0, 0};
        while (i < len && j < len) {
            if (!set.contains(str.charAt(j))) {
                set.add(str.charAt(j++));
                int tmp = j - i;
                if (tmp > ans) {
                    ans = tmp;
                    point[0] = i;
                    point[1] = j;
                }
            } else {
                set.remove(str.charAt(i++));
            }
        }
        System.out.println("不重复子串：长度=" + ans + " ," + str.substring(point[0], point[1]));
    }
}
