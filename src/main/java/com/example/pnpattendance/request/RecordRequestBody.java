package com.example.pnpattendance.request;


import com.example.pnpattendance.models.Admin;
import com.example.pnpattendance.models.Officer;
import com.example.pnpattendance.models.Record;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class RecordRequestBody {
    private long adminId;
    private long badgeNumber;
    private Time time;
    private Date date;

    public RecordRequestBody(
            @JsonProperty("adminId") long adminId,
            @JsonProperty("badgeNumber") long badgeNumber,
            @JsonProperty("time") Time time,
            @JsonProperty("date") Date date){
        this.adminId = adminId;
        this.badgeNumber = badgeNumber;
        this.time = time;
        this.date = date;
    }

}
