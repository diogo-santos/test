package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapInsertionOrder {
    private final List<String> keys;
    private final Map<String, Object> map;

    public HashMapInsertionOrder() {
        keys = new ArrayList<>();
        map = new HashMap<>();
    }

    public void put(String key, Object value) {
        int index = keys.indexOf(key);
        if (index != -1) {
            keys.set(index, key);
        } else {
            keys.add(key);
        }
        map.put(key, value);
    }

    public Object get(String key) {
        return map.get(key);
    }

    public Object getAt(int index) {
        String key = keys.get(index);
        return map.get(key);
    }

    public void delete(String key) {
        keys.removeIf(k -> k.equals(key));
        map.remove(key);
    }

    public static void main(String[] args) {
        HashMapInsertionOrder map = new HashMapInsertionOrder();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");

        assert map.get("k1").equals("v1");
        assert map.get("k2").equals("v2");
        assert map.get("k3").equals("v3");
        assert map.getAt(0).equals("v1");
        assert map.getAt(1).equals("v2");
        assert map.getAt(2).equals("v3");

        map.delete("k2");
        assert map.get("k2") == null;
        assert map.getAt(1).equals("v3");

        map.put("k3", "v3.1");
        assert map.getAt(1).equals("v3.1");

        System.out.println(map.map);
    }
}
