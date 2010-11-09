package com.smes.web.dto;

public enum TransactionType {
	PAYMENT, ACCOUNT;
	
	public static TransactionType getTransactionType (String type){
		for (TransactionType tt : values()){
			if (tt.name().equalsIgnoreCase(type))
				return tt;
		}
		throw new IllegalArgumentException("invalid paramater " + type);
	}
}
