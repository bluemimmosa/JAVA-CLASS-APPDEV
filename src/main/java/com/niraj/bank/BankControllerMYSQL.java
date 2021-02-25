/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niraj.bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class BankControllerMYSQL implements BankControllerInterface{
    dbConnection dbConn;

    public BankControllerMYSQL() {
        dbConn = new dbConnection();
    }
    
    
    @Override
    public Account findAccount(int accNo) {
        ResultSet rs;
        Account a;
        String sqlStmt = "SELECT * FROM account WHERE accNo = '"+accNo+"';";
        rs = dbConn.select(sqlStmt);
        try{
            while(rs.next()){
                a = new Account(rs.getInt("accNo"), rs.getString("name"), rs.getInt("amount"), rs.getString("accType").charAt(0));
                return a;
            }
        }catch (SQLException e){
            ;
        }
        return null;
    }

    @Override
    public boolean deposit(int accNo, int balance) {
        Account a = findAccount(accNo);
        if(a != null){
            a.setAmount(a.getAmount()+balance);
            String sqlStmt = "UPDATE `bank`.`account` SET `amount` = '"+a.getAmount()+"' WHERE (`accNo` = '"+accNo+"');";
            return dbConn.iud(sqlStmt)>0;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean newAccount(Account a) {
        // check if the accNo already exists in the system or not?
        String sqlStmt = "INSERT INTO `bank`.`account` (`accNo`, `name`, `amount`, `accType`) VALUES ('"+a.getAccNo()+"', '"+a.getName()+"', '"+a.getAmount()+"', '"+a.getAccType()+"');";
        return dbConn.iud(sqlStmt)>0;
    }

    @Override
    public ArrayList<Account> viewAllAccounts() {
        ResultSet rs;
        String sqlStmt = "SELECT * FROM account;";
        ac.clear();
        rs = dbConn.select(sqlStmt);
        try{
            while(rs.next()){
                Account a = new Account(rs.getInt("accNo"), rs.getString("name"), rs.getInt("amount"), rs.getString("accType").charAt(0));
                ac.add(a);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ac;
    }

    @Override
    public int withdraw(int accNo, int balance) {
        Account a = findAccount(accNo);
        if(a != null){
            if(a.getAmount()<balance){
                return -1; // when the balance is not sufficient to withdraw.
            }
            a.setAmount(a.getAmount()-balance);
            String sqlStmt = "UPDATE `bank`.`account` SET `amount` = '"+a.getAmount()+"' WHERE (`accNo` = '"+accNo+"');";
            if(dbConn.iud(sqlStmt)>0){
                return 1;
            }
            else{
                return -2;
            }
        }
        else{
            return 0;
        }
    }

    @Override
    public int deleteAccount(int accNo) {
        Account a = findAccount(accNo);
        if(a != null){
            if(a.getAmount()== 0){
                //i can safely delete now as the account contains 0 balance.
                String sqlStmt = "DELETE FROM `bank`.`account` WHERE (`accNo` = '"+accNo+"');";
                return dbConn.iud(sqlStmt);
            }
            else{
                return -2; // bcoz there was some money remaining in the account.
            }
        }
        else{
            return 0; // bcoz account was not found.
        }
    }

    @Override
    public int fundTransfer(int accNoTarget, int accNoSource, int amount) {
        Account a = findAccount(accNoSource);
        if(a == null){
            return -1;
        }
        Account b = findAccount(accNoTarget);
        if(b == null){
            return -2;
        }
        if(a.getAmount() < amount){
            return -3;
        }
        a.setAmount(a.getAmount()- amount);
        b.setAmount(b.getAmount()+ amount);
        String sqlStmtA = "UPDATE `bank`.`account` SET `amount` = '"+a.getAmount()+"' WHERE (`accNo` = '"+a.getAccNo()+"');";
        String sqlStmtB = "UPDATE `bank`.`account` SET `amount` = '"+b.getAmount()+"' WHERE (`accNo` = '"+b.getAccNo()+"');";
       if((dbConn.iud(sqlStmtA)==1) && (dbConn.iud(sqlStmtB)==1)){
           return 1;
       }
       else{
           return 0;
       }
    }

    @Override
    public ArrayList<Object[]> viewAllAccountsinObj() {
        ResultSet rs;
        String sqlStmt = "SELECT * FROM account;";
        oac.clear();
        rs = dbConn.select(sqlStmt);
        try{
            while(rs.next()){
                Object a[] = {rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4).charAt(0)};
                oac.add(a);
            } 
        }catch (SQLException e){
            e.printStackTrace();
        }
        return oac;
    }
    
}
