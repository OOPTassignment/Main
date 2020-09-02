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
    private static ArrayList<Manager> ManagerList;
    
    public Manager(){
            
    }
    public Manager (String managerID, String name, String dateJoined, double salary, String password) {
        super(managerID,name,dateJoined,salary,password);
    }
    

    public static ArrayList<Manager> getManagerList() {
        return ManagerList;
    }

    public static void setManagerList(ArrayList<Manager> ManagerList) {
        Manager.ManagerList = ManagerList;
    }
    
    public static void initManager(){
        ManagerList = new ArrayList<>();
        ManagerList.add(new Manager("M001", "Mek Di", "23/06/2001", 3000.00, "abc123"));
        ManagerList.add(new Manager("M002", "Justin", "23/07/2001", 5000.00, "def123"));
        ManagerList.add(new Manager("M003", "Pee shee", "23/09/2001", 1000.00, "ghi123"));
    }
}
