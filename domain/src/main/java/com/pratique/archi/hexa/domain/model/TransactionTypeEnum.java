package com.pratique.archi.hexa.domain.model;

public enum TransactionTypeEnum {

	DEPOSIT(1,"DEPOSIT"), WITHDRAWAL(2,"WITHDRAWAL");

	int code;
	String name;
	TransactionTypeEnum(int code,String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}

	public static TransactionTypeEnum findByCode(int code){
	    for(TransactionTypeEnum v : values()){
	        if( v.getCode()==code){
	            return v;
	        }
	    }
	    return null;
	}
}
