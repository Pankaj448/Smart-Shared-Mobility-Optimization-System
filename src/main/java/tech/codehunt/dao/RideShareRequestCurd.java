package tech.codehunt.dao;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.codehunt.model.RideShareRequest;

@Repository
public interface RideShareRequestCurd extends JpaRepository<RideShareRequest, Integer> {
    long countByDateGreaterThanEqual(LocalDate date);

    @Query("select coalesce(sum(r.seats), 0) from RideShareRequest r")
    long sumSeats();
}
