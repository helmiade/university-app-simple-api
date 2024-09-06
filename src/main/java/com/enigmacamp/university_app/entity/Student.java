package com.enigmacamp.university_app.entity;

import jakarta.persistence.*;
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
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true, nullable = false)
    private String nim;

    @Column
    private String address;

    @Column(name = "phone_number",unique = true, columnDefinition = "VARCHAR(50) CHECK (CHAR_LENGTH(phone_number) BETWEEN 11 AND 13)")
    private String phoneNumber;

    @Column(unique = true)
    private String email;
}
