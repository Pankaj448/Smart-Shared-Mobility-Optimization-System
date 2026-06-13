package tech.codehunt.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ride_share_request")
public class RideShareRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Invalid name length")
    @Pattern(regexp = "[A-Za-z ]+$", message = "Name must contain only alphabet")
    @Column(length = 100, nullable = false)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    @Column(length = 100, nullable = false)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    @Column(length = 10, nullable = false)
    private String phone;

    @NotBlank(message = "Pickup location is required")
    @Size(min = 2, max = 100, message = "Invalid pickup location length")
    @Column(length = 100, nullable = false)
    private String source;

    @NotBlank(message = "Drop location is required")
    @Size(min = 2, max = 100, message = "Invalid drop location length")
    @Column(length = 100, nullable = false)
    private String destination;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Time is required")
    private LocalTime time;

    @Min(value = 1, message = "Seats must be at least 1")
    @Max(value = 6, message = "Seats can be at most 6")
    private int seats;

    @Size(max = 500, message = "Notes can be at most 500 characters")
    @Column(length = 500)
    private String notes;

    private LocalDateTime createdAt;

    @Column(length = 100)
    private String assignedDriverName;

    @Column(length = 10)
    private String assignedDriverPhone;

    @Column(length = 30)
    private String assignedVehicleNumber;

    @Column(length = 30)
    private String assignedVehicleType;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getAssignedDriverName() {
        return assignedDriverName;
    }

    public void setAssignedDriverName(String assignedDriverName) {
        this.assignedDriverName = assignedDriverName;
    }

    public String getAssignedDriverPhone() {
        return assignedDriverPhone;
    }

    public void setAssignedDriverPhone(String assignedDriverPhone) {
        this.assignedDriverPhone = assignedDriverPhone;
    }

    public String getAssignedVehicleNumber() {
        return assignedVehicleNumber;
    }

    public void setAssignedVehicleNumber(String assignedVehicleNumber) {
        this.assignedVehicleNumber = assignedVehicleNumber;
    }

    public String getAssignedVehicleType() {
        return assignedVehicleType;
    }

    public void setAssignedVehicleType(String assignedVehicleType) {
        this.assignedVehicleType = assignedVehicleType;
    }
}
