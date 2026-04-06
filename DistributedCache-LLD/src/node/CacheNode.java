package node;

import java.util.HashMap;
import java.util.Map;
import policy.EvictionPolicy;

public class CacheNode<K, V> {
    private final int capacity;
    private final Map<K, V> storage;
    private final EvictionPolicy<K> evictionPolicy;

    public CacheNode(int capacity, EvictionPolicy<K> evictionPolicy) {
        this.capacity = capacity;
        this.storage = new HashMap<>();
        this.evictionPolicy = evictionPolicy;
    }

    public synchronized V get(K key) {
        if (!storage.containsKey(key)) {
            return null;
        }
        evictionPolicy.keyAccessed(key);
        return storage.get(key);
    }

    public synchronized void put(K key, V value) {
        if (storage.containsKey(key)) {
            storage.put(key, value);
            evictionPolicy.keyAccessed(key);
            return;
        }

        if (storage.size() >= capacity) {
            K keyToEvict = evictionPolicy.evictKey();
            if (keyToEvict != null) {
                storage.remove(keyToEvict);
                System.out.println("Node evicted key: " + keyToEvict);
            }
        }

        storage.put(key, value);
        evictionPolicy.keyAccessed(key);
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentSize() {
        return storage.size();
    }
}
