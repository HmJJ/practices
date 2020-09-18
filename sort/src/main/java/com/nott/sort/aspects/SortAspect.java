/**
 * @Author: nott
 * @Description:
 * @Date: created in 2020/9/18 10:13
 * @Modified By:
 */
package com.nott.sort.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class SortAspect {

    @Pointcut("execution(* com.nott.sort.concrete..*.sort(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void startSort(JoinPoint joinPoint) throws Exception {
        Object[] arr = joinPoint.getArgs();
        String sortName = joinPoint.getSignature().getName();
        if (arr == null || arr.length == 0)
            throw new IllegalAccessException("数组不能为空");
        System.out.println(sortName + "前：");
        for (Object a : arr) {
            System.out.print(a + " ");
        }
    }

    @After("pointCut()")
    public void endSort(JoinPoint joinPoint) {
        Object[] arr = joinPoint.getArgs();
        String sortName = joinPoint.getSignature().getName();
        System.out.println(sortName + "后：");
        for (Object a : arr) {
            System.out.print(a + " ");
        }
    }

}
