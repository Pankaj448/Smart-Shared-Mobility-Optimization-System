package tech.codehunt.service;

import java.util.List;

import tech.codehunt.model.RideShareRequest;

public interface RideShareRequestService {
    RideShareRequest saveRideShareRequest(RideShareRequest rideShareRequest);

    List<RideShareRequest> readAllRideShareRequests();

    void deleteRideShareRequest(int id);
}
