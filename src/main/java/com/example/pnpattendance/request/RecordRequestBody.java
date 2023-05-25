package com.example.pnpattendance.request;


import com.example.pnpattendance.models.Admin;
import com.example.pnpattendance.models.Officer;
import com.example.pnpattendance.models.Record;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

@Data
public class RecordRequestBody {
    private long adminId;
    private long badgeNumber;
    private Time time;
    private Date date;

    public RecordRequestBody(
            @JsonProperty("adminId") long adminId,
            @JsonProperty("badgeNumber") long badgeNumber,
            @JsonProperty("time") Time time){
        this.adminId = adminId;
        this.badgeNumber = badgeNumber;
        this.time = time;
        this.date = Date.valueOf(LocalDate.now());
    }

}
