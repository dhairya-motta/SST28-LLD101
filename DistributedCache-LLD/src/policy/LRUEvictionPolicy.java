package policy;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {
    private final LinkedList<K> list;
    private final Map<K, Boolean> exists;

    public LRUEvictionPolicy() {
        this.list = new LinkedList<>();
        this.exists = new HashMap<>();
    }

    @Override
    public void keyAccessed(K key) {
        if (exists.containsKey(key)) {
            list.remove(key); // Move to the front if it's already there
        }
        list.addFirst(key);
        exists.put(key, true);
    }

    @Override
    public K evictKey() {
        if (list.isEmpty()) {
            return null;
        }
        K keyToEvict = list.removeLast(); // Least recently used is at the end
        exists.remove(keyToEvict);
        return keyToEvict;
    }
}
