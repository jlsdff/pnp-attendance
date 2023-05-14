package com.example.pnpattendance.services.recordService;

import com.example.pnpattendance.models.Record;

import java.sql.Date;
import java.util.List;

public interface IRecordService {
    public Iterable findAll();

    public Record save(Record record);

    public Iterable findAllByDate(Date date);

    public Iterable getAllByWeek(Date startDate, Date endDate);

    public List<Record> getAnnualRecords(int year);

    public List<Record> getAnnualRecordsByMonth(int year, int month);
}
