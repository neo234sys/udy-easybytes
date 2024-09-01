package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

	 private static final long MAX = 9999999999L; // one minute per test
	 
	@NotNull(message = "AccountNumber must be 10 digits")
	@Min(value=1000000000,message = "AccountNumber must be 10 digits")
	@Max(value = MAX, message = "AccountNumber must be 10 digits")
	private Long accountNumber;

	@NotEmpty(message = "AccountType can not be a null or empty")
	private String accountType;

	@NotEmpty(message = "BranchAddress can not be a null or empty")
	private String branchAddress;
}
