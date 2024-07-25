package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;

/**
 * @author Hasan
*/
public interface IAccountsService {
	
	/**
	 * @param customerDto
	 */
	void createAccount(CustomerDto customerDto);

}
