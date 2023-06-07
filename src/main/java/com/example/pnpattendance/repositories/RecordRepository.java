package com.example.pnpattendance.repositories;

import com.example.pnpattendance.models.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    public List<Record> findAllByDateAfterAndDateBefore(Date startDate, Date endDate);
    public List<Record> findAllByDate(Date date);
    public List<Record> findRecordsByDateEquals(Date date);
    public Optional<Record> findRecordByOfficer_BadgeNumberAndDate(long badgeNumber, Date date);

    public Optional<Record> findLastRecordByOfficer_BadgeNumber(long badgeNumber);

    public List<Record> findAllRecordByOfficer_BadgeNumber(long badgeNumber);

}
