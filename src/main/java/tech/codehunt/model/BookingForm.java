package tech.codehunt.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
@Entity
@Table(name="bookingform")
public class BookingForm {
   @Id
   @GeneratedValue(strategy =GenerationType.IDENTITY )
    private int id;

    @NotEmpty(message = "email can't be empty")
    @NotBlank(message="email can't be blank")
    @Email(message = "invalid email")
    @Size(min=5,max=100, message="invalid email length")
    @Column(length=100)
    private String email;

    @NotEmpty(message = "destination can't be empty")
    @NotBlank(message="destination can't be blank")
    @Size(min=2,max=100, message="invalid source length")
    @Column(length=100)
    private String destination;

    @NotEmpty(message = "source can't be empty")
    @NotBlank(message="source can't be blank")
    @Size(min=2,max=100, message="invalid destination length")
    @Column(length=100)
    private String source;

    @NotNull(message = "time can't be empty")
    private LocalTime time;

    @NotNull(message = "date can't be empty")
    private LocalDate date;

    @NotEmpty(message = "comfort can't be empty")
    @Column(length=20)
    
    private String comfort;

    @Min(value = 1, message = "adult count must be at least 1")
    @Max(value = 3, message = "adult count can be at most 3")
    private int adult;

    @Max(value = 2, message = "children count can be at most 2")
    private int children;

    @NotEmpty(message = "message can't be empty")
    @NotBlank(message="message can't be blank")
    @Size(min=10,max=1000, message="invalid message length")
    @Column(length=1000)
    private String message;

    @NotEmpty(message = "name can't be empty")
    @NotBlank(message="name can't be blank")
    @Size(min=2,max=100, message="invalid name length")
    @Pattern(regexp="[A-Za-z ]+$",message="name must contain only alphabet")
    @Column(length=100)
    private String name;

    @Column(length = 100)
    private String assignedDriverName;

    @Column(length = 10)
    private String assignedDriverPhone;

    @Column(length = 30)
    private String assignedVehicleNumber;

    @Column(length = 30)
    private String assignedVehicleType;

    // No-args constructor
    public BookingForm() {
    }

    // All-args constructor
    public BookingForm(int id, String email, String destination, String source, LocalTime time, LocalDate date,
                       String comfort, int adult, int children, String message, String name) {
        this.id = id;
        this.email = email;
        this.destination = destination;
        this.source = source;
        this.time = time;
        this.date = date;
        this.comfort = comfort;
        this.adult = adult;
        this.children = children;
        this.message = message;
        this.name = name;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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


    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComfort() {
        return comfort;
    }

    public void setComfort(String comfort) {
        this.comfort = comfort;
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    // toString()
    @Override
    public String toString() {
        return "BookingForm{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", destination='" + destination + '\'' +
                ", source='" + source + '\'' +
                ", time=" + time +
                ", date=" + date +
                ", comfort='" + comfort + '\'' +
                ", adult=" + adult +
                ", children=" + children +
                ", message='" + message + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
