package com.example.pnpattendance.services.recordService;


import com.example.pnpattendance.models.Admin;
import com.example.pnpattendance.models.Officer;
import com.example.pnpattendance.models.Record;
import com.example.pnpattendance.repositories.IAdminRepository;
import com.example.pnpattendance.repositories.OfficerRepository;
import com.example.pnpattendance.repositories.RecordRepository;
import com.example.pnpattendance.request.RecordRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class RecordService implements IRecordService{

    @Autowired
    private final RecordRepository recordRepository;
    private final IAdminRepository adminRepository;
    private final OfficerRepository officerRepository;

    public RecordService(
            RecordRepository recordRepository,
            IAdminRepository adminRepository,
            OfficerRepository officerRepository)
    {
        this.adminRepository = adminRepository;
        this.officerRepository = officerRepository;
        this.recordRepository = recordRepository;
    }

    @Override
    public Iterable<Record> findAll(){
        Iterable<Record> records = recordRepository.findAll(Sort.by(Sort.Direction.DESC, "recordId"));
        return records;
    }

    @Override
    public Record save(RecordRequestBody record){

        long badgeNumber = record.getBadgeNumber();
        Date date = record.getDate();

        Record existingRecord = recordRepository
              .findRecordByOfficer_BadgeNumberAndDate(badgeNumber, date);

        if(existingRecord != null){
            existingRecord.setTimeOut(record.getTime());
            return recordRepository.save(existingRecord);
        }

        Admin admin = adminRepository.findById(record.getAdminId()).orElse(null);
        Officer officer = officerRepository.findById(record.getBadgeNumber()).orElse(null);
        if(admin == null || officer == null){
            return null;
        }
        Record newRecord = new Record(
              admin,
              officer,
              record.getTime(),
              null,
              record.getDate()
        );

        return recordRepository.save(newRecord);
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
    public List<Record> getMonthlyRecords(int year, int month) {

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
