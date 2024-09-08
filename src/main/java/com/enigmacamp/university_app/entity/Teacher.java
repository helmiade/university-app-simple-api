package com.enigmacamp.university_app.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "m_teacher")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "first_name", nullable = false)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must only contain letters")
    private String firstName;

    @Column(name = "last_name")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must only contain letters")
    private String lastName;

    @Column(unique = true, nullable = false)
    @Pattern(regexp = "\\d+", message = "NIP must contain only digits")
    private String nip;

    @Column
    private String address;

    @Column(name = "phone_number",unique = true)
    @Pattern(regexp = "\\d+", message = "Phone number must contain only digits")
    @Size(min = 11, max = 13)
    private String phoneNumber;

    @Column(unique = true)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{1,}$", message = "Invalid email address format")
    private String email;

}
