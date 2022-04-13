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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRANSACTION")
public class AccountTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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