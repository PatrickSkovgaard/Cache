package com.example.demo.models;

import java.util.HashMap;
import java.util.Map;

public class Cache {

    private Map<String, String> cache = new HashMap<>();

    public Cache () {
    }


    public String get(String key) {
        return cache.get(key);
    }


    public String set(String key, String value){
       return cache.put(key, value);
    }


    public boolean has(String key){
        return cache.containsKey(key);
    }


    public String delete(String key){
        return cache.remove(key);
    }
}
