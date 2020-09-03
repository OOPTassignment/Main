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
import java.util.ArrayList;
public class Staff extends Employees{
    protected static ArrayList<Staff> staffList;
    
    public Staff(){
            
    }
    public Staff (String staffID, String name, String dateJoined, double salary, String password) {
        super(staffID,name,dateJoined,salary,password);
    }


    public static ArrayList<Staff> getStaffList() {
        return staffList;
    }

    public static void setStaffList(ArrayList<Staff> staffList) {
        Staff.staffList = staffList;
    }
    
    protected static void initStaff(){
        staffList = new ArrayList<>();
        staffList.add(new Staff("S001", "lee", "23/06/2016", 3000.00, "abc123"));
        staffList.add(new Staff("S002", "lim", "12/07/2019", 5000.00, "def123"));
        staffList.add(new Staff("S003", "pee", "1/09/2020", 1000.00, "ghi123"));
    }
}
