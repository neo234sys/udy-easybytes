package com.eazybytes.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Hasan
*/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto {

	private String statusCode;
	private String statusMsg;
}
