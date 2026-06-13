package tech.codehunt.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import tech.codehunt.dao.BookingFormCurd;
import tech.codehunt.dao.ContactFormCurd;
import tech.codehunt.dao.RideShareRequestCurd;
import tech.codehunt.dao.ServiceFormCurd;
import tech.codehunt.model.DashboardStats;

@Service
public class DashboardStatsServiceImpl implements DashboardStatsService {
    private final BookingFormCurd bookingFormCurd;
    private final RideShareRequestCurd rideShareRequestCurd;
    private final ContactFormCurd contactFormCurd;
    private final ServiceFormCurd serviceFormCurd;

    public DashboardStatsServiceImpl(BookingFormCurd bookingFormCurd,
                                     RideShareRequestCurd rideShareRequestCurd,
                                     ContactFormCurd contactFormCurd,
                                     ServiceFormCurd serviceFormCurd) {
        this.bookingFormCurd = bookingFormCurd;
        this.rideShareRequestCurd = rideShareRequestCurd;
        this.contactFormCurd = contactFormCurd;
        this.serviceFormCurd = serviceFormCurd;
    }

    @Override
    public DashboardStats getDashboardStats() {
        LocalDate today = LocalDate.now();

        DashboardStats stats = new DashboardStats();
        stats.setTotalBookings(bookingFormCurd.count());
        stats.setTotalRideShares(rideShareRequestCurd.count());
        stats.setTotalContacts(contactFormCurd.count());
        stats.setTotalServices(serviceFormCurd.count());
        stats.setTodayBookings(bookingFormCurd.countByDate(today));
        stats.setUpcomingBookings(bookingFormCurd.countByDateGreaterThanEqual(today));
        stats.setUpcomingRideShares(rideShareRequestCurd.countByDateGreaterThanEqual(today));
        stats.setTotalSharedSeats(rideShareRequestCurd.sumSeats());

        return stats;
    }
}
