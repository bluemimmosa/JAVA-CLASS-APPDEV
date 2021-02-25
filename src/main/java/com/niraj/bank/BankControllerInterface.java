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
public interface BankControllerInterface {
    ArrayList<Account> ac = new ArrayList();
    ArrayList<Object[]> oac = new ArrayList();
    public Account findAccount(int accNo);
    public boolean deposit(int accNo, int balance);
    public boolean newAccount(Account a);
    public ArrayList<Account> viewAllAccounts();
    public ArrayList<Object[]> viewAllAccountsinObj();
    public int withdraw(int accNo, int balance);
    public int deleteAccount(int accNo);
    public int fundTransfer(int accNoTarget, int accNoSource, int amount);
}
