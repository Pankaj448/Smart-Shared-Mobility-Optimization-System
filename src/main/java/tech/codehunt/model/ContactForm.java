package tech.codehunt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
@Entity
@Table(name="contactform")

public class ContactForm {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private int id;

    @NotEmpty(message="Name cannot be Empty")
    @Size(min=2 , max=50, message="Invalid name size")
    @Column(length=30)
    private String name;

    @NotEmpty(message="Email cannot be Empty")
    @Email(message="Invalid Email")
    @Column(length=50)
    private String email;

    @NotEmpty(message="Phone number cannot be empty")
    @Size(min=10, max=10, message="Phone number must be 10 digits")
    @Pattern(regexp = "\\d{10}", message = "Phone number must contain only digits")
    @Column(length=10)
    private String phone;

    @NotEmpty(message="Message cannot be Empty")
    @Size(min=2 , max=500, message="Invalid message size")
    @Column(length=500)
    private String message;

    // --- getters & setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    @Override
    public String toString() {
        return "ContactForm [id=" + id + ", name=" + name + ", email=" + email + 
               ", phone=" + phone + ", message=" + message + "]";
    }
}
