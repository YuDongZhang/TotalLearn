package com.example.totallearn.designmode.buildermode;

/**
 * 可以这样调用   Director director = new Director();
 *				director.pay("100");
 */
public class Director {
	//调用的地方
	public void pay(String amount){
		new Charges.Builder()
		.amount(amount)
		.created("127.0.0.1")
		.subject("篮球")
		.body("NBA篮球官方提供").build().pay();
	}
	
}
