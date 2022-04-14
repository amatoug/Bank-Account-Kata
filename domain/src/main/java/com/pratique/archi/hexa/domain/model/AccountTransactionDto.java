package com.pratique.archi.hexa.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName(value = "Transaction")
public class AccountTransactionDto {

	private String accountNumber;

	private TransactionTypeEnum type;

	private BigDecimal amount;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime date;

}