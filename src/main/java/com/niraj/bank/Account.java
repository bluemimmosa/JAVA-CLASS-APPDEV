/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niraj.bank;

/**
 *
 * @author Dell
 */
public class Account {
    int accNo;
    String name;
    int amount;
    char accType;
    
    
    public Account() {
    }

    public Account(int accNo, String name, int amount, char accType) {
        this.accNo = accNo;
        this.name = name;
        this.amount = amount;
        this.accType = accType;
    }

    @Override
    public String toString() {
        return "Account{" + "accNo=" + accNo + ", name=" + name + ", amount=" + amount + ", accType=" + accType + '}';
    }
    
    

    public int getAccNo() {
        return accNo;
    }

    public void setAccNo(int accNo) {
        this.accNo = accNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public char getAccType() {
        return accType;
    }

    public void setAccType(char accType) {
        this.accType = accType;
    }
    
    
}
