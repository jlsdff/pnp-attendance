package com.example.pnpattendance.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "tbl_admin")
public class Admin implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long admin_id;


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
    private String password;

    @Column(name = "timestamp")
    @CreatedDate
    private Timestamp timestamp;

    public Admin(
                 @JsonProperty("admin_id") long admin_id,
                 @JsonProperty("firstName") String firstName,
                 @JsonProperty("middleName") String middleName,
                 @JsonProperty("lastName") String lastName,
                 @JsonProperty("email") String email,
                 @JsonProperty("password")String password) {
        this.admin_id = admin_id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Admin() {
    }
}




