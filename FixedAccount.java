package com.bankManager;

import java.util.Calendar;

public class FixedAccount extends Account{
    private double depositAmmount;
    private double tenure;
    private double intrestEarned;


    public FixedAccount(int accountNo,String ifscCode,String bankName,double balance,double tenure){
        super(accountNo,ifscCode,bankName,balance);
        this.tenure = tenure;
        setSavings(false);
    }


    public void calculateIntrest(){ this.intrestEarned = getBalance() * tenure * 0.08;}
}
