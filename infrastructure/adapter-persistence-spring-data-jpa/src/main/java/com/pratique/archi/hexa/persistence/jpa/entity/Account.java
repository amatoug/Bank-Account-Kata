package com.pratique.archi.hexa.persistence.jpa.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ACCOUNT")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCOUNT_ID")
	private Integer accountId;

	@Column(name = "ACC_NUM")
	private String accountNumber;

	@Column(name = "BALANCE")
	private BigDecimal balance;

    @OneToMany(mappedBy="account",cascade = CascadeType.ALL)
	private List<AccountTransaction> transactions = new ArrayList<AccountTransaction>();

    
}
