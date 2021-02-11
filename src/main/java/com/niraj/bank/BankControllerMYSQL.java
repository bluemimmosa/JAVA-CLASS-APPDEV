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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deposit(int accNo, int balance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean newAccount(Account a) {
        String sqlStmt = "INSERT INTO `bank`.`account` (`accNo`, `name`, `amount`, `accType`) VALUES ('"+a.getAccNo()+"', '"+a.getName()+"', '"+a.getAmount()+"', '"+a.getAccType()+"');";
        if(dbConn.iud(sqlStmt)>0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public ArrayList<Account> viewAllAccounts() {
        ResultSet rs;
        String sqlStmt = "SELECT * FROM account;";
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
    
}
