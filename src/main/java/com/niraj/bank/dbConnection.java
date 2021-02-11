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

import java.sql.*;
public class dbConnection { 
    private String className = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/bank";
    private String username = "root";
    private String password = "N3Ur0t0x1c";
    private Connection conn;

    public Connection getConn() {
        return conn;
    }
    
    
    public dbConnection(){
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url, username, password);
            if(conn == null){
                System.out.println("Not Connected.");
            }
            else{
                System.out.println("connected.");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found.");
        }catch (SQLException e){
            System.out.println("Connection probelm due to SQL Exception in getconnection() method. Check url, username and password.");
        }
    }
    
    int iud(String sqlStmt){
        try{
            Statement st = conn.createStatement();
            return st.executeUpdate(sqlStmt);
        }catch (SQLException e){
            System.out.println("Cannot extecute query.");
            return -1;
        }
    }
    
    ResultSet select(String sqlStmt){
        try{
            Statement st = conn.createStatement();
            return st.executeQuery(sqlStmt);
        }catch (SQLException e)
        {
            System.out.println("Cannot execute SELECT Query.");
            return null;
        }
    }
}
