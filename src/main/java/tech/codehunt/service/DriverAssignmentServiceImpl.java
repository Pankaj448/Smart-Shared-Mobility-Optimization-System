package tech.codehunt.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import tech.codehunt.dao.DriverDao;
import tech.codehunt.model.Driver;
import tech.codehunt.model.DriverAssignment;

@Service
public class DriverAssignmentServiceImpl implements DriverAssignmentService {
    private final DriverDao driverDao;

    public DriverAssignmentServiceImpl(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    @PostConstruct
    public void seedDrivers() {
        if (driverDao.count() == 0) {
            driverDao.save(new Driver("Amit Kumar", "9876500011", "HR20TX1001", "Standard", true));
            driverDao.save(new Driver("Rohit Sharma", "9876500022", "HR20TX1002", "Cheap", true));
            driverDao.save(new Driver("Sunil Verma", "9876500033", "HR20TX1003", "Lux", true));
            driverDao.save(new Driver("Deepak Singh", "9876500044", "HR20TX1004", "Ride Share", true));
        }
    }

    @Transactional
    @Override
    public DriverAssignment assignDriver(String preferredVehicleType) {
        List<Driver> availableDrivers = driverDao.findByAvailableTrue();
        if (availableDrivers.isEmpty()) {
            List<Driver> allDrivers = driverDao.findAll();
            allDrivers.forEach(driver -> driver.setAvailable(true));
            driverDao.saveAll(allDrivers);
            availableDrivers = driverDao.findByAvailableTrue();
        }

        List<Driver> candidateDrivers = availableDrivers;
        Driver selectedDriver = candidateDrivers.stream()
                .filter(driver -> driver.getVehicleType().equalsIgnoreCase(preferredVehicleType))
                .findFirst()
                .orElseGet(() -> candidateDrivers.stream()
                        .min(Comparator.comparingInt(Driver::getId))
                        .orElseThrow());

        selectedDriver.setAvailable(false);
        driverDao.save(selectedDriver);

        return new DriverAssignment(
                selectedDriver.getName(),
                selectedDriver.getPhone(),
                selectedDriver.getVehicleNumber(),
                selectedDriver.getVehicleType()
        );
    }
}
