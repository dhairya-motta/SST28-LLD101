package strategy;

import config.RateLimitConfig;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowStrategy implements RateLimiterStrategy {
    private static class Window {
        final AtomicInteger count = new AtomicInteger(0);
        final long startTime;

        Window(long startTime) {
            this.startTime = startTime;
        }
    }

    private final Map<String, Window> windows = new ConcurrentHashMap<>();

    @Override
    public boolean allowRequest(String key, RateLimitConfig config) {
        long currentTime = System.currentTimeMillis();
        long currentWindowStartTime = (currentTime / config.getWindowSizeInMillis()) * config.getWindowSizeInMillis();

        Window window = windows.compute(key, (k, existing) -> {
            if (existing == null || existing.startTime < currentWindowStartTime) {
                return new Window(currentWindowStartTime);
            }
            return existing;
        });

        if (window.count.get() < config.getMaxRequests()) {
            window.count.incrementAndGet();
            return true;
        }

        return false;
    }
}
