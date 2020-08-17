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

public class LoginPage {

    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Employees[] empList;
        empList = new Employees[5];
        empList[0] = new Staff("kee", "S020", "a123");
        empList[1] = new Manager("lee", "M011", "12345");

        int tempId = 0;
        int tempPss = 0;
        do{
            System.out.println("Welcome! Please log in your id and password");
            System.out.println("To quit the login please enter id \"exit\"");
            System.out.print("User ID : ");
            String idScan = scan.nextLine();

            if(idScan.equalsIgnoreCase("exit")){
                System.out.println("Exit Succesfully...");
                System.exit(0);
            }

            System.out.print("Password : ");
            String pssScan = scan.nextLine();
        
            for (Employees emp : empList) {
                tempId = 0;
                tempPss = 0;
                if (emp.getId().equalsIgnoreCase(idScan)) {
                    switch (emp.getId().charAt(0)) {
                        case 'S':
                            if (emp.getPss().equals(pssScan)) {
                                System.out.println("Welcome Staff, " + emp.getName());
                                break;
                            }
                            else{
                                tempPss++;
                            }
                            break;
                        case 'M':
                            if (emp.getPss().equals(pssScan)) {
                                System.out.println("Welcome Manager, " + emp.getName());
                                break;
                            }            
                            else{
                                tempPss++;
                            }
                            break;
                        default:
                            tempId++;
                            break;
                    }
                    break;
                } else {
                    tempId++;
                }
            }

            if(tempId > 0){
                System.out.println("The id entered doesn't exist\n");
            }

            if(tempPss > 0){
                System.out.println("The password entered is invalid\n");
            }
        }while(tempId > 0 || tempPss > 0);
    }
    
}
