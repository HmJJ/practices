package com.nott.sort.concrete.recursive;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/10 14:35
 * @Modified By:
 **/
public class Native {
    public static void main(String[] args) {
        List<Data> dataList = new ArrayList<>();
        dataList.add(new Data(5L, "5"));
        dataList.add(new Data(2L, "2"));
        dataList.add(new Data(7L, "7"));
        for (Data data : dataList) {
            System.out.println(data.toString());
        }
        dataList.sort(new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return o1.id.compareTo(o2.id);
            }
        });
        for (Data data : dataList) {
            System.out.println(data.toString());
        }
    }

    public static class Data {
        private Long id;
        private String message;

        public Data(Long id, String message) {
            this.id = id;
            this.message = message;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id=" + id +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}
