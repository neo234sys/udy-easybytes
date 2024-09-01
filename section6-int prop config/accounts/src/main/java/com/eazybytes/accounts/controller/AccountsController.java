package com.eazybytes.accounts.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.AccountTechSupportInfoDto;
import com.eazybytes.accounts.dto.AccountsContactInfoDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountsService;
import com.eazybytes.accounts.dto.ErrorResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

/**
 * @author Hasan
*/

@Tag(
        name = "CRUD REST APIs for Cards in EazyBank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE Accounts details"
)
@RestController
@RequestMapping(path="/api", produces= {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {
	
	private final IAccountsService iAccountsService;
	
	@Value("${build.version}")
	private String buildVersion;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private AccountsContactInfoDto accountsContactInfoDto;
	
	@Autowired
	private AccountTechSupportInfoDto accountTechSupportInfoDto;
	
	
	
	
	public AccountsController(IAccountsService iAccountsService) {
		this.iAccountsService=iAccountsService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
		iAccountsService.createAccount(customerDto);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam 
			 @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
			String mobileNumber){
		CustomerDto custDto=iAccountsService.fetchAccount(mobileNumber);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(custDto);
	}
	
	 @PutMapping("/update")
	 public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
		boolean isupdated= iAccountsService.updateAccount(customerDto);
		if(isupdated) {
			return ResponseEntity
				.status(HttpStatus.OK)
				.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		}else {
			return ResponseEntity
					.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
		}
	 }
	 
	 @DeleteMapping("/delete")
	 public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
	                                                                @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	                                                                String mobileNumber) {
	        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
	        if(isDeleted) {
	            return ResponseEntity
	                    .status(HttpStatus.OK)
	                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
	        }else{
	            return ResponseEntity
	                    .status(HttpStatus.EXPECTATION_FAILED)
	                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
	        }
	    }
	 
	 
	 @Operation(
	            summary = "Get Build info REST API",
	            description = "Get Build Info that is deployed into accounts MS"
	    )
	    @ApiResponses({
	            @ApiResponse(
	                    responseCode = "200",
	                    description = "HTTP Status OK"
	            ),
	            @ApiResponse(
	                    responseCode = "500",
	                    description = "HTTP Status Internal Server Error",
	                    content = @Content(
	                            schema = @Schema(implementation = ErrorResponseDto.class)
	                    )
	            )
	    }
	    )
	 @GetMapping("/build-info")
	 public ResponseEntity<String> getBuildnfo(){
		  return ResponseEntity
				  .status(HttpStatus.OK)
				  .body(buildVersion);
	 }
	 
	 
	 
	 
	 @Operation(
	            summary = "Get Java version REST API",
	            description = "Get Java version that is deployed into accounts MS"
	    )
	    @ApiResponses({
	            @ApiResponse(
	                    responseCode = "200",
	                    description = "HTTP Status OK"
	            ),
	            @ApiResponse(
	                    responseCode = "500",
	                    description = "HTTP Status Internal Server Error",
	                    content = @Content(
	                            schema = @Schema(implementation = ErrorResponseDto.class)
	                    )
	            )
	    }
	    )
	 @GetMapping("/java-version")
	 public ResponseEntity<String> getJavaVersion(){
		  return ResponseEntity
				  .status(HttpStatus.OK)
				  .body(environment.getProperty("JAVA_HOME"));
	 }
	 
	 
	 
	 
	 @Operation(
	            summary = "Get ContactInfo from prop file REST API",
	            description = "Get ContactInfo that is deployed into accounts MS"
	    )
	    @ApiResponses({
	            @ApiResponse(
	                    responseCode = "200",
	                    description = "HTTP Status OK"
	            ),
	            @ApiResponse(
	                    responseCode = "500",
	                    description = "HTTP Status Internal Server Error",
	                    content = @Content(
	                            schema = @Schema(implementation = ErrorResponseDto.class)
	                    )
	            )
	    }
	    )
	 @GetMapping("/contact-info")
	 public ResponseEntity<AccountsContactInfoDto> getContactInfo(){
		  return ResponseEntity
				  .status(HttpStatus.OK)
				  .body(accountsContactInfoDto);
	 }
	 
	 
	 
	 
	 
	 @Operation(
	            summary = "Get TechSupport Info from prop file REST API",
	            description = "Get TechSupport that is deployed into accounts MS"
	    )
	    @ApiResponses({
	            @ApiResponse(
	                    responseCode = "200",
	                    description = "HTTP Status OK"
	            ),
	            @ApiResponse(
	                    responseCode = "500",
	                    description = "HTTP Status Internal Server Error",
	                    content = @Content(
	                            schema = @Schema(implementation = ErrorResponseDto.class)
	                    )
	            )
	    }
	    )
	 @GetMapping("/techSupport-info")
	 public ResponseEntity<AccountTechSupportInfoDto> getTechSupport(){
		  return ResponseEntity
				  .status(HttpStatus.OK)
				  .body(accountTechSupportInfoDto);
	 }

}
