package com.bankManager;

import java.io.*;
import java.util.*;
import java.sql.*;
public class Manager{

    private Scanner scanner = new Scanner(System.in);
    private static List<Customer> customers = new ArrayList<>();




    private class compareName implements Comparator<Customer>{

        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
        }
    }

    private boolean validateDate(String Date){
        try{
            String[] splitDate = Date.split("/");
            int date = Integer.parseInt(splitDate[0]);
            int mounth = Integer.parseInt(splitDate[1]);
            int year = Integer.parseInt(splitDate[2]);
            boolean isLeapyear;
            if(year > Calendar.getInstance().get(Calendar.YEAR)) {
                System.out.println("cannot Enter Future date! ");
                return false;
            }
            if(year == Calendar.getInstance().get(Calendar.YEAR)){
                if (mounth==Calendar.getInstance().get(Calendar.MONTH)+1)
                    if(date> Calendar.getInstance().get(Calendar.DATE)){
                        System.out.println("cannot Enter Future date! ");
                        return false;
                }else if(mounth>Calendar.getInstance().get(Calendar.MONTH)+1){
                        System.out.println("cannot Enter Future date! ");
                        return false;
                    }
            }

            int[] temp = {1,3,5,7,8,10,12};
            if(year<1 || mounth<1||date<1)

                return false;
            if(year%400==0){
                isLeapyear = true;
            }else if(year%100==0){
                isLeapyear=false;
            }else if(year%4==0){
                isLeapyear=true;
            }else isLeapyear=false;

            if( mounth<13 ){
                if(mounth==2){
                    if(isLeapyear ){
                        if( date<=29)
                        return true;
                    }else{
                        if( date<=28){
                            return true;
                        }
                    }
                    return false;
                }
                if(Arrays.asList(temp).contains(mounth)){
                    if (date <=31){
                        return true;
                    }
                }else {
                    if(date<=30){
                        return true;
                    }
                }

            }else return false;

        }catch (Exception e){
//            System.out.println(e);
            System.out.print("Wrong Format. ");
            return false;

        }
        return false;

    }

    private class compareBalance implements Comparator<Customer>{

        @Override
        public int compare(Customer o1, Customer o2) {
            return (int)(o1.getAccountBalance()-o2.getAccountBalance());
        }
    }


    private static boolean validateMobile(long  mobile){
        long max = 9999999999L;
        return (mobile>999999999 && mobile<=max);
    }

    private static boolean validateAdhar(String adhar){
        return adhar.length()==12;
    }


    public void addCustomer(){
        Customer cust;
        System.out.print("Enter Name : ");
        String name = scanner.next();

        System.out.print("Enter Date of Birth (DD/MM/YYYY) : ");
        String Dob = scanner.next();
        while (!validateDate(Dob)){
            System.out.println("invalid Date!!");
            System.out.print("Enter Date of Birth (DD/MM/YYYY) : ");
            Dob = scanner.next();
        }

        System.out.print("Enter age : ");
        int age = scanner.nextInt();

        System.out.print("Enter Mobile No. : ");
        long mobile = scanner.nextLong();

        while(!validateMobile(mobile)){
            System.out.println("Invalid Mobile Number!!");
            System.out.print("Enter Mobile No. : ");
            mobile = scanner.nextLong();
        }

        System.out.print("Enter AdharCard Number : ");
        String adharno = scanner.next();

        while(!validateAdhar(adharno)){
            System.out.println("Invalid Adhar Number!!");
            System.out.print("Enter AdharCard Number : ");
            adharno = scanner.next();
        }

        cust = new Customer(name,Dob,age,mobile,adharno);
        customers.add(cust);
        System.out.println(cust.getName());
    }



        public void assignAccount(int id){
            for(Customer c : customers) {
                if (c.getId() == id) {
                    System.out.println("Enter bank account type Savings/Fixed Deposit (s/f) : ");
                    String type = scanner.next();
                    System.out.println("Enter Account Details : ");
                    System.out.println("enter Bank Account No. : ");
                    int accno = scanner.nextInt();
                    System.out.println("enter Bank bank IFSC : ");
                    String ifsc = scanner.next();
                    System.out.println("enter Bank Name : ");
                    String bname = scanner.next();
                    System.out.println("enter Bank Balance : ");
                    double bal = scanner.nextDouble();
                    System.out.println("enter account open date (0 to take default today) : ");
                    String openingdate = scanner.next();
                    if (openingdate != "0") {
                        while (!validateDate(openingdate)){
                            System.out.println("invalid Date!!");
                            System.out.println("enter account open date (0 to take default today) : ");
                            openingdate = scanner.next();
                        }

                    }
                    if (type.toLowerCase().strip().equals("s")) {
                        System.out.print("Is the Account Salary Account (y/n) : ");
                        String isSalary = scanner.next();
                        while (isSalary.toLowerCase() == "y" || isSalary.toLowerCase() == "y") {
                            System.out.println("invalid  Try Again!!");
                            System.out.print("Is the Account Salary Account (y/n) : ");
                            isSalary = scanner.next();
                        }
                        boolean isSal = isSalary.toLowerCase() == "y" ? true : false;
                        if((!isSal)&&(bal<5000)){
                            System.out.println("Insufficient Funds minimum 5000 required for account creation");
                            return;
                        }
                        try {
                            c.addSavingsAccount(accno, ifsc, bname, bal, isSal,openingdate);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.print("enter Deposit Amount (min 1000) : ");
                        double deposit = scanner.nextDouble();
                        while(deposit<1000){
                            System.out.print("Minimum amount should be 1000 : ");
                            deposit = scanner.nextDouble();
                        }
                        System.out.print("Enter Tenure period(1-7) : ");
                        double tenure = scanner.nextDouble();
                        while(tenure<1||tenure>7){
                            System.out.print("Tenure period must be in range(1-7) : ");
                            deposit = scanner.nextDouble();
                        }
                        try {
                            c.addFixedDeposit(accno, ifsc, bname, bal, tenure);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    return;
                }
            }
            System.out.println("Bank Account not Found!!");
    }

    public void displaBalance(int id){
        for(Customer c : customers){
            if(c.getId()==id){
                System.out.println(c.getAccountBalance());
                break;
            }
        }

    }


    public void sortByBalance(){
        Comparator<Customer> comp = new compareBalance();

        Collections.sort(customers,comp);
        displayAllCustomers();

    }

    public void sortByName(){
        Comparator<Customer> comp = new compareName();

        Collections.sort(customers,comp);
        displayAllCustomers();
    }

    public void displayAllCustomers(){
        for(Customer c : customers){
            System.out.print(c.getId());
            System.out.print(" -> " + c.getName());
            System.out.print(" ("+c.getAccountBalance()+" ).\n");
        }
    }


    public void writeToFile(String fileName) throws Exception{
        try{
            FileOutputStream writeData = new FileOutputStream("customerData.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(customers);
            writeStream.flush();
            writeStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFromFile(String fileName) throws Exception{
        try{
            FileInputStream readData = new FileInputStream("customerData.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            ArrayList<Customer> customers2 = (ArrayList<Customer>) readStream.readObject();
            readStream.close();
            for(Customer c : customers2){
                System.out.println(c.getId()+" " + c.getName()+ " "+ c.getAccountBalance());
            }
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void readFromDatabase() throws Exception{
        Customer c = null;
        ObjectInputStream objIn = null;
        ByteArrayInputStream bIn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String url="jdbc:mysql://localhost:3306/BankManagement",uname="root",password="root";

        String query = "select * from Customers;";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,uname,password);

        statement = con.createStatement();
        resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int id,age;
            long mobileNo;
            String adharNo,name;
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            age = resultSet.getInt("age");
            mobileNo = resultSet.getLong("mobileNo");
            adharNo = resultSet.getString("adharNo");
            c = new Customer(id,name,age,mobileNo,adharNo);
            Blob bankAccount = resultSet.getBlob("bankAccount");
            bIn = new ByteArrayInputStream(bankAccount.getBytes(1, (int) bankAccount.length()));
            objIn = new ObjectInputStream(bIn);
            Account a = (Account) objIn.readObject();

            c.addSavingsAccount(a);
            c.getdetails();
            customers.add(c);

        }

    }

    public void writeToDatabase() throws Exception{
        String url="jdbc:mysql://localhost:3306/BankManagement",uname="root",password="root";

        String customerQuery = "insert into Customers (id,name,age,mobileNo,adharNo,bankAccount) values(?,?,?,?,?,?);";

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(url,uname,password);
        PreparedStatement prepared = con.prepareStatement(customerQuery);

        for(Customer c :customers) {
            prepared.setInt(1, c.getId());
            prepared.setString(2, c.getName());
            prepared.setInt(3, c.getAge());
            prepared.setLong(4, c.getMobileNumber());
            prepared.setString(5, c.getAdharNumber());
            ByteArrayOutputStream bAout = new ByteArrayOutputStream();
            ObjectOutputStream objOut = new ObjectOutputStream(bAout);
            try {
                objOut.writeObject(c.getAccount());
                prepared.setBlob(6, new ByteArrayInputStream(bAout.toByteArray()));
            } catch (Exception e){
                System.out.println(e);
                continue;
            }finally {
                objOut.close();
                bAout.close();
            }
            prepared.execute();
        }
        prepared.close();
        con.close();
        customers.clear();
        Customer.setCustomerCount(1);

    }

    public void searchByName(String sname)throws Exception {
        Customer c = null;
        ObjectInputStream objIn = null;
        ByteArrayInputStream bIn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String url = "jdbc:mysql://localhost:3306/BankManagement", uname = "root", password = "root";

        String query = "select * from Customers where name = \"" + sname + "\";";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, uname, password);

        statement = con.createStatement();
        resultSet = statement.executeQuery(query);
        if(resultSet.next()){
            do {
                int id, age;
                long mobileNo;
                String adharNo, name;
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                age = resultSet.getInt("age");
                mobileNo = resultSet.getLong("mobileNo");
                adharNo = resultSet.getString("adharNo");
                c = new Customer(id, name, age, mobileNo, adharNo);
                Blob bankAccount = resultSet.getBlob("bankAccount");
                bIn = new ByteArrayInputStream(bankAccount.getBytes(1, (int) bankAccount.length()));
                objIn = new ObjectInputStream(bIn);
                Account a = (Account) objIn.readObject();

                c.addSavingsAccount(a);
                c.getdetails();
            }while (resultSet.next());
        }else{
            System.out.println("Name not Found!!");
        }
    }
}
