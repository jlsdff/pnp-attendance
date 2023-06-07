package com.example.pnpattendance.request;

import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WholeRecordRequestBody {
    
    private long adminId;
    private long badgeNumber;
    private Time timeIn;
    private Time timeOut;
    private Date date;

    public WholeRecordRequestBody(
        @JsonProperty("adminId") long adminId,
        @JsonProperty("badgeNumber") long badgeNumber,
        @JsonProperty("timeIn") Time timeIn,
        @JsonProperty("timeOut") Time timeOut
    ){
        this.adminId = adminId;
        this.badgeNumber = badgeNumber;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    
}
