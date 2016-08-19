package org.srr.dev.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class GsonUtils {
    private GsonUtils() {
    }

    public static <T> T parseJSONArray(String jsonArr, Type type) {
        Gson gson = new Gson();
        T infos = gson.fromJson(jsonArr, type);
        return infos;
    }

    public static <T> T parseJSON(String json, Class<T> clazz) {
        Gson gson = new Gson();
        T info = gson.fromJson(json, clazz);
        return info;
    }

    public static String toJson(Object o)
    {
        String json;
        Gson gson=new Gson();
        json=gson.toJson(o);
        return json;
    }
}
