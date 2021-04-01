package com.bankManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public abstract class Account implements Serializable {
    private int accountNumber;
    private String ifscCode;
    private String bankName;
    private double balance;
    private String openingDate;
    private boolean isSavings=true;

    public abstract void calculateIntrest();

    public Account(int accountNumber, String ifscCode, String bankName, double balance,String openingDate) {
        this.accountNumber = accountNumber;
        this.ifscCode = ifscCode;
        this.bankName = bankName;
        this.balance = balance;
        this.openingDate = openingDate;
    }
    public Account(int accountNumber, String ifscCode, String bankName, double balance) {
        this.accountNumber = accountNumber;
        this.ifscCode = ifscCode;
        this.bankName = bankName;
        this.balance = balance;
         setDate();
    }


    public void getdetails(){
        System.out.println(this.accountNumber);
        System.out.println(this.balance);

    }


    public int getAccountNumber(){
        return this.accountNumber;
    }


    public double getBalance(){
        return this.balance;
    }

    public double setBalance(double amount){
        this.balance = amount;
        return this.balance;
    }


    private void setDate(){
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.openingDate  = Formatter.format(localDate);
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public String getBankName() {
        return bankName;
    }

    public boolean isSavings(){return this.isSavings; }


    public void setSavings(boolean b) {
        this.isSavings=b;
    }

    public String getOpeningDate() {
        return openingDate;
    }
}
