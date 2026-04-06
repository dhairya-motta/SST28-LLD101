package db;

import java.util.HashMap;
import java.util.Map;

public class Database<K, V> {
    private final Map<K, V> storage;

    public Database() {
        this.storage = new HashMap<>();
    }

    public void put(K key, V value) {
        storage.put(key, value);
    }

    public V get(K key) {
        System.out.println("Fetching key " + key + " from database...");
        return storage.get(key);
    }
}
