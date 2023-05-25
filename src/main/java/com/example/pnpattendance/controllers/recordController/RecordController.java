package com.example.pnpattendance.controllers.recordController;


import com.example.pnpattendance.models.Record;
import com.example.pnpattendance.request.DateRequestBody;
import com.example.pnpattendance.request.RecordRequestBody;
import com.example.pnpattendance.services.recordService.IRecordService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/record")
@CrossOrigin(origins = "http://localhost:3000")
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
    public Iterable getDailyRecords(
            @RequestParam( name = "year" ) int year,
            @RequestParam( name = "month" ) int month,
            @RequestParam( name = "day" ) int day
    ){

        DateRequestBody date = new DateRequestBody(year, month, day);

        return recordService.findAllByDate(date.toSqlDate());
    }

    @PostMapping
    public Record save(@RequestBody @NotNull RecordRequestBody record){
        record.setDate(Date.valueOf(LocalDate.now()));
        return recordService.save(record);
    }

    @GetMapping("/weekly")
    public Iterable getWeeklyRecords(
            @RequestParam( name = "year" ) int year,
            @RequestParam( name = "month" ) int month,
            @RequestParam( name = "day" ) int day
    ){
        DateRequestBody date = new DateRequestBody(year, month, day);
        date.setDay(date.getDay()-1);
        return recordService.getAllByWeek(date.toSqlDate(), date.getWeekLater());
    }

    @GetMapping("/annual")
    public List<Record> getAnnualRecords(
            @RequestParam(name = "year") int year
    ){
        return recordService.getAnnualRecords(year);
    }

    @GetMapping("/monthly")
    public List<Record> getMonthlyRecords(
            @RequestParam(name = "year" ) int year,
            @RequestParam(name = "month" ) int month
    ){
        return recordService.getMonthlyRecords(year, month);
    }
}
