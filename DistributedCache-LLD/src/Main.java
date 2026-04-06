import cache.DistributedCache;
import db.Database;
import strategy.ModuloDistributionStrategy;

public class Main {
    public static void main(String[] args) {
        // Setup database
        Database<String, String> database = new Database<>();
        database.put("user:1", "John Doe");
        database.put("user:2", "Jane Smith");
        database.put("user:3", "Alice Bob");
        database.put("user:4", "Charlie Brown");

        // Initialize Distributed Cache
        // 3 nodes, each with capacity 2
        int numberOfNodes = 3;
        int nodeCapacity = 2;
        DistributedCache<String, String> cache = new DistributedCache<>(
                numberOfNodes, 
                nodeCapacity, 
                new ModuloDistributionStrategy(), 
                database
        );

        System.out.println("--- GET operations (Cache Miss initially) ---");
        System.out.println("Result: " + cache.get("user:1"));
        System.out.println("Result: " + cache.get("user:2"));
        System.out.println("Result: " + cache.get("user:3"));

        System.out.println("\n--- GET operations (Cache Hit) ---");
        System.out.println("Result: " + cache.get("user:1"));
        System.out.println("Result: " + cache.get("user:2"));

        System.out.println("\n--- Testing Eviction (Node Capacity is 2) ---");
        // Forcing multiple items into the same node (or different) to see eviction
        // In theory, with modulo, we need to pick keys that map to same node if we want to see LRU in action.
        cache.put("user:4", "Charlie Brown");
        cache.put("user:5", "David G.");
        cache.get("user:6"); // Fetch from DB

        System.out.println("\nCache System Demonstration Complete.");
    }
}
