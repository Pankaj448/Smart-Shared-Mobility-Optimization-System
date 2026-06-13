package tech.codehunt.service;

import java.util.List;

import tech.codehunt.model.ShortestPathResult;

public interface ShortestPathService {
    List<String> getLocations();

    ShortestPathResult findShortestPath(String source, String destination);
}
