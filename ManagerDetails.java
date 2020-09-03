/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooptassignment;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
        
        

/**
 *
 * @author Lim Chee Ziong
 */
public class ManagerDetails {
    public static void ManagerM() {
        Scanner scn = new Scanner(System.in);
        ArrayList<Manager> checkManager = Manager.getManagerList();
        int selection=0;
        boolean catchInt;
        do{
            System.out.printf("%18s%15s\n","","Manager Details");
            System.out.println("===================================================");
            System.out.println("| 1 | Add Manager ");
            System.out.println("===================================================");
            System.out.println("| 2 | Modify Manager ");
            System.out.println("===================================================");
            System.out.println("| 3 | Display Manager ");
            System.out.println("===================================================");
            System.out.println("| 4 | Delete Manager ");
            System.out.println("===================================================");
            System.out.println("| 5 | Back to Manager Menu ");
            System.out.println("===================================================");
            System.out.print("Please enter your selection :");
            
            try{
                selection = scn.nextInt();
                scn.nextLine();//rewind
                catchInt=true;
                switch (selection) {
                    case 1:
                        char addMore;
                        do{
                         addNewManager(checkManager);
                         System.out.print("Do you want to add more?(Y/N)");
                         addMore=scn.next().charAt(0);
                         Character.toUpperCase(addMore);
                        if(addMore!='Y'||addMore!='N'){
                             System.out.println("Invalid input entered");
                         }
                        }while(addMore=='Y');
                        break;

                    case 2:
                        System.out.print("Please enter the Manager ID :");

                        String inputID = scn.nextLine();
                        for (int i = 0; i <checkManager.size();i++) {
                            if (inputID.equals(checkManager.get(i).getId()) ) {
                                ModManager(checkManager, i,inputID);
                                break;
                            }
                            else {
                                System.out.println("ID is not registered.");
                                break;
                            }
                        }
                        break;
                    case 3:
                        displayManager(checkManager);
                        break;
                    case 4:
                        char check;
                        do{
                            deleteManager(checkManager);
                            System.out.print("Do you want to continue deleting staff(Y/N)?");
                            check = scn.next().charAt(0);
                            Character.toUpperCase(check);
                            if(!(check!='Y'||check!='N')){
                                System.out.println("Invalid input entered");
                            }
                        }while(check=='Y');
                        break;
                    case 5:
                        return;
                    default :
                        System.out.print("Invalid code entered.\nPlease enter an existing code :");
                }

            }catch(Exception ab){
                catchInt=false;
                scn.nextLine();
                System.out.println("Invalid salary entered !");
            }
            
            
        }while(selection!=5 || catchInt==false);
        /*       addM[0] = new Manager("ABC123", "lee", 20201111, 3000.00, "abc123");*/
    }

    public static void addNewManager(ArrayList<Manager> addM) {
        LocalDate dateS = LocalDate.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner scn = new Scanner(System.in);
        String ManagerID;
        /*valManagerID.valManagerID(ManagerID);*/
        int array = addM.size() - 1;
        int unit = addM.get(array).getId().charAt(3);
        int hundred = addM.get(array).getId().charAt(1);
        int tenth = addM.get(array).getId().charAt(2); 
        int convertUnit = Character.getNumericValue(unit);          //auto generate member ID
        int convertTenth = Character.getNumericValue(tenth);
        int convertHundred = Character.getNumericValue(hundred);

        convertUnit = convertUnit + 1;
        if(convertUnit == 10){
            convertTenth++;
            convertUnit = 0;
        }
        if(convertTenth == 10){
            convertHundred++;
            convertTenth = 0;
        }
        ManagerID = "M" + convertHundred + convertTenth + convertUnit;

        System.out.print("Please enter the Name :");
        String managerName = scn.nextLine();

        String dateJoined = dateS.format(formatDate);
        
        boolean catchDouble;
        double salary=0;
        do{
            System.out.print("Please enter the Salary :");
            try{
                salary = scn.nextDouble();
                scn.nextLine();
                catchDouble=true;
            }catch(Exception ab){
                catchDouble=false;
                scn.nextLine();
                System.out.println("Invalid salary entered !");
            }
            if(salary < 0)
                System.out.println("Salary must not be negative number !");
        }while(!(catchDouble) || salary < 0);
        
        
        String password;
        boolean limitPw;
        do{
            System.out.print("Please set the password :");
            password = scn.nextLine();
            if(password.length()>=19){
                System.out.println("Password can only have 18 characters");
                limitPw = false;
            }
            else if((password.equals(""))||(password.length()<8)){
                System.out.println("Password must have at least 8 characters");
                limitPw = false;
            }
            else limitPw=true;
        }while(!limitPw);
                
        addM.add(new Manager(ManagerID, managerName, dateJoined, salary, password));
    }

