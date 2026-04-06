package manager;

import config.RateLimitConfig;
import strategy.RateLimiterStrategy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterManager {
    private RateLimiterStrategy strategy;
    private final Map<String, RateLimitConfig> keyConfigs = new ConcurrentHashMap<>();

    public RateLimiterManager(RateLimiterStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(RateLimiterStrategy strategy) {
        this.strategy = strategy;
    }

    public void setConfigForKey(String key, RateLimitConfig config) {
        keyConfigs.put(key, config);
    }

    public boolean allowRequest(String key) {
        RateLimitConfig config = keyConfigs.get(key);
        if (config == null) {
            System.err.println("No config found for key: " + key + ". Request blocked.");
            return false;
        }
        return strategy.allowRequest(key, config);
    }
}
