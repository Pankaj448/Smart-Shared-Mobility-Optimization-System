package tech.codehunt.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.codehunt.model.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer> {
          Optional<Admin> findByUsername(String username);
}
