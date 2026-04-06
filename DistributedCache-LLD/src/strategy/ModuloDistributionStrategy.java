package strategy;

public class ModuloDistributionStrategy implements DistributionStrategy {
    @Override
    public int getNodeId(String key, int totalNodes) {
        // Handle negative hash values
        int hash = key.hashCode();
        return Math.abs(hash) % totalNodes;
    }
}
