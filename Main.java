package com.bankManager;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();
        int id;
        Customer cust;
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("* Menu *\n" +
                    "\t-> 1) Add new customer \n" +
                    "\t-> 2) Assign account to customer \n" +
                    "\t-> 3) Display balance/interest\n" +
                    "\t-> 4) Sort customers\n" +
                    "\t-> 5) Persist data\n" +
                    "\t-> 6) Show all customers\n" +
                    "\t-> 7) Search customer by name\n" +
                    "\t-> 8) Exit\n");

            System.out.print("Enter your choice : ");
            String scan = scanner.next();
            try {

                switch (scan) {
                    case "1":
                        manager.addCustomer();
                        break;

                    case "2":
                        System.out.print("Enter user id whom to assign account : ");
                        id = scanner.nextInt();
                        manager.assignAccount(id);
                        break;

                    case "3":
                        System.out.print("Enter user id to display balance : ");
                        id = scanner.nextInt();
                        manager.displaBalance(id);
                        break;

                    case "4":
                        System.out.println("-> 1) Sort by Name\n" +
                                "-> 2) sort by Balance");
                        System.out.print("Enter choice : ");
                        String ch = scanner.next();
                        switch (ch) {
                            case "1":
                                manager.sortByName();
                                break;
                            case "2":
                                manager.sortByBalance();
                                break;

                            default:
                                System.out.println("Invalid choice try again . ");
                        }
                        break;

                    case "5":
//                    manager.writeToFile("asd");
                        try {
                            manager.writeToDatabase();
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;

                    case "6":
                        System.out.print("Read From file/Database (f/d) : ");
                        String inpfile = scanner.next();
                        while(inpfile.equals("f")|| inpfile.equals("d"))
//                    manager.readFromFile("asd");
                        try {
                            manager.readFromDatabase();
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;

                    case "7":
                        try {
                            manager.searchByName("vin");
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;

                    case "8":
                        System.out.println("Bye!!");
                        break;
                    default:
                        System.out.println("invalid input Try Again!!");
                }
            }catch (InputMismatchException e){
                System.out.println("Wrong Format Encountered. Try Again!!");
            }
            if(scan == "8")break;;
        }

    }

}