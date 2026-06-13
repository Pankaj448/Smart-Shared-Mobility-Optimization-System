package tech.codehunt.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.codehunt.model.BookingForm;
import tech.codehunt.model.ContactForm;

@Repository
public interface BookingFormCurd extends JpaRepository<BookingForm, Integer> {
         @Override
        public <S extends BookingForm> S save(S entity) ;
         @Override
     	public List<BookingForm> findAll();
     	@Override
     	public void deleteById(Integer id) ;

        long countByDate(LocalDate date);

        long countByDateGreaterThanEqual(LocalDate date);
        
}
