/**
 * @Author: nott
 * @Description:
 * @Date: created in 2020/8/27 13:38
 * @Modified By:
 */
package com.nott.sort.boot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestListController {

    public static void main(String[] args) {
        String loginType = "mobileClient,adminClient";
        List<String> loginClients = new ArrayList<>();
        loginClients.add("mobileClient");
        List<String> types = Arrays.asList(loginType.split(","));
        types = new ArrayList<>(types);
        System.out.println("begin: " + types);
        for (String type : loginClients) {
            System.out.println("check: " + type);
            if (types.contains(type)) {
                System.out.println("before-remove: " + types);
                types.remove(type);
                System.out.println("after-remove: " + types);
            }
        }
        System.out.println("end: " + types);
    }

}
