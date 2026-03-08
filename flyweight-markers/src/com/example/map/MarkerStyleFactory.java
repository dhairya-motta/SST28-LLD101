package com.example.map;

import java.util.HashMap;
import java.util.Map;

/**
 * MarkerStyleFactory - Flyweight factory that caches MarkerStyle instances.
 * Returns shared instances for identical style configurations.
 */
public class MarkerStyleFactory {

    private final Map<String, MarkerStyle> cache = new HashMap<>();

    public MarkerStyle get(String shape, String color, int size, boolean filled) {
        String key = shape + "|" + color + "|" + size + "|" + (filled ? "F" : "O");
        
        // Return cached instance if present
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        
        // Create new instance, cache it, and return
        MarkerStyle style = new MarkerStyle(shape, color, size, filled);
        cache.put(key, style);
        return style;
    }

    public int cacheSize() {
        return cache.size();
    }
}
