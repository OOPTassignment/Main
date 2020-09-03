
package ooptassignment;

import java.util.ArrayList;
import java.util.Scanner;
//show all the current table
public class SearchTheTable {
    public void showTable(tables[] allTable)
    {
        int scanOption = 0;
        //show all tables
        System.out.println("\t\t\t\tSearch Table");
        System.out.println("==========================================================================================");
        System.out.println("No " + "||" + "  Condition " + "||" + " Total Head " + "||" + " Adult " + "||" + " Children " + "||" + " Elder " + "||" + " Combo Set " + "||" + " Time Until " + "||");
        for(int i = 0; i < 15; i++){
            System.out.printf("%2d || %10s || %10d || %5d || %8d || %5d || %9c || %10s ||\n" ,allTable[i].getTableNo(), allTable[i].getOccupy(), allTable[i].getPersonCount(),
                    allTable[i].getAdultCount() , allTable[i].getChildCount(), allTable[i].getElderCount(), allTable[i].getComboSet(), allTable[i].getTableTime());
        }
        System.out.println("==========================================================================================");
        do{
            //Search 
            Scanner scan = new Scanner(System.in);   
            boolean error;              
                //Scan options
                do{
                    System.out.println("");
                    System.out.println("Select your option: ");
                    System.out.println("1. Search by Table No");
                    System.out.println("2. Search by Condition");
                    System.out.println("3. Search by Set");                 
                    System.out.println("4. Exit");
                    System.out.println("===================");
                    try
                    {            
                        scanOption = scan.nextInt();     
                        error = true;   
                        scan.nextLine();
                    }
                    catch (Exception ex){
                        System.out.println("Invalid integer input");
                        error = false;
                        scan.nextLine();
                    }    
                    if(scanOption < 0 || scanOption > 4){
                        System.out.println("The option must be in (1-4).");
                    }
                }while(!(error) || scanOption <= 0 || scanOption > 4);
            
            switch(scanOption){
                case 1:
                    searchTableNo(allTable);
                    break;
                case 2:
                    searchCondition(allTable);
                    break;
                case 3:
                    searchSet(allTable);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid value...");
                    break;  
            }
        }while(scanOption != 4);
        
    }
    
    
    public static void searchTableNo(tables[] allTable){
        //Enter tableNo   
        Scanner scan = new Scanner(System.in);
        int scanTableNo = 0;
        boolean error;
        do{
            System.out.print("Select tableNo (1-15):");
            try
            {            
                scanTableNo = scan.nextInt();     
                error = true;   
                scan.nextLine();
            }
            catch (Exception ex){
                System.out.println("Invalid integer input");
                error = false;
                scan.nextLine();
            }    
            if(scanTableNo < 0 || scanTableNo > 15){
                System.out.println("The value must in this range (1-15).");
            }
        }while(!(error) || scanTableNo <= 0 || scanTableNo > 15);
        System.out.println("==========================================================================================");
        System.out.println("No " + "||" + "  Condition " + "||" + " Total Head " + "||" + " Adult " + "||" + " Children " + "||" + " Elder " + "||" + " Combo Set " + "||" + " Time Until " + "||");
        System.out.printf("%2d || %10s || %10d || %5d || %8d || %5d || %9c || %10s ||\n" ,allTable[scanTableNo-1].getTableNo(), allTable[scanTableNo-1].getOccupy(), allTable[scanTableNo-1].getPersonCount(),
                            allTable[scanTableNo-1].getAdultCount(), allTable[scanTableNo-1].getChildCount(), allTable[scanTableNo-1].getElderCount(), allTable[scanTableNo-1].getComboSet(), allTable[scanTableNo-1].getTableTime());
        System.out.println("==========================================================================================");
    }
    
