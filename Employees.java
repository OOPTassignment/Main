/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooptassignment;

/**
 *
 * @author Lim Chee Ziong
 */
public class Employees {
    private String id;
    private String name;
    private String dateJoined;
    private double salary;
    private String password;

    public Employees(){
        
    }
    public Employees(String id, String name, String dateJoined, double salary, String password) {
        this.id = id;
        this.name = name;
        this.dateJoined = dateJoined;
        this.salary = salary;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
