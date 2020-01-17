package com.nott.sort.concrete.insertion;

import com.nott.sort.factory.SortImpl;
import org.springframework.stereotype.Component;

/**
 * @Author: wangjun
 * @Description:
 * 插入排序（Insertion-Sort） 的算法描述是一种简单直观的排序算法。它的工作原理是通过构建有序序列，
 * 对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。插入排序在实现上，
 * 通常采用in-place排序（即只需用到O(1)的额外空间的排序），因而在从后向前扫描过程中，
 * 需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
 * @Date: created in 2020/1/14 15:22
 * @Modified By:
 **/
@Component
public class InsertionSort extends SortImpl {
    @Override
    public void sort(int[] arr) throws Exception {
        Long startTime = beforeSort("插入排序", arr);
        int current;
        for (int i=0; i<arr.length-1; i++) {
            current = arr[i+1];
            int preIndex = i;
            while (preIndex>=0 && current<arr[preIndex]) {
                arr[preIndex+1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex+1] = current;
        }
        Long endTime = afterSort("插入排序", arr);
        System.out.println("排序耗时：" + (endTime - startTime));
    }
}
