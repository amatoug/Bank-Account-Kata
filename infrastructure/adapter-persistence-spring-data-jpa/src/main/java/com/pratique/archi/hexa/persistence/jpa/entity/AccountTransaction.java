package com.pratique.archi.hexa.persistence.jpa.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity
@Table(name = "TRANSACTION")
public class AccountTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRANS_ID")
	private Integer transactionId;

	@ManyToOne
	@JoinColumn(name = "ACCOUNT_ID", nullable = false)
	Account account;

	@Column
	private int type;
	
	@Column
	private BigDecimal amount;
	
	@Column
	private LocalDateTime date;

}