/**
 * @Author: nott
 * @Description:
 * @Date: created in 2020/8/28 10:30
 * @Modified By:
 */
package com.nott.sort.boot;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class TestSortArrayController {

    public static void main(String[] args) {
        JSONArray array = new JSONArray();
        JSONObject obj1 = new JSONObject();obj1.put("titles", "D");array.put(obj1);
        JSONObject obj2 = new JSONObject();obj2.put("titles", "C");array.put(obj2);
        JSONObject obj3 = new JSONObject();obj3.put("titles", "B");array.put(obj3);
        JSONObject obj4 = new JSONObject();obj4.put("titles", "A");array.put(obj4);
        Map<String, JSONObject> preData = new HashMap<>();
        List<String> titles = new ArrayList<>();
        Iterator iterator = array.iterator();
        while (iterator.hasNext()) {
            JSONObject object = (JSONObject) iterator.next();
            String title = object.getString("titles");
            titles.add(title);
            preData.put(title, object);
        }
        Collections.sort(titles);
        JSONArray array1 = new JSONArray();
        for (String key : titles) {
            array1.put(preData.get(key));
        }
        System.out.println(array1.toString());
    }

}
