package com.nott.sort.concrete.shell;

import com.nott.sort.factory.SortImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: wangjun
 * @Description:
 * 希尔排序是希尔（Donald Shell） 于1959年提出的一种排序算法。
 * 希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，
 * 也称为缩小增量排序，同时该算法是冲破O(n2）的第一批算法之一。
 * 它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序。
 * 希尔排序是把记录按下表的一定增量分组，对每组使用直接插入排序算法排序；
 * 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
 * @Date: created in 2020/1/14 16:53
 * @Modified By:
 **/
@Component
public class ShellSort extends SortImpl {

    public static final Logger log = LoggerFactory.getLogger(ShellSort.class);

    @Override
    public void sort(int[] arr) throws Exception {
        Long startTime = beforeSort("希尔排序", arr);
        int len = arr.length;
        int temp, gap = len/2;
        while (gap>0) {
            for (int i=gap; i<len; i++) {
                temp = arr[i];
                int preIndex = i-gap;
                while (preIndex>=0 && arr[preIndex]>temp) {
                    arr[preIndex+gap] = arr[preIndex];
                    preIndex -= gap;
                }
                arr[preIndex+gap] = temp;
            }
            gap /= 2;
        }
        Long endTime = afterSort("希尔排序", arr);
        System.out.println("排序耗时：" + (endTime - startTime));
    }
}
