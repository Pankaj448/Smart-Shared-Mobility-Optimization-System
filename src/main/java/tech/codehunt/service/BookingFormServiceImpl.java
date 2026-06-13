package tech.codehunt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tech.codehunt.dao.BookingFormCurd;
import tech.codehunt.model.BookingForm;
import tech.codehunt.model.DriverAssignment;
@Service
public class BookingFormServiceImpl implements BookingFormService {
	private final BookingFormCurd bookingFormCurd;
	private final DriverAssignmentService driverAssignmentService;

	public BookingFormServiceImpl(BookingFormCurd bookingFormCurd,
								  DriverAssignmentService driverAssignmentService) {
		this.bookingFormCurd = bookingFormCurd;
		this.driverAssignmentService = driverAssignmentService;
	}

	@Override
	public BookingForm saveBookingFormService(BookingForm bookingForm) {
		DriverAssignment assignment = driverAssignmentService.assignDriver(bookingForm.getComfort());
		bookingForm.setAssignedDriverName(assignment.getDriverName());
		bookingForm.setAssignedDriverPhone(assignment.getDriverPhone());
		bookingForm.setAssignedVehicleNumber(assignment.getVehicleNumber());
		bookingForm.setAssignedVehicleType(assignment.getVehicleType());
	    return bookingFormCurd.save(bookingForm);
		
	}

	@Override
	public List<BookingForm> readAllBookingsService() {
		return bookingFormCurd.findAll();
	}

	@Override
	public void deleteBookingService(int id) {
		bookingFormCurd.deleteById(id);
	}

}
