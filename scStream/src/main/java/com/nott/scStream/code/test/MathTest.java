package com.nott.scStream.code.test;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/8/30 18:19
 * @Modified By:
 **/
public class MathTest {

    public static void main(String[] args) {
        int A = 422;
        int B = (int) Math.ceil(A/10.0);
        System.out.println(B);

        String url = "https://map.taobao.com/item/api/itemStoreList.do?locType=current&_input_charset=utf-8&source=map_itemdetail&pageSize=20&pageNo=1&areaCode=310115&itemId=604699475944&tbToken=eee17f6759e3a";
        String[] splits = url.split("&");
        String newUrl = "";
        for (String str : splits) {
            if (str.startsWith("pageNo")) {
                str = str.substring(0, 7);
                str = str + 2;
                System.out.println(str);
            }
            if (!"".equals(newUrl)) {
                newUrl += "&";
            }
            newUrl += str;
        }
        System.out.println(newUrl);
    }

}
