package com.example.pnpattendance.services.recordService;

import com.example.pnpattendance.models.Admin;
import com.example.pnpattendance.models.Officer;
import com.example.pnpattendance.models.Record;
import com.example.pnpattendance.repositories.IAdminRepository;
import com.example.pnpattendance.repositories.OfficerRepository;
import com.example.pnpattendance.repositories.RecordRepository;
import com.example.pnpattendance.request.RecordRequestBody;
import com.example.pnpattendance.request.WholeRecordRequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecordService implements IRecordService {

    @Autowired
    private final RecordRepository recordRepository;
    private final IAdminRepository adminRepository;
    private final OfficerRepository officerRepository;

    public RecordService(
            RecordRepository recordRepository,
            IAdminRepository adminRepository,
            OfficerRepository officerRepository) {
        this.adminRepository = adminRepository;
        this.officerRepository = officerRepository;
        this.recordRepository = recordRepository;
    }

    @Override
    public List<Record> findAll() {
        List<Record> recordList = new ArrayList<>(recordRepository.findAll(Sort.by(Sort.Direction.DESC, "recordId")));
        return recordList;
    }

    @Override
    public Record save(RecordRequestBody record) {

        // Verify admin and officer
        Admin admin = adminRepository.findById(record.getAdminId()).orElse(null);
        Officer officer = officerRepository.findById(record.getBadgeNumber()).orElse(null);

        // return null if admin or officer is null
        if (admin == null || officer == null) {
            return null;
        }

        long badgeNumber = record.getBadgeNumber();
        Date date = record.getDate();

        LocalTime officerTimeOut = officer.getDutyOut().toLocalTime();
        LocalTime officerTimeIn = officer.getDutyOn().toLocalTime();
        LocalTime recordTime = record.getTime().toLocalTime();

        // Check existing record
        Record existingRecord = recordRepository
                .findRecordByOfficer_BadgeNumberAndDate(badgeNumber, date).orElse(null);

        // If record exists, update the time-out and status
        if (existingRecord != null) {

            if (officerTimeOut.isBefore(recordTime)) {
                existingRecord.setUndertime(true);
            }
            if (officerTimeOut.isAfter(recordTime) && !existingRecord.isLate()) {
                existingRecord.setOvertime(true);
            }

            existingRecord.setTimeOut(record.getTime());
            return recordRepository.save(existingRecord);
        }

        // Create new record
        Record newRecord = new Record(
                admin,
                officer,
                record.getTime(),
                null,
                record.getDate(),
                officerTimeIn.isBefore(recordTime),
                false,
                false);

        return recordRepository.save(newRecord);
    }

    @Override
    public List<Record> findAllByDate(Date date) {
        LocalDate localDate = date.toLocalDate().plusDays(1);
        return recordRepository.findRecordsByDateEquals(Date.valueOf(localDate));
    }

    @Override
    public List<Record> getAllByWeek(Date startDate, Date endDate) {
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
                .filter(record -> record.getDate()
                        .toLocalDate()
                        .getYear() == year
                        &&
                        record.getDate()
                                .toLocalDate()
                                .getMonthValue() == month)
                .toList();
        return records;
    }

    @Override
    public Record wholeSave(WholeRecordRequestBody record) {

        // Verify admin and officer
        Admin admin = adminRepository.findById(record.getAdminId()).orElse(null);
        Officer officer = officerRepository.findById(record.getBadgeNumber()).orElse(null);

        if (admin == null || officer == null) {
            return null;
        }

        LocalTime officerTimeOut = officer.getDutyOut().toLocalTime();
        LocalTime officerTimeIn = officer.getDutyOn().toLocalTime();
        LocalTime recordTimeIn = record.getTimeIn().toLocalTime();
        LocalTime recordTimeOut = record.getTimeOut().toLocalTime();

        Record newRecord = new Record(
                admin,
                officer,
                record.getTimeIn(),
                record.getTimeOut(),
                record.getDate(),
                officerTimeIn.isBefore(recordTimeIn),
                officerTimeOut.isBefore(recordTimeOut),
                officerTimeOut.isAfter(recordTimeOut) && !officerTimeIn.isBefore(recordTimeIn));

        return recordRepository.save(newRecord);
    }

    @Override
    public List<Record> findAllByOfficerId(long id) {
        return recordRepository.findAllRecordByOfficer_BadgeNumber(id);
    }
}
