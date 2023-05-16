package com.example.pnpattendance.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class DateRequestBody {
    private int year;
    private int month;
    private int day;

    public DateRequestBody(
            @JsonProperty("year") int year,
            @JsonProperty("month") int month,
            @JsonProperty("day") int day) {
        this.month = month;
        this.year = year;
        this.day = day;
    }

    public Date toSqlDate(){
        return Date.valueOf(LocalDate.of(this.year, this.month, this.day));
    }
    public Date getWeekLater(){
        LocalDate weekLater = LocalDate.of(this.year, this.month, this.day).plusDays(7);
        return Date.valueOf(weekLater);
    }
}
