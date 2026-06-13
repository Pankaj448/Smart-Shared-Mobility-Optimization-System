package tech.codehunt.model;

public class DashboardStats {
    private long totalBookings;
    private long totalRideShares;
    private long totalContacts;
    private long totalServices;
    private long todayBookings;
    private long upcomingBookings;
    private long upcomingRideShares;
    private long totalSharedSeats;

    public long getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(long totalBookings) {
        this.totalBookings = totalBookings;
    }

    public long getTotalRideShares() {
        return totalRideShares;
    }

    public void setTotalRideShares(long totalRideShares) {
        this.totalRideShares = totalRideShares;
    }

    public long getTotalContacts() {
        return totalContacts;
    }

    public void setTotalContacts(long totalContacts) {
        this.totalContacts = totalContacts;
    }

    public long getTotalServices() {
        return totalServices;
    }

    public void setTotalServices(long totalServices) {
        this.totalServices = totalServices;
    }

    public long getTodayBookings() {
        return todayBookings;
    }

    public void setTodayBookings(long todayBookings) {
        this.todayBookings = todayBookings;
    }

    public long getUpcomingBookings() {
        return upcomingBookings;
    }

    public void setUpcomingBookings(long upcomingBookings) {
        this.upcomingBookings = upcomingBookings;
    }

    public long getUpcomingRideShares() {
        return upcomingRideShares;
    }

    public void setUpcomingRideShares(long upcomingRideShares) {
        this.upcomingRideShares = upcomingRideShares;
    }

    public long getTotalSharedSeats() {
        return totalSharedSeats;
    }

    public void setTotalSharedSeats(long totalSharedSeats) {
        this.totalSharedSeats = totalSharedSeats;
    }
}
