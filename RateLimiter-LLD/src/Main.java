import manager.RateLimiterManager;
import config.RateLimitConfig;
import strategy.FixedWindowStrategy;
import strategy.SlidingWindowStrategy;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Setup Rate Limiter with Fixed Window initially
        RateLimiterManager rateLimiter = new RateLimiterManager(new FixedWindowStrategy());

        // Configure a limit: 3 requests per 2 seconds
        RateLimitConfig customerConfig = new RateLimitConfig(3, 2000);
        rateLimiter.setConfigForKey("customer:1", customerConfig);

        System.out.println("--- Testing Fixed Window (3 reqs / 2 sec) ---");
        System.out.println("Attempt 1: " + (rateLimiter.allowRequest("customer:1") ? "Allowed" : "Blocked"));
        System.out.println("Attempt 2: " + (rateLimiter.allowRequest("customer:1") ? "Allowed" : "Blocked"));
        System.out.println("Attempt 3: " + (rateLimiter.allowRequest("customer:1") ? "Allowed" : "Blocked"));
        System.out.println("Attempt 4 (Should be blocked): " + (rateLimiter.allowRequest("customer:1") ? "Allowed" : "Blocked"));

        System.out.println("\nWaiting for 2 seconds to reset window...");
        Thread.sleep(2100);
        System.out.println("Attempt 5 (Should be allowed): " + (rateLimiter.allowRequest("customer:1") ? "Allowed" : "Blocked"));

        System.out.println("\n--- Switching to Sliding Window ---");
        rateLimiter.setStrategy(new SlidingWindowStrategy());
        // Configure new limit: 2 requests per 1 second
        RateLimitConfig slidingConfig = new RateLimitConfig(2, 1000);
        rateLimiter.setConfigForKey("user:A", slidingConfig);

        System.out.println("Attempt 1 for user:A: " + (rateLimiter.allowRequest("user:A") ? "Allowed" : "Blocked"));
        Thread.sleep(600);
        System.out.println("Attempt 2 for user:A: " + (rateLimiter.allowRequest("user:A") ? "Allowed" : "Blocked"));
        Thread.sleep(500); // Window has moved past attempt 1
        System.out.println("Attempt 3 for user:A (Should be allowed by sliding window): " + (rateLimiter.allowRequest("user:A") ? "Allowed" : "Blocked"));
        System.out.println("Attempt 4 for user:A (Should be blocked): " + (rateLimiter.allowRequest("user:A") ? "Allowed" : "Blocked"));

        System.out.println("\nRate Limiter System Demonstration Complete.");
    }
}
