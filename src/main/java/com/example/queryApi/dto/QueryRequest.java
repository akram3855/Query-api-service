package com.example.queryApi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryRequest {
    private String record_date_start;
    private String record_date_end;
    private String msisdn;
    private String imsi;
}
