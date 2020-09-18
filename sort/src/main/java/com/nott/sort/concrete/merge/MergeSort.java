package com.nott.sort.concrete.merge;

import com.nott.sort.factory.SortImpl;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author: wangjun
 * @Description:
 * 归并排序 是建立在归并操作上的一种有效的排序算法。
 * 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 归并排序是一种稳定的排序方法。将已有序的子序列合并，得到完全有序的序列；
 * 即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
 * @Date: created in 2020/1/15 9:35
 * @Modified By:
 **/
@Component
public class MergeSort extends SortImpl {
    @Override
    public void sort(int[] arr) throws Exception {
//        String sortName = "归并排序";
        if (arr.length < 2) {
            throw new IllegalArgumentException("归并排序要求数组长度不小于2");
        }
//        beforeSort(sortName, arr);
        arr = mergeSort(arr);
//        afterSort(sortName, arr);
    }

    private int[] mergeSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int mid = arr.length/2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    /**
     * 将两段排序好的数组结合成一个排序数组
     * @param left
     * @param right
     * @return
     */
    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index=0, l=0, r=0; index<result.length; index++) {
            if (l>=left.length) {
                result[index] = right[r++];
            } else if (r>=right.length) {
                result[index] = left[l++];
            } else if (left[l] > right[r]) {
                result[index] = right[r++];
            } else {
                result[index] = left[l++];
            }
        }
        return result;
    }
}
