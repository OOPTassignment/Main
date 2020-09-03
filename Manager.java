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
public class Manager extends Employees{
    protected static ArrayList<Manager> managerList;
    
    public Manager(){
            
    }
    public Manager (String managerID, String name, String dateJoined, double salary, String password) {
        super(managerID,name,dateJoined,salary,password);
    }
    


    public static ArrayList<Manager> getManagerList() {
        return managerList;
    }

    public static void setManagerList(ArrayList<Manager> managerList) {
        Manager.managerList = managerList;
    }
    
    protected static void initManager(){
        managerList = new ArrayList<>();
        managerList.add(new Manager("M001", "Mek Di", "23/06/2001", 3000.00, "abc123"));
        managerList.add(new Manager("M002", "Justin", "23/07/2001", 5000.00, "def123"));
        managerList.add(new Manager("M003", "Pee shee", "23/09/2001", 1000.00, "ghi123"));
    }
}
