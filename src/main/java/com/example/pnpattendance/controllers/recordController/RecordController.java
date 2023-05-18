package com.example.pnpattendance.controllers.recordController;


import com.example.pnpattendance.models.Record;
import com.example.pnpattendance.request.DateRequestBody;
import com.example.pnpattendance.request.RecordRequestBody;
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
    public Iterable findAll(){
        return recordService.findAll();
    }

    @GetMapping()
    public Iterable getDailyRecords(@RequestBody @NotNull DateRequestBody date){
        return recordService.findAllByDate(date.toSqlDate());
    }

    @PostMapping
    public Record save(@RequestBody @NotNull RecordRequestBody record){
        return recordService.save(record);
    }

    @GetMapping("/weekly")
    public Iterable getWeeklyRecords(@RequestBody @NotNull DateRequestBody date){
        date.setDay(date.getDay()-1);
        return recordService.getAllByWeek(date.toSqlDate(), date.getWeekLater());
    }

    @GetMapping("/annual")
    public List<Record> getAnnualRecords(@RequestBody @NotNull DateRequestBody date){
        return recordService.getAnnualRecords(date.getYear());
    }

    @GetMapping("/monthly")
    public List<Record> getMonthlyRecords(@RequestBody @NotNull DateRequestBody date){
        return recordService.getMonthlyRecords(date.getYear(), date.getMonth());
    }
}
