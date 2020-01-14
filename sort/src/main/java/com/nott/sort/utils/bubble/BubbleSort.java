package com.nott.sort.utils.bubble;

import com.nott.sort.common.SortImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: wangjun
 * @Description:
 * 冒泡排序 是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，
 * 如果它们的顺序错误就把它们交换过来。走访数列的工作是重复地进行直到没有再需要交换，
 * 也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
 * @Date: created in 2020/1/14 11:56
 * @Modified By:
 **/
@Component
public class BubbleSort extends SortImpl {

    public static final Logger log = LoggerFactory.getLogger(BubbleSort.class);

    @Override
    public void sort(int[] arr) throws Exception {
        Long startTime = beforeSort("冒泡排序", arr);
        for (int i=0; i<arr.length; i++) {
            for (int j=0; j<arr.length-1-i; j++) {
                if (arr[j+1] < arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        Long endTime = afterSort("冒泡排序", arr);
        System.out.println("排序耗时：" + (endTime - startTime));
    }
}
