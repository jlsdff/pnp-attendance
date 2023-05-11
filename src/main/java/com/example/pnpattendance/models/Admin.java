package com.example.pnpattendance.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "tbl_admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long admin_id;

    @NotBlank(message = "Email is required")
    @Column(name="email", unique = true)
    private String email;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    @NotBlank
    private String lastName;

    @Column(name = "password")
    @NotBlank
    private String password;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    public Admin(@JsonProperty("firstName") String firstName,
                 @JsonProperty("middleName") String middleName,
                 @JsonProperty("lastName") String lastName,
                 @JsonProperty("email") String email,
                 @JsonProperty("password")String password) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Admin() {
    }
}




