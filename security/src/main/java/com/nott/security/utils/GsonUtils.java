package com.nott.security.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/8/10 15:55
 * @Modified By:
 **/
public class GsonUtils {

    //序列化
    final static JsonSerializer<LocalDateTime> jsonSerializerDateTime = (localDateTime, type, jsonSerializationContext) -> new JsonPrimitive(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    final static JsonSerializer<LocalDate> jsonSerializerDate = (localDate, type, jsonSerializationContext) -> new JsonPrimitive(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    //反序列化
    final static JsonDeserializer<LocalDateTime> jsonDeserializerDateTime = (jsonElement, type, jsonDeserializationContext) -> LocalDateTime.parse(jsonElement.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    final static JsonDeserializer<LocalDate> jsonDeserializerDate = (jsonElement, type, jsonDeserializationContext) -> LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    public static Gson gson = create();

    public static Gson create() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, jsonSerializerDateTime)
                .registerTypeAdapter(LocalDateTime.class, jsonDeserializerDateTime)
                .registerTypeAdapter(LocalDate.class, jsonSerializerDate)
                .registerTypeAdapter(LocalDate.class, jsonDeserializerDate)
                .create();
        return gson;
    }

    public static String toJson(Object object, Class clazz) {
        String json = gson.toJson(object, clazz.getClass());
        return json;
    }

    public static Object fromJson(String json, Class clazz) {
        Object object = gson.fromJson(json, (Type) clazz.getClass());
        return object;
    }

}
