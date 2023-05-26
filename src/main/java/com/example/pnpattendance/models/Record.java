package com.example.pnpattendance.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Entity
@Data
@Table(name="tbl_record")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private long recordId;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "officer_id")
    private Officer officer;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Column(name = "time_in")
    private Time timeIn;

    @Column(name = "time_out")
    private Time timeOut;

    @Column(name = "date")
    private Date date;

    @Column(name="late")
    private boolean isLate;

    @Column(name="undertime")
    private boolean isUndertime;

    @Column(name="overtime")
    private boolean isOvertime;

    public Record(){}

    public Record(
            Admin admin,
            Officer officer,
            Time timeIn,
            Time timeOut,
            Date date,
            boolean isLate,
            boolean isUndertime,
            boolean isOvertime
    ){
        this.admin = admin;
        this.officer = officer;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.date = date;
        this.isLate = isLate;
        this.isUndertime = isUndertime;
        this.isOvertime = isOvertime;
    }

}
