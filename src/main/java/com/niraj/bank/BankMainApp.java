/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niraj.bank;

import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class BankMainApp {
    BankControllerInterface bc = new BankControllerHardCoded();
    public void deposit(){
        int accNo; 
        int bal;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter account number: ");
        accNo = sc.nextInt();
        
        System.out.println("Enter amount to deposit: ");
        bal = sc.nextInt(); 
        if(bc.deposit(accNo, bal))
            System.out.println("Deposit Successful. Remaining balance in your account is: "+bc.findAccount(accNo).getAmount());                     
        else
            System.out.println("Account not existent.");
    }
    
//    public void withdraw(){
//        int accNo;
//        int bal;
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter account number: ");
//        accNo = sc.nextInt();
//        Account a = findAccount(accNo);
//        if(a == null){
//            System.out.println("Account not exisiting in system. Invalid account number.");
//            return;
//        }
//        else{
//            System.out.println("Enter amount to withdraw: ");
//            bal = sc.nextInt(); 
//            if(a.getAmount()>=bal){
//                int newbal = a.getAmount()-bal;
//                a.setAmount(newbal);
//                System.out.println("Withdraw Successful. Remaining balance in your account is: "+a.getAmount());
//            }
//            else{
//                System.out.println("The withdraw amount is more than that is in your account.");
//            }
//            
//        }
//    }
//    
    public void viewAllAccounts(){
        for(Account a : bc.viewAllAccounts()){
            System.out.println(a.toString());
        }
    }
//    
    public void newAccount(){
        Scanner sc = new Scanner(System.in);
        String name;
        int amount;
        int accNo;
        char accType;
        System.out.println("Account No: ");
        try{
            accNo = sc.nextInt();
        }catch(Exception n){
            accNo = 0;
            System.out.println("Invalid account number.");
        }
        
        sc.nextLine();
        System.out.println("Name: ");
        name = sc.nextLine();
        System.out.println("Opening Balance: ");
        try{
            amount = sc.nextInt();
        }catch(Exception n){
            amount = 0;
            System.out.println("the input is not a number.");
        }
        sc.nextLine();
        System.out.println("Account Type ('C' for Current; 'F' for Fixed; 'S' for Saving): ");
        accType = sc.nextLine().charAt(0);
        if(bc.newAccount(new Account(accNo, name, amount, accType)))
            System.out.println("Account Created successfully.");
        else
            System.out.println("Cannot create account.");
        
        
    }
    
    public void displayMenu(){
            System.out.println("=============================================");
            System.out.println("\t\tCore Banking System");
            System.out.println("=============================================");
            System.out.println("1. Create new Account");
            System.out.println("2. Withdraw money from Account");
            System.out.println("3. Deposit money into Account");
            System.out.println("4. Display All Account information");
            System.out.println("0. Exit");
    }
    
    public static void main(String[] args) {
        int choice;
        Scanner sc;
        BankMainApp bma = new BankMainApp();
        do {
            bma.displayMenu();
            System.out.println("Enter your choice (0-4): ");
            sc = new Scanner(System.in);
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    bma.newAccount();
                    break;
                case 2:
                    //bma.withdraw();
                    break;
                case 3:
                    bma.deposit();
                    break;
                case 4:
                    bma.viewAllAccounts();
                    break;
                case 0:
                    System.out.println("Exiting");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }while(true);
    
    }
}
