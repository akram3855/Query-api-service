package com.example.queryApi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Response DTO representing a Call Detail Record (CDR).
 */
@Getter
@AllArgsConstructor
public class CallDetailRecordResponse {
    private final LocalDateTime recordDate;
    private final String msisdn;
    private final String imsi;
}
