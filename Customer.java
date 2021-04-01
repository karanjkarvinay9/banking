package com.bankManager;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Customer implements Serializable {
    private static int customerCount = 100;
    final private int id;
    private String name;
    private String DOB;
    private int age;
    private long mobileNumber;
    private String adharNumber;
    private Account bankAccount = null;

    public Customer( String name, String DOB,int age, long mobileNumber, String adharNumber){
        this.id = Customer.customerCount++;
        this.name=name;
        this.DOB = DOB;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.adharNumber = adharNumber;

    }

    public Customer(int id, String name, int age, long mobileNumber, String adharNumber){
        this.id = id;
        this.name=name;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.adharNumber = adharNumber;
    }

    public static void setCustomerCount(int count){
        customerCount = 100+count-1;
    }

    public Account getAccount(){return this.bankAccount;}

    public boolean isSavings(){return  this.bankAccount.isSavings();}

    public int getAge(){return this.age;}

    public long getMobileNumber(){return this.mobileNumber;}

    public String getAdharNumber(){return this.adharNumber;}

    public double getAccountBalance(){
        try{
            return this.bankAccount.getBalance();
        }catch (NullPointerException e){
            return 0;
        }
    }

    public String getName() {
        return name;
    }


    public boolean getAccountStatus(){
        return this.bankAccount == null;
    }


    public void getdetails(){
        if(bankAccount!=null){
            this.bankAccount.getdetails();
        }
        System.out.println(getId());
        System.out.println(getName());
        System.out.println(getAdharNumber());
        System.out.println(getAge());
        System.out.println(getMobileNumber());

    }

    public int getId() {
        return this.id;
    }

    public void addSavingsAccount(Account a){
        this.bankAccount = a;
    }

    public void addSavingsAccount(int accno,String ifsc,String bname,double bal,boolean isSalary,String openingdate) throws Exception {
        if(getAccountStatus())
            if(openingdate=="0"){
                this.bankAccount = new SavingsAccount(accno, ifsc, bname, bal, isSalary);
            }else{
            this.bankAccount = new SavingsAccount(accno, ifsc, bname, bal, isSalary,openingdate);
            }
        else throw new Exception("Bank Account Limit Exceeded!!");
    }

    public void addFixedDeposit(int accno,String ifsc,String bname,double bal,double tenure) throws Exception {
        if(getAccountStatus())
            this.bankAccount = new FixedAccount(accno, ifsc, bname, bal, tenure);
        else throw new Exception("Bank Account Limit Exceeded!!");

    }


    @Override
    public String toString(){
        return "Customer{'" +
                "id='"+id+'\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", adharNumber='" + adharNumber + '\'' +
                ", bankAccount='" + bankAccount.toString() + '\'' +
                "}\n";
    }
}
