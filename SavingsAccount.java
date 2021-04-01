package com.bankManager;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class SavingsAccount extends Account implements Serializable {
    private boolean isSalary = false;
    private double minBalance = 5000;
    private double interest=0;


    public class CustomException extends Exception {
        public CustomException(String message){
            super(message);
        }
    }

    public SavingsAccount(int accountNo,String ifscCode,String bankName,double balance){
        super(accountNo,ifscCode,bankName,balance);

    }

    public SavingsAccount(int accountNo,String ifscCode,String bankName,double balance,boolean isSalary){
        super(accountNo,ifscCode,bankName,balance);
        this.isSalary = isSalary;
    }

    public SavingsAccount(int accountNo,String ifscCode,String bankName,double balance,boolean isSalary,String opendate){
        super(accountNo,ifscCode,bankName,balance,opendate);
        this.isSalary = isSalary;
    }

    public SavingsAccount(int accountNo,String ifscCode,String bankName,double balance,boolean isSalary,double minBalance,double interest){
        super(accountNo,ifscCode,bankName,balance);
        this.isSalary = isSalary;
        this.minBalance=minBalance;
        this.interest=interest;
    }

    public SavingsAccount createSavingsAccount(int accountNo,String ifscCode,String bankName,double balance,boolean isSalary) throws CustomException {
        if(!isSalary){
            if(balance>=5000)
            return new SavingsAccount(accountNo,ifscCode,bankName,balance,isSalary);
            throw new CustomException("Insufficient balance to create account. Minimum required balance : 5000");
        }else{
            if(balance>=0){
                return new SavingsAccount(accountNo,ifscCode,bankName,balance,isSalary);
            }
            throw new CustomException("Balance can't be negative..");


        }
    }

    public void depositAmmount(double amount){
        this.setBalance(this.getBalance()+amount);
    }


    public void withdrawAmount(double amount) throws CustomException {
        if(!this.isSalary && this.getBalance()-amount>=5000){
            this.setBalance(this.getBalance()-amount);
        }else if(this.isSalary){
            if(this.getBalance()-amount>=0){
                this.setBalance(this.getBalance()-amount);
            }
            throw new CustomException("Insufficient Funds");
        }throw new CustomException("Insufficient Funds! Minimum of 5000 is required in account");
    }

    public String toString(){
        return "SavingsAccount{'" +
                "accountNumber='"+getAccountNumber()+'\'' +
                ", ifscCode='" +getIfscCode() + '\'' +
                ", bankName='" + getBankName() + '\'' +
                ", balance='" + getBalance() + '\'' +
                ", isSalary='" + this.isSalary + '\'' +
                ", minBalance='" + this.minBalance + '\'' +
                ", interest='" + this.interest + '\'' +
                "}\n";
    }
    public void calculateIntrest(){
        String s = getOpeningDate();
        String[] list = s.split("/");
        int mounth = Integer.parseInt(list[1]);
        int year = Integer.parseInt(list[2]);
        int currYear  = Calendar.getInstance().get(Calendar.YEAR);
        int currmounth = Calendar.getInstance().get(Calendar.MONTH)+1;
        int period=1;
        if(mounth>currmounth)
        {
            period = currYear-year+1;
        }
        else{
            period = currYear-year;
        }
        this.interest = getBalance() * period * 0.04;
    }
}


