package tech.codehunt.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.stereotype.Service;

import tech.codehunt.model.ShortestPathResult;

@Service
public class ShortestPathServiceImpl implements ShortestPathService {
    private static final int AVERAGE_CITY_SPEED_KMPH = 35;
    private final Map<String, Map<String, Integer>> graph = new LinkedHashMap<>();

    public ShortestPathServiceImpl() {
        addRoad("Bus Stand", "Railway Station", 3);
        addRoad("Bus Stand", "GJU University", 6);
        addRoad("Bus Stand", "City Hospital", 4);
        addRoad("Railway Station", "City Center", 2);
        addRoad("Railway Station", "Airport Road", 8);
        addRoad("City Center", "GJU University", 5);
        addRoad("City Center", "Model Town", 3);
        addRoad("Model Town", "Airport Road", 5);
        addRoad("Model Town", "City Hospital", 2);
        addRoad("GJU University", "Industrial Area", 7);
        addRoad("Industrial Area", "Airport Road", 4);
        addRoad("City Hospital", "Industrial Area", 6);
    }

    @Override
    public List<String> getLocations() {
        return new ArrayList<>(graph.keySet());
    }

    @Override
    public ShortestPathResult findShortestPath(String source, String destination) {
        if (!graph.containsKey(source) || !graph.containsKey(destination)) {
            throw new IllegalArgumentException("Please select valid source and destination");
        }

        if (source.equals(destination)) {
            return new ShortestPathResult(source, destination, List.of(source), 0, 0);
        }

        Map<String, Integer> distance = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<PathNode> queue = new PriorityQueue<>();

        for (String location : graph.keySet()) {
            distance.put(location, Integer.MAX_VALUE);
        }

        distance.put(source, 0);
        queue.add(new PathNode(source, 0));

        while (!queue.isEmpty()) {
            PathNode current = queue.poll();
            if (current.distance() > distance.get(current.location())) {
                continue;
            }

            if (current.location().equals(destination)) {
                break;
            }

            for (Map.Entry<String, Integer> road : graph.get(current.location()).entrySet()) {
                int newDistance = current.distance() + road.getValue();
                if (newDistance < distance.get(road.getKey())) {
                    distance.put(road.getKey(), newDistance);
                    previous.put(road.getKey(), current.location());
                    queue.add(new PathNode(road.getKey(), newDistance));
                }
            }
        }

        List<String> path = buildPath(source, destination, previous);
        int totalDistance = distance.get(destination);
        int estimatedMinutes = (int) Math.ceil((totalDistance * 60.0) / AVERAGE_CITY_SPEED_KMPH);

        return new ShortestPathResult(source, destination, path, totalDistance, estimatedMinutes);
    }

    private void addRoad(String firstLocation, String secondLocation, int distanceKm) {
        graph.computeIfAbsent(firstLocation, key -> new LinkedHashMap<>()).put(secondLocation, distanceKm);
        graph.computeIfAbsent(secondLocation, key -> new LinkedHashMap<>()).put(firstLocation, distanceKm);
    }

    private List<String> buildPath(String source, String destination, Map<String, String> previous) {
        List<String> path = new ArrayList<>();
        String current = destination;

        while (current != null) {
            path.add(current);
            if (current.equals(source)) {
                break;
            }
            current = previous.get(current);
        }

        Collections.reverse(path);
        return path;
    }

    private record PathNode(String location, int distance) implements Comparable<PathNode> {
        @Override
        public int compareTo(PathNode other) {
            return Integer.compare(distance, other.distance);
        }
    }
}
