package tech.codehunt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tech.codehunt.dao.RideShareRequestCurd;
import tech.codehunt.model.DriverAssignment;
import tech.codehunt.model.RideShareRequest;

@Service
public class RideShareRequestServiceImpl implements RideShareRequestService {
    private final RideShareRequestCurd rideShareRequestCurd;
    private final DriverAssignmentService driverAssignmentService;

    public RideShareRequestServiceImpl(RideShareRequestCurd rideShareRequestCurd,
                                       DriverAssignmentService driverAssignmentService) {
        this.rideShareRequestCurd = rideShareRequestCurd;
        this.driverAssignmentService = driverAssignmentService;
    }

    @Override
    public RideShareRequest saveRideShareRequest(RideShareRequest rideShareRequest) {
        DriverAssignment assignment = driverAssignmentService.assignDriver("Ride Share");
        rideShareRequest.setAssignedDriverName(assignment.getDriverName());
        rideShareRequest.setAssignedDriverPhone(assignment.getDriverPhone());
        rideShareRequest.setAssignedVehicleNumber(assignment.getVehicleNumber());
        rideShareRequest.setAssignedVehicleType(assignment.getVehicleType());
        return rideShareRequestCurd.save(rideShareRequest);
    }

    @Override
    public List<RideShareRequest> readAllRideShareRequests() {
        return rideShareRequestCurd.findAll();
    }

    @Override
    public void deleteRideShareRequest(int id) {
        rideShareRequestCurd.deleteById(id);
    }
}
