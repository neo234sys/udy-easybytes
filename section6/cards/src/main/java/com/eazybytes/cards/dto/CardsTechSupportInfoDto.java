package com.eazybytes.cards.dto;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cards")
public record CardsTechSupportInfoDto(Map<String,String> techSupport) {

}
