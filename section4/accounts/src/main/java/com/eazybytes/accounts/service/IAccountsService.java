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
	
	CustomerDto fetchAccount(String mobileNumber);

	
	/**
    *
    * @param customerDto - CustomerDto Object
    * @return boolean indicating if the update of Account details is successful or not
    */
   boolean updateAccount(CustomerDto customerDto);
   
   /**
   *
   * @param mobileNumber - Input Mobile Number
   * @return boolean indicating if the delete of Account details is successful or not
   */
  boolean deleteAccount(String mobileNumber);
}
