package com.enigmacamp.university_app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "m_student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
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
    private String nim;

    @Column
    private String address;

    @Column(name = "phone_number",unique = true)
    @Size(min = 11, max = 13)
    @Pattern(regexp = "\\d+", message = "Phone number must contain only digits")
    private String phoneNumber;

    @Column(unique = true)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{1,}$", message = "Invalid email address format")
    private String email;
}
