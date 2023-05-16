package com.example.pnpattendance.services.recordService;

import com.example.pnpattendance.models.Record;
import com.example.pnpattendance.request.RecordRequestBody;

import java.sql.Date;
import java.util.List;

public interface IRecordService {
    public Iterable findAll();

    public Record save(RecordRequestBody record);

    public Iterable findAllByDate(Date date);

    public Iterable getAllByWeek(Date startDate, Date endDate);

    public List<Record> getAnnualRecords(int year);

    public List<Record> getMonthlyRecords(int year, int month);
}
