package com.phh.test.algorithm;

import org.junit.Test;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2019/8/9
 */
public class MergeSortedArray {


    /**
     * 合并两个有序的数组
     */
    @Test
    public void test() {
        int[] arr1 = {3, 5, 6, 7, 9};
        int[] arr2 = {1, 2, 4, 8};
        printArray(arr1);
        printArray(arr2);
        int len1 = arr1.length;
        int len2 = arr2.length;
        int i = 0, j = 0, k = 0;
        int[] newArray = new int[len1 + len2];
        for (; i < len1 && j < len2; ) {
            if (arr1[i] < arr2[j]) {
                newArray[k++] = arr1[i++];
            } else {
                newArray[k++] = arr1[j++];
            }
        }
        while (i < len1) {
            newArray[k++] = arr1[i++];
        }
        while (j < len2) {
            newArray[k++] = arr2[j++];
        }
        printArray(newArray);
    }

    private void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(" " + i);
        }
        System.out.println();
    }

}
