package com.phh.test.algorithm;

import org.junit.Test;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 排序算法
 *
 * @author phh
 * @version V1.0
 * @date 2020/1/11
 */
public class SortTest {


    /**
     * 快速排序1
     * 递归实现
     */
    @Test
    public void quickSort1Test() {
        int[] array = {16, 5, 12, 6, 14, 15, 2, 10, 11, -1, 4, 9, 7, 1, 13, 3, 0, 8};
        System.out.println(Arrays.toString(array));
        quickSort1(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 快排实现
     * <p>
     * 递归实现
     *
     * @param array
     * @param start
     * @param end
     */
    private void quickSort1(int[] array, int start, int end) {
        if (array == null || start >= end) {
            return;
        }
        int i = start, j = end;
        int pivotVal = array[start];
        while (i < j) {
            while (i < j && array[j] > pivotVal) {
                j--;
            }
            while (i < j && array[i] < pivotVal) {
                i++;
            }
            if (i < j) {
                swap(array, i, j);
            }
        }
        array[i] = pivotVal;
        //对pivot左边值排序
        quickSort1(array, start, i - 1);
        //对pivot右边值排序
        quickSort1(array, i + 1, end);
    }

    /**
     * 插入排序
     */
    private void insertSort(int[] array) {
        if (array == null) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            int tmp = array[i];
            for (int j = i; j >= 0; j--) {
                if (array[j - 1] > tmp) {
                    array[j] = array[j - 1];
                }
            }
        }
    }

    /**
     * 交换组数元素
     *
     * @param array
     * @param i
     * @param j
     */
    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }


}