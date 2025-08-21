package com.example.queryApi.controller;

import com.example.queryApi.dto.CallDetailRecordResponse;
import com.example.queryApi.dto.QueryRequest;
import com.example.queryApi.model.CallDetailRecord;
import com.example.queryApi.repository.CallDetailRecordRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST Controller for querying Call Detail Records (CDRs).
 * Provides a POST endpoint to filter records by date range, MSISDN, and IMSI.
 */
@RestController
@RequestMapping("/api/query")
public class CallDetailRecordController {

    private final CallDetailRecordRepository repository;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public CallDetailRecordController(CallDetailRecordRepository repository) {
        this.repository = repository;
    }

    /**
     * Query Call Detail Records based on input filters.
     *
     * @param request contains date range (required), and optional MSISDN, IMSI
     * @return list of matching CallDetailRecordResponse objects
     */
    @PostMapping
    public List<CallDetailRecordResponse> queryRecords(@RequestBody QueryRequest request) {

        LocalDateTime start = LocalDateTime.parse(request.getRecord_date_start(), FORMATTER);
        LocalDateTime end = LocalDateTime.parse(request.getRecord_date_end(), FORMATTER);

        List<CallDetailRecord> records;

        if (request.getMsisdn() != null && request.getImsi() != null) {
            records = repository.findByRecordDateBetweenAndMsisdnAndImsi(
                    start, end, request.getMsisdn(), request.getImsi());
        } else if (request.getMsisdn() != null) {
            records = repository.findByRecordDateBetweenAndMsisdn(start, end, request.getMsisdn());
        } else if (request.getImsi() != null) {
            records = repository.findByRecordDateBetweenAndImsi(start, end, request.getImsi());
        } else {
            records = repository.findByRecordDateBetween(start, end);
        }

        return records.stream()
                .map(record -> new CallDetailRecordResponse(
                        record.getRecordDate(),
                        record.getMsisdn(),
                        record.getImsi()
                ))
                .collect(Collectors.toList());
    }
}
