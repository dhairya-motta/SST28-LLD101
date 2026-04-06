package strategy;

public interface DistributionStrategy {
    int getNodeId(String key, int totalNodes);
}
