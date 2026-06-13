package tech.codehunt.service;

import tech.codehunt.model.DriverAssignment;

public interface DriverAssignmentService {
    DriverAssignment assignDriver(String preferredVehicleType);
}
