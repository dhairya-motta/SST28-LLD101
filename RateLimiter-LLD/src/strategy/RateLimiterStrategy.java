package strategy;

import config.RateLimitConfig;

public interface RateLimiterStrategy {
    boolean allowRequest(String key, RateLimitConfig config);
}
