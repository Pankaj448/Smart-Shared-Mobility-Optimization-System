package tech.codehunt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.codehunt.model.Driver;

@Repository
public interface DriverDao extends JpaRepository<Driver, Integer> {
    List<Driver> findByAvailableTrue();
}
