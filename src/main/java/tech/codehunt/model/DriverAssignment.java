package tech.codehunt.model;

public class DriverAssignment {
    private String driverName;
    private String driverPhone;
    private String vehicleNumber;
    private String vehicleType;

    public DriverAssignment(String driverName, String driverPhone, String vehicleNumber, String vehicleType) {
        this.driverName = driverName;
        this.driverPhone = driverPhone;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }
}
