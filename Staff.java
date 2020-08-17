/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;
/**
 *
 * @author Asus
 */
public class Staff{
    private String staffID;
    private String name;
    private int dateJoined;
    private double salary;
    private String password;
    
    public Staff(){
            
    }
    public Staff(String staffID, String name, int dateJoined, double salary, String password) {
        this.staffID = staffID;
        this.name = name;
        this.dateJoined = dateJoined;
        this.salary =salary;
        this.password = password;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
    

    public double getSalary() {
        return salary;
    }

    public void setSalary(double basicSalary) {
        this.salary = basicSalary;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(int dateJoined) {
        this.dateJoined = dateJoined;
    }
    
    
    @Override
    public String toString() {
        return "Staff ID"+ staffID +"\nName: " + name + "\nYear Joined: " + dateJoined + "\nBasic Salary: " + salary + "\n";
    }
    
    public double calculateSalary() {
        return salary;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Staff) {
            return ((Staff)obj).getName().equals(this.getName());
        } else return false;
    }
    
}
