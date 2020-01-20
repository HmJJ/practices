package com.nott.sort.concrete.quick;

import com.nott.sort.factory.SortImpl;
import org.springframework.stereotype.Component;

/**
 * @Author: wangjun
 * @Description:
 * 快速排序 的基本思想：通过一趟排序将待排记录分隔成独立的两部分，
 * 其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 * @Date: created in 2020/1/19 11:57
 * @Modified By:
 **/
@Component
public class QuickSort extends SortImpl {
    @Override
    public void sort(int[] arr) throws Exception {
        String sortName = "快速排序";
        beforeSort(sortName, arr);
//        arr = quickSort(arr, 0, arr.length - 1); //TODO 有问题
        afterSort(sortName, arr);
    }

    private int[] quickSort(int[] arr, int start, int end) {
        if (arr.length < 1 || start < 0 || end >= arr.length || start > end)
            return null;
        int smallIndex = partition(arr, start, end);
        if (smallIndex > start)
            quickSort(arr, start, smallIndex - 1);
        if (smallIndex < end)
            quickSort(arr, smallIndex + 1, end);
        return arr;
    }

    private int partition(int[] arr, int start, int end) {
        int pivot = (int) (start + Math.random() * (end - start + 1));
        int smallIndex = start + 1;
        swap(arr, pivot, end);
        for (int i = start; i <= end; i++) {
            if (arr[i] <= arr[end]) {
                smallIndex++;
                if (i > smallIndex)
                    swap(arr, i, smallIndex);
            }
        }
        return smallIndex;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
