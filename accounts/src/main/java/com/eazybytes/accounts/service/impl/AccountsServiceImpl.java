package com.eazybytes.accounts.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Hasan
*/
@Service
@AllArgsConstructor
@NoArgsConstructor
public class AccountsServiceImpl implements IAccountsService {
	
	@Autowired
	 private AccountsRepository accountsRepository;
	@Autowired
	 private CustomerRepository customerRepository;

	/**
	 *
	 */
	@Override
	public void createAccount(CustomerDto customerDto) {
		Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
		Optional<Customer> optionalCustomer=customerRepository.findByMobileNumber(customer.getMobileNumber());
		if(optionalCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    +customerDto.getMobileNumber());
		}
		Customer savedCustomer = customerRepository.save(customer);
		 accountsRepository.save(createNewAccount(savedCustomer));

	}
	
	 private Accounts createNewAccount(Customer customer) {
	        Accounts newAccount = new Accounts();
	        newAccount.setCustomerId(customer.getCustomerId());
	        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

	        newAccount.setAccountNumber(randomAccNumber);
	        newAccount.setAccountType(AccountsConstants.SAVINGS);
	        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
	        newAccount.setCreatedBy(AccountsConstants.ADMIN);
	        newAccount.setCreatedAt(AccountsConstants.TIME_NOW);
	        return newAccount;
	}

}
