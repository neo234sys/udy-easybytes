package com.eazybytes.loans.dto;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "loans")
public record LoansTechSupportInfoDto(Map<String,String> techSupport) {

}
