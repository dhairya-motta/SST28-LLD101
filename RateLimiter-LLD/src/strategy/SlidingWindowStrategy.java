package strategy;

import config.RateLimitConfig;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindowStrategy implements RateLimiterStrategy {
    private final Map<String, Queue<Long>> requestLogs = new ConcurrentHashMap<>();

    @Override
    public boolean allowRequest(String key, RateLimitConfig config) {
        long currentTime = System.currentTimeMillis();
        long windowStart = currentTime - config.getWindowSizeInMillis();

        Queue<Long> keyLogs = requestLogs.computeIfAbsent(key, k -> new ConcurrentLinkedQueue<>());

        // Remove outdated request timestamps
        while (!keyLogs.isEmpty() && keyLogs.peek() < windowStart) {
            keyLogs.poll();
        }

        synchronized (keyLogs) {
            if (keyLogs.size() < config.getMaxRequests()) {
                keyLogs.add(currentTime);
                return true;
            }
        }

        return false;
    }
}
