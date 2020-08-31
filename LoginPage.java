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
        Employees.initial();
        Employees[] empList = Employees.getEmpList();
        
        int tempId = 0;
        int tempPsw = 0;
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
            String pswScan = scan.nextLine();
        
            for (Employees emp : empList) {
                tempId = 0;
                tempPsw = 0;
                if (emp.getId().equalsIgnoreCase(idScan)) {
                    switch (emp.getId().charAt(0)) {
                        case 'S':
                            if (emp.getPsw().equals(pswScan)) {
                                System.out.println("Welcome Staff, " + emp.getName());
                                break;
                            }
                            else{
                                tempPsw++;
                            }
                            break;
                        case 'M':
                            if (emp.getPsw().equals(pswScan)) {
                                System.out.println("Welcome Manager, " + emp.getName());
                                break;
                            }            
                            else{
                                tempPsw++;
                            }
                            break;
                        default:
                            tempId++;
                            break;
                    }
                    break;
                }else {
                    tempId++;
                    break;
                }
            }

            if(tempId > 0){
                System.out.println("The id entered doesn't exist\n");
            }

            if(tempPsw > 0){
                System.out.println("The password entered is invalid\n");
            }
        }while(tempId > 0 || tempPsw > 0);
    }
    
}