    public static void ModManager(ArrayList<Manager> modM, int i, String inputID) {
        Scanner scn = new Scanner(System.in);
        int selection=0;
        String managerName="";
        double salary=-1.00;
        String password="";
        String managerID= inputID;
        boolean catchInt;
        do{
            System.out.println("===================================================");
            System.out.println("| 1 | Modify Manager Name ");
            System.out.println("===================================================");
            System.out.println("| 2 | Modify Salary ");
            System.out.println("===================================================");
            System.out.println("| 3 | Modify Password ");
            System.out.println("===================================================");
            System.out.println("| 4 | Save the changes ");
            System.out.println("===================================================");
            System.out.println("| 5 | Exit ");
            System.out.println("===================================================");
            System.out.print("Enter your selection :");


            try{
                selection = scn.nextInt();
                scn.nextLine();
                catchInt=true;
                switch(selection){
                    case 1:
                        System.out.print("Please enter the Name :");
                        managerName = scn.nextLine();
                        break;
                    case 2:
                        boolean catchDouble;
                        do{
                            System.out.print("Please enter the Salary :");
                            try{
                                salary = scn.nextDouble();
                                scn.nextLine();
                                catchDouble=true;
                            }catch(Exception ac){
                                catchDouble=false;
                                scn.nextLine();
                                System.out.println("Invalid salary entered !");
                            }
                            if(salary < 0)
                                System.out.println("Salary must not be negative number !");
                        }while(!(catchDouble) || salary < 0);
                        break;
                    case 3:
                        boolean limitPw;
                        do{
                            System.out.print("Please set the password :");
                            password = scn.nextLine();
                            if(password.length()>=19){
                                System.out.println("Password can only have 18 characters");
                                limitPw = false;
                            }
                            else if((password.equals(""))||(password.length()<8)){
                                System.out.println("Password must have at least 8 characters");
                                limitPw = false;
                            }
                            else limitPw=true;
                        }while(!limitPw);
                        break;
                    case 4:
                        if(!managerName.equals("")){
                            modM.get(i).setName(managerName); 
                        }
                        if(!(salary == -1.00)){
                            modM.get(i).setSalary(salary); 
                        }
                        if(!password.equals("")){
                            modM.get(i).setPassword(password); 
                        }
                        break;
                    case 5:
                        return;
                    default :
                        System.out.print("Invalid code entered.\nPlease enter an existing code :");
                }
            }catch(Exception ab){
                catchInt=false;
                scn.nextLine();
                System.out.println("Invalid selection entered !");
            }
        
            
        } while(selection!=5 || catchInt==false);
    }
    
    public static void displayManager(ArrayList<Manager> ManagerList){

        ManagerList = Manager.getManagerList();
        System.out.println("");  
        System.out.printf("\n%15sManager Information\n", "");
        System.out.printf("|%-10s|%-12s|%-13s|%-12s|","Manager ID","Manager Name","Date Joined","Salary");
        System.out.printf("\n===================================================");
        for(int x = 0; x < ManagerList.size(); x++){
            System.out.printf("\n%s%-10s%s%-12s%s%-13s%s%.2f"," ",ManagerList.get(x).getId()," ", ManagerList.get(x).getName()," "
                    , ManagerList.get(x).getDateJoined()," ", ManagerList.get(x).getSalary());
        }
        System.out.println("\n");
        
    }
    
    public static void deleteManager(ArrayList<Manager> ManagerList){
        ManagerList = Manager.getManagerList();
        Scanner scn = new Scanner(System.in);
        String deleteManager;

        System.out.print("Enter the manager ID wanted to delete : ");
        deleteManager = scn.nextLine();
        int temp = 0;
        for(int i =0;i<ManagerList.size();i++){
         if(ManagerList.get(i).getId().equals(deleteManager)){
             ManagerList.remove(i);
             temp=1;
            System.out.println("Record deleted successfully !");
            }   
        }
        if(temp!=1){
            System.out.println("ID does not exist ");
        }

    }
}
