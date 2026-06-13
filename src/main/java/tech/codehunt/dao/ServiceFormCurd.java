package tech.codehunt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.codehunt.model.ServiceForm;
@Repository
public interface ServiceFormCurd extends JpaRepository<ServiceForm, Integer> {
      
}
