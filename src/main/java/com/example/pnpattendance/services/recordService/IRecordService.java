package com.example.pnpattendance.services.recordService;

import com.example.pnpattendance.models.Record;
import com.example.pnpattendance.request.RecordRequestBody;
import com.example.pnpattendance.request.WholeRecordRequestBody;

import java.sql.Date;
import java.util.List;

public interface IRecordService {
    public List<Record> findAll();

    public Record save(RecordRequestBody record);

    public Record wholeSave(WholeRecordRequestBody record);

    public List<Record> findAllByDate(Date date);

    public List<Record> getAllByWeek(Date startDate, Date endDate);

    public List<Record> getAnnualRecords(int year);

    public List<Record> getMonthlyRecords(int year, int month);

    public List<Record> findAllByOfficerId(long id);
}
