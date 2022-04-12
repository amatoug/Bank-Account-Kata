package com.pratique.archi.hexa.domain.model;

public enum TransactionTypeEnum {

	DEPOSIT(1), WITHDRAWAL(2);

	int id;

	TransactionTypeEnum(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
