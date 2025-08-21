package com.example.queryApi.repository;

import com.example.queryApi.model.CallDetailRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CallDetailRecordRepository extends JpaRepository<CallDetailRecord, Long> {

    List<CallDetailRecord> findByRecordDateBetween(LocalDateTime start, LocalDateTime end);

    List<CallDetailRecord> findByRecordDateBetweenAndMsisdn(LocalDateTime start, LocalDateTime end, String msisdn);

    List<CallDetailRecord> findByRecordDateBetweenAndImsi(LocalDateTime start, LocalDateTime end, String imsi);

    List<CallDetailRecord> findByRecordDateBetweenAndMsisdnAndImsi(LocalDateTime start, LocalDateTime end, String msisdn, String imsi);
}
