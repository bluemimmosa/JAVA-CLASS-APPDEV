/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niraj.bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public boolean withdraw(int accNo, int balance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
