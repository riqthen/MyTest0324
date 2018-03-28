package com.h.mylibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Update by H on 2017/5/8.
 * 使用方法:
 * 在Application中 SPUtils.getInstance().init();
 * 储存获取的方法 SPUtils.getInstance().saveString()  SPUtils.getInstance().getString()
 * 清除的方法 SPUtils.getInstance().clearSp()
 */
public class SPUtils {

    private static SPUtils spUtils;
    private Context mContext;
    private int mMode;

    //初始化,用于获取当前context,并设置sp名称
    public void init(Context context, int mode) { //在onCreate里面初始化，为了得到context
        if (null == context) {
            throw new RuntimeException("Must be use init(context) in Application");
        }
        this.mContext = context;
        this.mMode = mode;
    }

    public static SPUtils getInstance() {
        if (spUtils == null) {
            spUtils = new SPUtils();
        }
        return spUtils;
    }

    //清除某个sp
    public boolean clearSp(String spName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, mMode);
        return sharedPreferences.edit().clear().commit();
    }

    // -------------> boolean
    public boolean saveBoolean(String key, boolean value, String spName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, mMode);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public boolean getBoolean(String key, boolean defaultVal, String spName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, mMode);
        return sharedPreferences.getBoolean(key, defaultVal);
    }

    // -------------> String
    public boolean saveString(String key, String value, String spName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, mMode);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }


    public String getString(String key, String spName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, mMode);
        return sharedPreferences.getString(key, "");
    }

    public String getString(String key, String defaultValue, String spName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, mMode);
        return sharedPreferences.getString(key, defaultValue);
    }

    // -------------> int
    public boolean saveInt(String key, int value, String spName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, mMode);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public int getInt(String key, String spName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, mMode);
        return sharedPreferences.getInt(key, -10000);
    }

    public int getInt(String key, int defaultValue, String spName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, mMode);
        return sharedPreferences.getInt(key, defaultValue);
    }


    // -------------> float
    public boolean saveFloat(String key, float value, String spName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, mMode);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    public double getFloat(String key, String spName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, mMode);
        return sharedPreferences.getFloat(key, -10000.0f);
    }

    public double getFloat(String key, float defaultValue, String spName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, mMode);
        return sharedPreferences.getFloat(key, defaultValue);
    }

    // -------------> long
    public boolean saveLong(String key, long value, String spName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, mMode);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public double getLong(String key, String spName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, mMode);
        return sharedPreferences.getLong(key, -10000);
    }

    public double getLong(String key, long defaultValue, String spName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, mMode);
        return sharedPreferences.getLong(key, defaultValue);
    }

    // -------------> Set<String>
    public boolean saveSet(String key, Set<String> value, String spName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, mMode);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key, value);
        return editor.commit();
    }

    public Set<String> getSet(String key, String spName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, mMode);
        return sharedPreferences.getStringSet(key, new HashSet<String>());
    }

    public Set<String> getSet(String key, Set<String> strings, String spName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, mMode);
        return sharedPreferences.getStringSet(key, strings);
    }


    // -------------> List<T>
    public <T> boolean saveList(String key, List<T> list, String spName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, mMode);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (list.isEmpty()) {
            editor.putString(key, "[]");
            editor.apply();
            return true;
        }
        boolean result;
        String type;
        try {   //防止集合为空的时候报错
            type = list.get(0).getClass().getSimpleName();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        JsonArray array = new JsonArray();
        try {
            switch (type) {
                case "Boolean":
                    for (int i = 0; i < list.size(); i++) {
                        array.add((Boolean) list.get(i));
                    }
                    break;
                case "Long":
                    for (int i = 0; i < list.size(); i++) {
                        array.add((Long) list.get(i));
                    }
                    break;
                case "Float":
                    for (int i = 0; i < list.size(); i++) {
                        array.add((Float) list.get(i));
                    }
                    break;
                case "String":
                    for (int i = 0; i < list.size(); i++) {
                        array.add((String) list.get(i));
                    }
                    break;
                case "Integer":
                    for (int i = 0; i < list.size(); i++) {
                        array.add((Integer) list.get(i));
                    }
                    break;
                default:
                    Gson gson = new Gson();
                    for (int i = 0; i < list.size(); i++) {
                        JsonElement obj = gson.toJsonTree(list.get(i));
                        array.add(obj);
                    }
                    break;
            }
            editor.putString(key, array.toString());
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        editor.apply();
        return result;
    }

    public <T> List<T> getList(String key, Class<T> cls, String spName) {
        List<T> list = new ArrayList<>();
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, mMode);
        String json = sharedPreferences.getString(key, "");
        if (!json.equals("") && json.length() > 0) {
            Gson gson = new Gson();
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for (JsonElement elem : array) {
                list.add(gson.fromJson(elem, cls));
            }
        }
        return list;
    }

    // -------------> Map<K, V>
    public <K, V> boolean saveHashMap(String key, Map<K, V> map, String spName) {
        boolean result;
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, mMode);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try {
            Gson gson = new Gson();
            String json = gson.toJson(map);
            editor.putString(key, json);
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        editor.apply();
        return result;
    }

    public <V> HashMap<String, V> getHashMap(String key, Class<V> clsV, String spName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, mMode);
        String json = sharedPreferences.getString(key, "");
        HashMap<String, V> map = new HashMap<>();
        Gson gson = new Gson();
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entrySet = obj.entrySet();
        for (Map.Entry<String, JsonElement> entry : entrySet) {
            String entryKey = entry.getKey();
            JsonObject value = (JsonObject) entry.getValue();
            map.put(entryKey, gson.fromJson(value, clsV));
        }
        return map;
    }
}