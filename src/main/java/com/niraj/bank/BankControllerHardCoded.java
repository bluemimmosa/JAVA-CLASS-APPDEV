/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niraj.bank;

import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class BankControllerHardCoded implements BankControllerInterface{
         
    @Override
    public Account findAccount(int accNo){
       for(Account a:ac){
           if(accNo == a.getAccNo()){
               return a;
           }
       }
       return null;
    }
    
    @Override
    public boolean deposit(int accNo, int balance){
        Account a = findAccount(accNo);
        if(a != null){
            a.setAmount(a.getAmount()+balance);
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public boolean newAccount(Account a){
        return ac.add(a);
    }
    
    @Override
    public ArrayList<Account> viewAllAccounts(){
        return ac;
    }
}
