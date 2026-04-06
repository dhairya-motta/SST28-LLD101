package cache;

import java.util.ArrayList;
import java.util.List;
import node.CacheNode;
import strategy.DistributionStrategy;
import db.Database;

public class DistributedCache<K, V> {
    private final List<CacheNode<K, V>> nodes;
    private final DistributionStrategy distributionStrategy;
    private final Database<K, V> database;

    public DistributedCache(int numberOfNodes, int nodeCapacity, DistributionStrategy distributionStrategy, Database<K, V> database) {
        this.nodes = new ArrayList<>();
        this.distributionStrategy = distributionStrategy;
        this.database = database;
        // All nodes initialized with same eviction policy type (LRU for now)
        for (int i = 0; i < numberOfNodes; i++) {
            this.nodes.add(new CacheNode<>(nodeCapacity, new policy.LRUEvictionPolicy<>()));
        }
    }

    public V get(K key) {
        int nodeId = distributionStrategy.getNodeId(key.toString(), nodes.size());
        CacheNode<K, V> node = nodes.get(nodeId);
        
        V value = node.get(key);
        if (value != null) {
            System.out.println("Key " + key + " found in Cache Node " + nodeId);
            return value;
        }

        System.out.println("Key " + key + " not found in Cache Node " + nodeId);
        value = database.get(key);
        if (value != null) {
            node.put(key, value);
            System.out.println("Key " + key + " fetched from DB and stored in Cache Node " + nodeId);
        }
        
        return value;
    }

    public void put(K key, V value) {
        int nodeId = distributionStrategy.getNodeId(key.toString(), nodes.size());
        CacheNode<K, V> node = nodes.get(nodeId);
        
        node.put(key, value);
        System.out.println("Key " + key + " stored in Cache Node " + nodeId);
        // Assuming database is also updated
        database.put(key, value);
    }
}
