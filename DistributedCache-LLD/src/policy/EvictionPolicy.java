package policy;

public interface EvictionPolicy<K> {
    void keyAccessed(K key);
    K evictKey();
}
