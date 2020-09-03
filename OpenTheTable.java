package ooptassignment;
import java.util.Scanner;
import java.util.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.text.DateFormat;

//open table/booking table
public class OpenTheTable {

    public void openTable(tables[] allTable)
    {
        Scanner scan = new Scanner(System.in);        
            int scanTableNo = 0;
            int totalTable = 15;
            char againToOpenTable;
            boolean error;
            
        boolean scanDoAgain;
        do{
            //fill all the table to empty !!for running the program at the first time
            if(allTable[0] == null){
                for(int i = 0; i < totalTable; i++){
                    allTable[i] = new tables(i+1, "Empty", 0, 0, 0, 0, '-');
                }
            }    
            //show all tables
            System.out.println("\t\t\t\tTable Details");
            System.out.println("============================================================================");
            System.out.println("No " + "||" + "  Condition " + "||" + " Total Head " + "||" + " Adult " + "||" + " Children " + "||" + " Elder " + "||" + " Combo Set " + "||");
            for(int i = 0; i < totalTable; i++){
                System.out.printf("%2d || %10s || %10d || %5d || %8d || %5d || %9c ||\n" ,allTable[i].getTableNo(), allTable[i].getOccupy(), allTable[i].getPersonCount(),
                        allTable[i].getAdultCount() , allTable[i].getChildCount(), allTable[i].getElderCount(), allTable[i].getComboSet());
            }
            System.out.println("============================================================================");
            //select tableNo   
            do{
                System.out.print("Select tableNo (1-15):");
                try
                {            
                    scanTableNo = scan.nextInt();     
                    error = true;   
                    scan.nextLine();
                }
                catch (Exception ex){
                    System.out.print("Invalid integer input");
                    error = false;
                    scan.nextLine();
                }    
                if(scanTableNo < 0 || scanTableNo > 15){
                    System.out.println("The value must in this range (1-15).");
                }
            }while(!(error) || scanTableNo <= 0 || scanTableNo > 15);
            //finding table
            boolean confirmOpen, errorString, error1, error2, error3;
            String inputInfo;
            char confirm, comboS;
            int adultC = 0, childC = 0, elderC = 0, totalPerson = 0;
            String selectedTable = allTable[scanTableNo-1].getOccupy();
            if(selectedTable.equalsIgnoreCase("Empty")){
                //Enter table info
                do{
                        do{    
                            System.out.print("Enter the table info (Booking)/(Occupy): ");
                            inputInfo = scan.nextLine();                           
                            if(inputInfo.equalsIgnoreCase("Booking") || inputInfo.equalsIgnoreCase("Occupy")){
                                if(inputInfo.equalsIgnoreCase("Booking")){
                                    inputInfo = "Booking";
                                }else{
                                    inputInfo = "Occupy";
                                }
                                errorString = true;
                            }else{
                                errorString = false;   
                            }
                        }while(!(errorString));
                            
                        do{
                            System.out.println("!!!Each table only can fit 8 people!!!");                            
                            do{     //input adult 
                                System.out.print("Total adult : ");
                                try
                                {            
                                    adultC = scan.nextInt();     
                                    error1 = true;
                                    scan.nextLine();
                                }
                                catch (Exception ex){
                                    System.out.print("Invalid integer input");
                                    error1 = false;
                                }                                  
                            }while(!(error1) || adultC < 0 || adultC > 8);                          
                            do{     //input children
                                System.out.print("Total children : ");
                                try
                                {            
                                    childC = scan.nextInt();     
                                    error2 = true;
                                    scan.nextLine();
                                }
                                catch (Exception ex){
                                    System.out.print("Invalid integer input");
                                    error2 = false;
                                }                                  
                            }while(!(error2) || childC < 0 || childC > 8);    
                            do{     //input elders
                                System.out.print("Total elder : ");
                                try
                                {            
                                    elderC = scan.nextInt();     
                                    error3 = true; 
                                    scan.nextLine();
                                }
                                catch (Exception ex){
                                    System.out.println("Invalid integer input");
                                    error3 = false;
                                }                                  
                            }while(!(error3) || elderC < 0 || elderC > 8);
                            //Calc total People
                            totalPerson = adultC + childC + elderC;                            
                            if(totalPerson > 8){
                                System.out.println("The table can't fit all the people.");
                            }
                            if(totalPerson == 0){
                                System.out.println("The table can't be empty.");
                            }
                        }while(totalPerson > 8 || totalPerson <= 0);       //if more than 8 total personCount max
                    //Scan combo set    
                    do{
                        System.out.print("Select the combo Set (A/B/C/D): ");
                        comboS = scan.next().charAt(0);   
                        scan.nextLine();
                        comboS = Character.toUpperCase(comboS);                
                        if(!(comboS == 'A' || comboS == 'B' || comboS == 'C' || comboS == 'D')){
                            System.out.println("Just Enter which set A or B or C or D.");
                        }                       
                    }while(!(comboS == 'A' || comboS == 'B' || comboS == 'C' || comboS == 'D'));
                    
                           
                    //confirmation 
                    do{
                        System.out.print("Confirm (Y/N): ");
                        confirm = scan.next().charAt(0);   
                        scan.nextLine();
                        confirm = Character.toUpperCase(confirm);                
                        if(!(confirm == 'Y' || confirm == 'N')){
                            System.out.println("Just Enter Y to confirm and N to not.");
                        }
                    }while(!(confirm == 'Y' || confirm == 'N'));
                    //key in the info into the table
                    if(confirm == 'Y'){
                        confirmOpen = true;
                        allTable[scanTableNo-1].setOccupy(inputInfo);
                        allTable[scanTableNo-1].setAdultCount(adultC);
                        allTable[scanTableNo-1].setChildCount(childC);
                        allTable[scanTableNo-1].setElderCount(elderC);
                        allTable[scanTableNo-1].setPersonCount(totalPerson);
                        allTable[scanTableNo-1].setComboSet(comboS);
                        if(inputInfo.equals("Occupy")){
                            //printtableInfo
                            System.out.println("======================================================");
                            System.out.printf("Your table is No. %2d and the time is until ", scanTableNo);
                            LocalTime myTime = LocalTime.now();
                            LocalTime twoHoursLater = myTime.plusHours(2);
                            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
                            System.out.println(twoHoursLater.format(dateTimeFormatter));
                        }
                    }else{
                        confirmOpen = false;
                    }                   
                }while(!(confirmOpen));

                //Ask to reenter or exit
                System.out.println("======================================================");
                System.out.println("Do you want to open another table?");               
                do{
                    System.out.print("Select Yes(Y) to continue or No(N) to exit: ");
                    againToOpenTable = scan.next().charAt(0);   
                    scan.nextLine();
                    againToOpenTable = Character.toUpperCase(againToOpenTable);                
                    if(!(againToOpenTable == 'Y' || againToOpenTable == 'N')){
                        System.out.println("Just Enter Y to continue or N to exit.");
                    }
                }while(!(againToOpenTable == 'Y' || againToOpenTable == 'N'));
                if(againToOpenTable == 'Y'){
                    scanDoAgain = true;
                }else{
                    scanDoAgain = false;
                }
            }
            else{
                System.out.println("The table had occupied.");             
                do{
                    System.out.print("Please select another table(S) or exit(E): ");
                    againToOpenTable = scan.next().charAt(0);   
                    scan.nextLine();
                    againToOpenTable = Character.toUpperCase(againToOpenTable);                
                    if(!(againToOpenTable == 'S' || againToOpenTable == 'E')){
                        System.out.println("Just Enter S to select table again or E to exit.");
                    }
                }while(!(againToOpenTable == 'S' || againToOpenTable == 'E'));
                if(againToOpenTable == 'S'){
                    scanDoAgain = true;
                }else{
                    scanDoAgain = false;
                }
            }
        }while(scanDoAgain);
    }
}