    public static void searchCondition(tables[] allTable){
        Scanner scan = new Scanner(System.in);
        String inputInfo;
        char research;
        boolean errorString;
        //Search Condition
        do{    
            System.out.print("Enter to search the table's condition: ");
            inputInfo = scan.nextLine();                           
            if(inputInfo.equalsIgnoreCase("Booking") || inputInfo.equalsIgnoreCase("Occupy") || inputInfo.equalsIgnoreCase("Empty")){
                if(inputInfo.equalsIgnoreCase("Booking")){
                    inputInfo = "Booking";
                }else if(inputInfo.equalsIgnoreCase("Occupy")){
                    inputInfo = "Occupy";
                }else{
                    inputInfo = "Empty";
                }
                errorString = true;
            }else{ 
                System.out.println("Couldn't find the condition...");
                //Ask to reenter or exit
                System.out.println("Do you want to search again?");               
                do{
                    System.out.print("Select Yes(Y) to continue or No(N) to exit: ");
                    research = scan.next().charAt(0);   
                    scan.nextLine();
                    research = Character.toUpperCase(research);                
                    if(!(research == 'Y' || research == 'N')){
                        System.out.println("Just Enter Y to continue or N to exit.");
                    }
                }while(!(research == 'Y' || research == 'N'));
                if(research == 'Y'){
                    errorString = false;
                }else{
                    errorString = true;
                }
            }
        }while(!(errorString));
        
        int totalResult = 0;
        if(inputInfo.equals("Booking")){
            System.out.println("Booking Table Found");
            System.out.println("============================================================================");
            System.out.println("No " + "||" + "  Condition " + "||" + " Total Head " + "||" + " Adult " + "||" + " Children " + "||" + " Elder " + "||" + " Combo Set " + "||");
            for(int i = 0; i < 15; i++){
                if(allTable[i].getOccupy().equals("Booking")){
                    System.out.printf("%2d || %10s || %10d || %5d || %8d || %5d || %9c ||\n" ,allTable[i].getTableNo(), allTable[i].getOccupy(), allTable[i].getPersonCount(),
                        allTable[i].getAdultCount() , allTable[i].getChildCount(), allTable[i].getElderCount(), allTable[i].getComboSet());
                    totalResult++;
                }
            }
            System.out.println("============================================================================");
            System.out.printf("Total Booking Table > %-2d\n", totalResult);
        }else if(inputInfo.equals("Occupy")){
            System.out.println("Occupy Table Found");
            System.out.println("==========================================================================================");
            System.out.println("No " + "||" + "  Condition " + "||" + " Total Head " + "||" + " Adult " + "||" + " Children " + "||" + " Elder " + "||" + " Combo Set " + "||" + " Time Until " + "||");
            for(int i = 0; i < 15; i++){
                if(allTable[i].getOccupy().equals("Occupy")){
                    System.out.printf("%2d || %10s || %10d || %5d || %8d || %5d || %9c || %10s ||\n" ,allTable[i].getTableNo(), allTable[i].getOccupy(), allTable[i].getPersonCount(),
                        allTable[i].getAdultCount() , allTable[i].getChildCount(), allTable[i].getElderCount(), allTable[i].getComboSet(), allTable[i].getTableTime());
                    totalResult++;
                }
            }
            System.out.println("==========================================================================================");
            System.out.printf("Total Occupy Table > %-2d\n", totalResult);
        }else{
            System.out.println("Empty Table Found");
            System.out.println("============================================================================");
            System.out.println("No " + "||" + "  Condition " + "||" + " Total Head " + "||" + " Adult " + "||" + " Children " + "||" + " Elder " + "||" + " Combo Set " + "||");
            for(int i = 0; i < 15; i++){
                if(allTable[i].getOccupy().equals("Empty")){
                    System.out.printf("%2d || %10s || %10d || %5d || %8d || %5d || %9c ||\n" ,allTable[i].getTableNo(), allTable[i].getOccupy(), allTable[i].getPersonCount(),
                        allTable[i].getAdultCount() , allTable[i].getChildCount(), allTable[i].getElderCount(), allTable[i].getComboSet());
                    totalResult++;
                }
            }
            System.out.println("============================================================================");
            System.out.printf("Total Empty Table > %-2d\n", totalResult);
        }
    }
    
    public static void searchSet(tables[] allTable){
        Scanner scan = new Scanner(System.in);
        char comboS;
        boolean trueSet;
        int totalResult = 0;
        ArrayList<Product> checkComboSet = Product.getProductList();
        //Scan combo set    
        do{
            trueSet = true;
            System.out.print("Search combo Set: ");
            comboS = scan.next().charAt(0);   
            scan.nextLine();
            comboS = Character.toUpperCase(comboS);                
            for(int i = 0; i < checkComboSet.size(); i++){
                if(comboS == checkComboSet.get(i).getProductID()){                             
                    trueSet = true;
                    break;
                }else{
                    trueSet = false;
                }
            }
            if(trueSet == false){
                System.out.println("Select one Combo Set that exist.");
            }                      
        }while(!trueSet);
        //show all tables
            System.out.printf("Combo Set %c Table Found\n", comboS);
            System.out.println("============================================================================");
            System.out.println("No " + "||" + "  Condition " + "||" + " Total Head " + "||" + " Adult " + "||" + " Children " + "||" + " Elder " + "||" + " Combo Set " + "||");
                
            for(int i = 0; i < 15; i++){
                char searchCombo = allTable[i].getComboSet();
                if(comboS == searchCombo){
                    totalResult++;
                    System.out.printf("%2d || %10s || %10d || %5d || %8d || %5d || %9c ||\n" ,allTable[i].getTableNo(), allTable[i].getOccupy(), allTable[i].getPersonCount(),
                    allTable[i].getAdultCount() , allTable[i].getChildCount(), allTable[i].getElderCount(), allTable[i].getComboSet());
                }                 
            }
            System.out.println("============================================================================");
            System.out.printf("Total table found %2d\n", totalResult);
                
        }
}
