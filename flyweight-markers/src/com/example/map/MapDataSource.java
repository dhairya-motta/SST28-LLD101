package com.example.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * MapDataSource - Refactored to use MarkerStyleFactory (Flyweight pattern).
 * Obtains shared MarkerStyle instances from factory instead of creating new ones.
 */
public class MapDataSource {

    private static final String[] SHAPES = {"PIN", "CIRCLE", "SQUARE"};
    private static final String[] COLORS = {"RED", "BLUE", "GREEN", "ORANGE"};
    private static final int[] SIZES = {10, 12, 14, 16};

    // Shared factory for MarkerStyle instances
    private final MarkerStyleFactory styleFactory = new MarkerStyleFactory();

    public List<MapMarker> loadMarkers(int count) {
        Random rnd = new Random(7);
        List<MapMarker> out = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            double lat = 12.9000 + rnd.nextDouble() * 0.2000;
            double lng = 77.5000 + rnd.nextDouble() * 0.2000;
            String label = "M-" + i;

            // Force many duplicates by choosing from small pools
            String shape = SHAPES[rnd.nextInt(SHAPES.length)];
            String color = COLORS[rnd.nextInt(COLORS.length)];
            int size = SIZES[rnd.nextInt(SIZES.length)];
            boolean filled = rnd.nextBoolean();

            // Get shared MarkerStyle from factory (Flyweight)
            MarkerStyle style = styleFactory.get(shape, color, size, filled);
            out.add(new MapMarker(lat, lng, label, style));
        }
        
        // Print cache statistics
        System.out.println("Unique styles created: " + styleFactory.cacheSize());
        
        return out;
    }
}
