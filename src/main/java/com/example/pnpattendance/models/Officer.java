package com.example.pnpattendance.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Time;

@Entity
@Data
@Table(name = "tbl_officer")
public class Officer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_number")
    private long badgeNumber;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private  String middleName;
    @Column(name = "last_name")
    private  String lastName;
    private String ranks;
    @Column(name = "duty_on")
    private Time dutyOn;
    @Column(name = "duty_out")
    private Time dutyOut;


    public Officer(){
    }

    public Officer(
            @JsonProperty(value = "badge_number") long id,
            @JsonProperty(value = "firstName") String firstName,
            @JsonProperty(value = "middleName") String middleName,
            @JsonProperty(value = "lastName") String lastName,
            @JsonProperty(value = "dutyOn") Time dutyOn,
            @JsonProperty(value = "dutyOut") Time dutyOut
    ){
        this.badgeNumber = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dutyOn = dutyOn;
        this.dutyOut = dutyOut;
    }

}
