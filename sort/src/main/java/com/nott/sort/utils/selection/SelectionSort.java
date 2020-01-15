package com.nott.sort.utils.selection;

import com.nott.sort.factory.SortImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: wangjun
 * @Description:
 * 选择排序(Selection-sort) 是一种简单直观的排序算法。
 * 它的工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 * 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * 以此类推，直到所有元素均排序完毕。
 * @Date: created in 2020/1/14 14:25
 * @Modified By:
 **/
@Component
public class SelectionSort extends SortImpl {

    public static final Logger log = LoggerFactory.getLogger(SelectionSort.class);

    @Override
    public void sort(int[] arr) throws Exception {
        Long startTime = beforeSort("选择排序", arr);
        for (int i=0; i<arr.length; i++) {
            int minIndex = i;
            for (int j=i; j<arr.length; j++) {
                if (arr[j]<arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        Long endTime = afterSort("选择排序", arr);
        System.out.println("排序耗时：" + (endTime - startTime));
    }

}
