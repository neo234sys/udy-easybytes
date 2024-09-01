package com.eazybytes.accounts.dto;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "accounts")
public record AccountTechSupportInfoDto(Map<String, String> techSupport) {

}
