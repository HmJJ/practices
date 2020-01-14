package com.nott.sort.common;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/1/14 14:32
 * @Modified By:
 **/
public abstract class SortImpl implements Sort {

    public Long beforeSort(String sortName, int[] arr) throws Exception {
        if (arr == null || arr.length == 0)
            throw new IllegalAccessException("数组不能为空");
        System.out.println(sortName + "前：");
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
        Long time = System.currentTimeMillis();
        return time;
    }

    public Long afterSort(String sortName, int[] arr) {
        Long time = System.currentTimeMillis();
        System.out.println(sortName + "后：");
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
        return time;
    }
}
