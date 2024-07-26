package com.eazybytes.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Hasan
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDto {

	private Long accountNumber;
	private String accountType;
	private String branchAddress;
}