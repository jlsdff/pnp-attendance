package com.example.pnpattendance.services.recordService;


import com.example.pnpattendance.models.Officer;
import com.example.pnpattendance.models.Record;
import com.example.pnpattendance.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordService implements IRecordService{

    @Autowired
    private final RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository){
        this.recordRepository = recordRepository;
    }

    @Override
    public Iterable<Record> findAll(){
        Iterable<Record> records = recordRepository.findAll(Sort.by(Sort.Direction.DESC, "recordId"));
        return records;
    }

    @Override
    public Record save(Record record){

        long badgeNumber = record.getOfficer().getBadgeNumber();
        Date date = record.getDate();

        // Check if record already exists
        Record existingRecord = recordRepository
                .findRecordByOfficer_BadgeNumberAndDate(badgeNumber, date);

        // If record exists, update the time-out
        if(existingRecord != null){
            existingRecord.setTimeOut(record.getTimeOut());
            return recordRepository.save(existingRecord);
        }

        return recordRepository.save(record);
    }

    @Override
    public Iterable findAllByDate(Date date) {
        Iterable<Record> records = recordRepository.findAllByDate(date);
        return records;
    }

    @Override
    public Iterable getAllByWeek(Date startDate, Date endDate) {
        return recordRepository.findAllByDateAfterAndDateBefore(startDate, endDate);
    }

    @Override
    public List<Record> getAnnualRecords(int year) {
        List<Record> records = recordRepository
                .findAll()
                .stream()
                .filter(record -> record.getDate().toLocalDate().getYear() == year)
                .toList();
        return records;
    }

    @Override
    public List<Record> getAnnualRecordsByMonth(int year, int month) {

        List<Record> records = recordRepository
                .findAll()
                .stream()
                .filter(record ->
                        record.getDate()
                            .toLocalDate()
                            .getYear() == year
                        &&
                        record.getDate()
                                .toLocalDate()
                                .getMonthValue() == month)
                .toList();
        return records;
    }
}
