/*
 * To change this license header, choose License Headers in Project Properties.  
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooptassignment;

/**
 *
 * @author Kee Seng Pong
 */
import java.util.Scanner;
import java.util.ArrayList;

public class LoginPage {

    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Manager.initManager();
        Staff.initStaff();
        ArrayList<Manager> managerList = Manager.getManagerList();
        ArrayList<Staff> staffList = Staff.getStaffList();
        String idScan;
        
        int temp = 0;
        do{
            System.out.println("Welcome! Please log in your id and password");
            System.out.println("To quit the login please enter id \"exit\"");
            System.out.print("User ID : ");
            idScan = scan.nextLine();

            if(idScan.equalsIgnoreCase("exit")){
                System.out.println("Exit Succesfully...");
                System.exit(0);
            }


            System.out.print("Password : ");
            String pswScan = scan.nextLine();
        
            for (int i = 0, j = 0; i < managerList.size() && j < staffList.size(); i++, j++) {
                temp = 0;
                if (managerList.get(i).getId().equalsIgnoreCase(idScan)) {
                    if (managerList.get(i).getPassword().equals(pswScan)) {
                        System.out.println("Welcome Manager, " + managerList.get(i).getName());
                        ManagerMenu managerModule = new ManagerMenu();
                        managerModule.ManagerMenu();
                        break;
                    }            
                    else{
                        temp++;
                        break;
                    }
                       
                }else if(staffList.get(j).getId().equalsIgnoreCase(idScan)) {
                    if (staffList.get(j).getPassword().equals(pswScan)) {
                        System.out.println("Welcome Staff, " + staffList.get(j).getName());
                        StaffMainMenu staffModule = new StaffMainMenu();
                        staffModule.StaffMenu();
                        break;
                    }
                    else{
                        temp++;
                        break;
                    }        
                }else
                    temp++;
            }
            
            if(temp > 0){
                System.out.println("The account entered is invalid\n");
            }
        }while(temp > 0 || !(idScan.equalsIgnoreCase("exit")));
    }
    
}
            