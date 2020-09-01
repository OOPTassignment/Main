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
public class StaffDetails {
    public static void StaffM() {
        Scanner scn = new Scanner(System.in);
        int selection=0;
        ArrayList<Staff> checkStaff = Staff.getStaffList();
        boolean catchInt;
        do{
            System.out.printf("%19s%13s\n","","Staff Details");
            System.out.println("===================================================");
            System.out.println("| 1 | Add Staff ");
            System.out.println("===================================================");
            System.out.println("| 2 | Modify Staff ");
            System.out.println("===================================================");
            System.out.println("| 3 | Display Staff ");
            System.out.println("===================================================");
            System.out.println("| 4 | Delete ");
            System.out.println("===================================================");
            System.out.println("| 5 | Exit ");
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
                         addNewStaff(checkStaff);
                         System.out.print("Do you want to add more?(Y/N)");
                         addMore=scn.next().charAt(0);
                         Character.toUpperCase(addMore);
                         if(addMore!='Y'||addMore!='N'){
                             System.out.println("Invalid input entered");
                         }
                        }while(addMore=='Y');
                        break;


                    case 2:
                        System.out.print("Please enter the staff ID :");

                        String inputID = scn.nextLine();
                        for (int i = 0; i <checkStaff.size();i++) {
                            if (inputID.equals(checkStaff.get(i).getId()) ) {
                                ModStaff(checkStaff, i,inputID);
                                break;
                            }
                            else {
                                System.out.println("ID is not registered.");
                                break;
                            }
                        }
                        break;
                    case 3:
                        displayStaff(checkStaff);
                        break;
                    case 4:
                        char check;
                        do{
                            deleteStaff(checkStaff);
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
                System.out.println("Invalid selection entered !");
            }
            

        }while(selection!=5 || catchInt==false);
        /*       addS[0] = new Staff("ABC123", "lee", 20201111, 3000.00, "abc123");*/
    }

    public static void addNewStaff(ArrayList<Staff> addS) {
        LocalDate dateS = LocalDate.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner scn = new Scanner(System.in);
        String staffID;
        /*valStaffID.valStaffID(staffID);*/
        int array = addS.size() - 1;
        int unit = addS.get(array).getId().charAt(3);
        int hundred = addS.get(array).getId().charAt(1);
        int tenth = addS.get(array).getId().charAt(2); 
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
        staffID = "S" + convertHundred + convertTenth + convertUnit;

        System.out.print("Please enter the Name :");
        String staffName = scn.nextLine();

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
                
        addS.add(new Staff(staffID, staffName, dateJoined, salary, password));
    }

    public static void ModStaff(ArrayList<Staff> modS, int i, String inputID) {
        LocalDate memDate = LocalDate.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner scn = new Scanner(System.in);
        int selection=0;
        String staffName="";
        String dateJoined = "";
        double salary=-1.00;
        String password="";
        String staffID= inputID;
        boolean catchInt;
        do{
            System.out.println("===================================================");
            System.out.println("| 1 | Modify Staff Name ");
            System.out.println("===================================================");
            System.out.println("| 2 | Modify Staff ");
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
                        staffName = scn.nextLine();
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
                        if(!staffName.equals("")){
                            modS.get(i).setName(staffName); 
                        }
                        if(!dateJoined.equals("")){
                            modS.get(i).setDateJoined(dateJoined); 
                        }
                        if(!(salary == -1.00)){
                            modS.get(i).setSalary(salary); 
                        }
                        if(!password.equals("")){
                            modS.get(i).setPassword(password); 
                        }
                        break;
                    case 5:
                        return;
                    default :
                        System.out.print("Invalid code entered.\nPlease enter an existing code :");
                }
            }catch(Exception ac){
                catchInt=false;
                scn.nextLine();
                System.out.println("Invalid salary entered !");
            }
        } while(selection!=5 || catchInt==false);
    }
    
    public static void displayStaff(ArrayList<Staff> staffList){

        staffList = Staff.getStaffList();
        System.out.println("");  
        System.out.printf("\n%15sStaff Information\n", "");
        System.out.printf("|%-10s|%-12s|%-13s|%-12s|","Staff ID","Staff Name","Date Joined","Salary");
        System.out.printf("\n===================================================");
        for(int x = 0; x < staffList.size(); x++){
            System.out.printf("\n%s%-10s%s%-12s%s%-13s%s%.2f"," ",staffList.get(x).getId()," ", staffList.get(x).getName()," "
                    , staffList.get(x).getDateJoined()," ", staffList.get(x).getSalary());
        }
        System.out.println("\n");
    }
    
    public static void deleteStaff(ArrayList<Staff> staffList){
        staffList = Staff.getStaffList();
        Scanner scn = new Scanner(System.in);
        String deleteStaff;

        System.out.print("Enter the staff ID wanted to delete : ");
        deleteStaff = scn.nextLine();
        int temp = 0;
        for(int i =0;i<staffList.size();i++){
         if(staffList.get(i).getId().equals(deleteStaff)){
             staffList.remove(i);
             temp=1;
            System.out.println("Record deleted successfully !");
            }   
        }
        if(temp!=1){
            System.out.println("ID does not exist ");
        }

    }
}
