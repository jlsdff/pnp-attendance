package com.example.pnpattendance.controllers.recordController;


import com.example.pnpattendance.models.Record;
import com.example.pnpattendance.request.ModDate;
import com.example.pnpattendance.services.recordService.IRecordService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

@RestController
@RequestMapping("/api/v1/record")
public class RecordController {

    @Autowired
    private final IRecordService recordService;

    public RecordController(IRecordService recordService){
        this.recordService = recordService;
    }

    @GetMapping("/all")
    public Iterable findAll(){
        return recordService.findAll();
    }

    @GetMapping()
    public Iterable getDailyRecord(@RequestBody @NotNull ModDate date){
        return recordService.findAllByDate(date.toSqlDate());
    }

    @PostMapping
    public Record save(@RequestBody Record record){
        return recordService.save(record);
    }

    @GetMapping("/weekly")
    public Iterable getWeeklyRecords(@RequestBody @NotNull ModDate date){
        date.setDay(date.getDay()-1);
        return recordService.getAllByWeek(date.toSqlDate(), date.getWeekLater());
    }

    @GetMapping("/annual")
    public List<Record> getAnnualRecords(@RequestBody @NotNull ModDate date){
        return recordService.getAnnualRecords(date.getYear());
    }

    @GetMapping("/monthly")
    public List<Record> getMonthlyRecords(@RequestBody @NotNull ModDate date){
        return recordService.getAnnualRecordsByMonth(date.getYear(), date.getMonth());
    }
}
