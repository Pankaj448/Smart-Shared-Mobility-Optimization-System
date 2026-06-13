package tech.codehunt.model;

import java.util.List;

public class ShortestPathResult {
    private String source;
    private String destination;
    private List<String> path;
    private int totalDistanceKm;
    private int estimatedTimeMinutes;

    public ShortestPathResult(String source, String destination, List<String> path, int totalDistanceKm, int estimatedTimeMinutes) {
        this.source = source;
        this.destination = destination;
        this.path = path;
        this.totalDistanceKm = totalDistanceKm;
        this.estimatedTimeMinutes = estimatedTimeMinutes;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public int getTotalDistanceKm() {
        return totalDistanceKm;
    }

    public void setTotalDistanceKm(int totalDistanceKm) {
        this.totalDistanceKm = totalDistanceKm;
    }

    public int getEstimatedTimeMinutes() {
        return estimatedTimeMinutes;
    }

    public void setEstimatedTimeMinutes(int estimatedTimeMinutes) {
        this.estimatedTimeMinutes = estimatedTimeMinutes;
    }
}
