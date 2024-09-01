package com.eazybytes.accounts.entity;

import java.io.Serializable;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Hasan
*/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Accounts extends BaseEntity implements Serializable{

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="account_number")
    private Long accountNumber;
	
	
    @Column(name="customer_id")
    private Long customerId;

    @Column(name="account_type")
    private String accountType;

    @Column(name="branch_address")
    private String branchAddress;
}
