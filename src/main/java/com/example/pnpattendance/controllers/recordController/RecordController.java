package com.example.pnpattendance.controllers.recordController;


import com.example.pnpattendance.models.Record;
import com.example.pnpattendance.request.DateRequestBody;
import com.example.pnpattendance.request.RecordRequestBody;
import com.example.pnpattendance.request.WholeRecordRequestBody;
import com.example.pnpattendance.services.recordService.IRecordService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Record> findAll(){
        return recordService.findAll();
    }

    @GetMapping()
    public List<Record> getDailyRecords(
            @RequestParam( name = "year" ) int year,
            @RequestParam( name = "month" ) int month,
            @RequestParam( name = "day" ) int day
    ){

        DateRequestBody date = new DateRequestBody(year, month, day);

        return recordService.findAllByDate(date.toSqlDate());
    }

    @GetMapping(path = "/{id}")
    public List<Record> getByOfficerId(
            @PathVariable( name = "id" ) long id
    ){
        return recordService.findAllByOfficerId(id);
    }

    @PostMapping()
    public Record save(@RequestBody @NotNull RecordRequestBody record){
        System.out.println(record.toString());
        return recordService.save(record);
    }

    @PostMapping("/whole")
    public Record wholeSave(@RequestBody @NotNull WholeRecordRequestBody record){
        return recordService.wholeSave(record);
    }

    @GetMapping("/weekly")
    public List<Record> getWeeklyRecords(
            @RequestParam( name = "year" ) int year,
            @RequestParam( name = "month" ) int month,
            @RequestParam( name = "day" ) int day
    ){
        DateRequestBody date = DateRequestBody.of(year, month, day);
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
