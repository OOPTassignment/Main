package ooptassignment;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

//edit table such as edit info of the table (add, delete, remove,change)
public class EditTheTable {
    public void editTable(tables[] allTable)
    {
        if(allTable[0] != null){
            Scanner scan = new Scanner(System.in);
            int choice;
            do{
                //show all tables
                System.out.println("\t\t\t\tTable Details");
                System.out.println("============================================================================");
                System.out.println("No " + "||" + "  Condition " + "||" + " Total Head " + "||" + " Adult " + "||" + " Children " + "||" + " Elder " + "||" + " Combo Set " + "||");
                for(int i = 0; i < 15; i++){
                    System.out.printf("%2d || %10s || %10d || %5d || %8d || %5d || %10c ||\n" ,allTable[i].getTableNo(), allTable[i].getOccupy(), allTable[i].getPersonCount(),
                            allTable[i].getAdultCount() , allTable[i].getChildCount(), allTable[i].getElderCount(), allTable[i].getComboSet());
                }
                System.out.println("============================================================================");
                //ask to edit what
                System.out.print("Select what you want to edit:\n");
                System.out.print("=============================\n");
                System.out.print("1. Condition\n");
                System.out.print("2. Adult\n");
                System.out.print("3. Children\n");
                System.out.print("4. Elder\n");
                System.out.print("5. Combo Set\n");
                System.out.print("6. Exit\n"); 
                System.out.print("=============================\n");
                choice = scan.nextInt();           
                switch (choice) {
                    case 1:
                       editCondition(allTable);
                       break;
                    case 2:
                       editAdult(allTable);
                       break;
                    case 3:
                       editChildren(allTable);
                       break;
                    case 4:
                       editElder(allTable);
                       break;
                    case 5:
                       editComboSet(allTable);
                       break;
                    case 6:
                       System.out.print("Back to staff menu...");
                       return;
                    default:
                        System.out.println("That wasn't a choice...");
               }

            }while(choice != 6);
        }else{
            System.out.println("The system have 0 data...");
        }
    }
    public static void editCondition(tables[] allTable){
        int scanTableNo = 0;
        Scanner scan = new Scanner(System.in);
        boolean error, tableIsEmpty, stringNoError, confirmChange = false;
        String inputInfo;
        char confirm, confirm1, confirm2;
        do{
            //scan tableNo
            do{     
                System.out.print("Which table would you like to edit? (1-15) >>");
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
            String selectedTable = allTable[scanTableNo-1].getOccupy();
            if(!selectedTable.equalsIgnoreCase("Empty")){
                do{    
                    System.out.println("The current table condition is "+selectedTable+" what would you like to change to?");
                    System.out.print("Select the table info (Booking)/(Occupy)/(Empty): ");
                    inputInfo = scan.nextLine();                           
                    if(inputInfo.equalsIgnoreCase("Booking") || inputInfo.equalsIgnoreCase("Occupy") || inputInfo.equalsIgnoreCase("Empty")){
                        if(inputInfo.equalsIgnoreCase("Booking")){
                            inputInfo = "Booking";
                        }else if(inputInfo.equalsIgnoreCase("Occupy")){
                            inputInfo = "Occupy";
                        }else{
                            inputInfo = "Empty";
                        }
                        if(!inputInfo.equals(selectedTable))
                        {
                            //confirmation 
                            do{
                                System.out.print("Confirm change the value? (Y/N): ");
                                confirm = scan.next().charAt(0);   
                                scan.nextLine();
                                confirm = Character.toUpperCase(confirm);                
                                if(!(confirm == 'Y' || confirm == 'N')){
                                    System.out.println("Just Enter Y to confirm and N to not.");
                                }
                            }while(!(confirm == 'Y' || confirm == 'N'));
                            if(confirm == 'Y'){
                                stringNoError = true;
                                confirmChange = true;
                            }else{
                                System.out.println("Value did not edited.");
                                //ask continue or quit
                                do{
                                    System.out.print("Would like to continue edit or exit (Y/N): ");
                                    confirm1 = scan.next().charAt(0);   
                                    scan.nextLine();
                                    confirm1 = Character.toUpperCase(confirm1);                
                                    if(!(confirm1 == 'Y' || confirm1 == 'N')){
                                        System.out.println("Just Enter Y to continue edit and N to not.");
                                    }
                                }while(!(confirm1 == 'Y' || confirm1 == 'N'));
                                if(confirm1 == 'Y'){
                                    stringNoError = false;
                                }else{
                                    stringNoError = true;
                                    break;
                                }
                            }
                        }else{
                            stringNoError = false;
                            System.out.println("The info you input was the same as the old value.");
                        }
                    }else{
                        stringNoError = false;   
                    }
                    //set the new value
                    if(confirmChange){
                        allTable[scanTableNo-1].setOccupy(inputInfo);
                        //set the table time if occupy
                        //printtableInfo
                        if(inputInfo.equalsIgnoreCase("Occupy")){
                            System.out.printf("\nThe table No.%2d had starts until ", scanTableNo);
                            LocalTime myTime = LocalTime.now();
                            LocalTime twoHoursLater = myTime.plusHours(2);
                            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
                            System.out.println(twoHoursLater.format(dateTimeFormatter));
                            String tableTimeLimit = twoHoursLater.format(dateTimeFormatter);
                            System.out.println("Press enter to continue");
                            String pressToContinue = scan.nextLine();
                            allTable[scanTableNo-1].setTableTime(tableTimeLimit);
                        }
                        if(inputInfo.equalsIgnoreCase("Empty")){
                            allTable[scanTableNo-1].setPersonCount(0);
                            allTable[scanTableNo-1].setAdultCount(0);
                            allTable[scanTableNo-1].setChildCount(0);
                            allTable[scanTableNo-1].setElderCount(0);         
                            allTable[scanTableNo-1].setComboSet('-');
                            allTable[scanTableNo-1].setTableTime("-");
                        }
                        if(inputInfo.equalsIgnoreCase("Booking")){
                            allTable[scanTableNo-1].setTableTime("-");
                        }
                    }
                }while(!(stringNoError));
                tableIsEmpty = false;
            }else{
                //confirmation 
                do{
                    System.out.print("The table is empty would you like to continue? (Y/N)");
                    confirm2 = scan.next().charAt(0);   
                    scan.nextLine();
                    confirm2 = Character.toUpperCase(confirm2);                
                    if(!(confirm2 == 'Y' || confirm2 == 'N')){
                        System.out.println("Just enter Y to continue and N to not.");
                    }
                }while(!(confirm2 == 'Y' || confirm2 == 'N'));
                if(confirm2 == 'Y'){
                    tableIsEmpty = true;
                }else{
                    tableIsEmpty = false;
                }
            }
        }while(tableIsEmpty);
        
    }
    
    public static void editAdult(tables[] allTable){
        int scanTableNo = 0;
        Scanner scan = new Scanner(System.in);
        boolean error, tableIsEmpty, intNoError, confirmChange = false;
        int inputInfo;
        char confirm, confirm1, confirm2, confirm3;
        do{
            //scan tableNo
            do{     
                System.out.print("Which table would you like to edit? (1-15) >>");
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
            //read the old value
            String selectedTable = allTable[scanTableNo-1].getOccupy();
            int showAdultC = allTable[scanTableNo-1].getAdultCount();
            int showChildC = allTable[scanTableNo-1].getChildCount();
            int showElderC = allTable[scanTableNo-1].getElderCount();
            if(!selectedTable.equalsIgnoreCase("Empty")){
                do{    
                    System.out.printf("The old adult value is %d.\n", showAdultC);
                    System.out.print("Enter to edit adult number : ");
                    inputInfo = scan.nextInt();  
                    if((showChildC + showElderC + inputInfo) <= 8){                       
                        if(inputInfo != showAdultC)
                        {
                            //confirmation 
                            do{
                                System.out.print("Confirm change the value? (Y/N): ");
                                confirm = scan.next().charAt(0);   
                                scan.nextLine();
                                confirm = Character.toUpperCase(confirm);                
                                if(!(confirm == 'Y' || confirm == 'N')){
                                    System.out.println("Just Enter Y to confirm and N to not.");
                                }
                            }while(!(confirm == 'Y' || confirm == 'N'));
                            if(confirm == 'Y'){
                                intNoError = true;
                                confirmChange = true;
                            }else{
                                System.out.println("Value did not edited.");
                                //ask continue or quit
                                do{
                                    System.out.print("Would like to continue edit or exit (Y/N): ");
                                    confirm1 = scan.next().charAt(0);   
                                    scan.nextLine();
                                    confirm1 = Character.toUpperCase(confirm1);                
                                    if(!(confirm1 == 'Y' || confirm1 == 'N')){
                                        System.out.println("Just Enter Y to continue edit and N to not.");
                                    }
                                }while(!(confirm1 == 'Y' || confirm1 == 'N'));
                                if(confirm1 == 'Y'){
                                    intNoError = false;
                                }else{
                                    intNoError = true;
                                    break;
                                }
                            }
                        }else{
                            intNoError = false;
                            System.out.println("The value you input is the same as old value.");
                        }
                    }else{
                        System.out.println("The table can't fit in all the people please enter the value again.");
                        //ask continue or quit
                            do{
                                System.out.print("Would like to continue edit or exit (Y/N): ");
                                confirm3 = scan.next().charAt(0);   
                                scan.nextLine();
                                confirm3 = Character.toUpperCase(confirm3);                
                                if(!(confirm3 == 'Y' || confirm3 == 'N')){
                                    System.out.println("Just Enter Y to continue edit and N to not.");
                                }
                            }while(!(confirm3 == 'Y' || confirm3 == 'N'));
                            if(confirm3 == 'Y'){
                                intNoError = false;
                            }else{
                                intNoError = true;
                                break;
                            } 
                    }
                    //set the new value
                    if(confirmChange){
                        allTable[scanTableNo-1].setAdultCount(inputInfo);
                        int newValue = showChildC + showElderC + inputInfo;
                        allTable[scanTableNo-1].setPersonCount(newValue);
                    }
                }while(!(intNoError));
                tableIsEmpty = false;
            }else{
                //confirmation 
                do{
                    System.out.print("The table is empty would you like to continue? (Y/N)");
                    confirm2 = scan.next().charAt(0);   
                    scan.nextLine();
                    confirm2 = Character.toUpperCase(confirm2);                
                    if(!(confirm2 == 'Y' || confirm2 == 'N')){
                        System.out.println("Just enter Y to continue and N to not.");
                    }
                }while(!(confirm2 == 'Y' || confirm2 == 'N'));
                if(confirm2 == 'Y'){
                    tableIsEmpty = true;
                }else{
                    tableIsEmpty = false;
                }
            }
        }while(tableIsEmpty);
    }
    
    public static void editChildren(tables[] allTable){
        int scanTableNo = 0;
        Scanner scan = new Scanner(System.in);
        boolean error, tableIsEmpty, intNoError, confirmChange = false;
        int inputInfo;
        char confirm, confirm1, confirm2, confirm3;
        do{
            //scan tableNo
            do{     
                System.out.print("Which table would you like to edit? (1-15) >>");
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
            //read the old value
            String selectedTable = allTable[scanTableNo-1].getOccupy();
            int showAdultC = allTable[scanTableNo-1].getAdultCount();
            int showChildC = allTable[scanTableNo-1].getChildCount();
            int showElderC = allTable[scanTableNo-1].getElderCount();
            if(!selectedTable.equalsIgnoreCase("Empty")){
                do{    
                    System.out.printf("The current Child value is %d.\n", showChildC);
                    System.out.print("Enter to edit child number : ");
                    inputInfo = scan.nextInt();  
                    if((showAdultC + showElderC + inputInfo) <= 8){                       
                        if(inputInfo != showChildC)
                        {
                            //confirmation 
                            do{
                                System.out.print("Confirm change the value? (Y/N): ");
                                confirm = scan.next().charAt(0);   
                                scan.nextLine();
                                confirm = Character.toUpperCase(confirm);                
                                if(!(confirm == 'Y' || confirm == 'N')){
                                    System.out.println("Just Enter Y to confirm and N to not.");
                                }
                            }while(!(confirm == 'Y' || confirm == 'N'));
                            if(confirm == 'Y'){
                                intNoError = true;
                                confirmChange = true;
                            }else{
                                System.out.println("Value did not edited.");
                                //ask continue or quit
                                do{
                                    System.out.print("Would like to continue edit or exit (Y/N): ");
                                    confirm1 = scan.next().charAt(0);   
                                    scan.nextLine();
                                    confirm1 = Character.toUpperCase(confirm1);                
                                    if(!(confirm1 == 'Y' || confirm1 == 'N')){
                                        System.out.println("Just Enter Y to continue edit and N to not.");
                                    }
                                }while(!(confirm1 == 'Y' || confirm1 == 'N'));
                                if(confirm1 == 'Y'){
                                    intNoError = false;
                                }else{
                                    intNoError = true;
                                    break;
                                }
                            }
                        }else{
                            intNoError = false;
                            System.out.println("The value you input is the same as old value.");
                        }
                    }else{
                        System.out.println("The table can't fit in all the people please enter the value again.");
                        //ask continue or quit
                            do{
                                System.out.print("Would like to continue edit or exit (Y/N): ");
                                confirm3 = scan.next().charAt(0);   
                                scan.nextLine();
                                confirm3 = Character.toUpperCase(confirm3);                
                                if(!(confirm3 == 'Y' || confirm3 == 'N')){
                                    System.out.println("Just Enter Y to continue edit and N to not.");
                                }
                            }while(!(confirm3 == 'Y' || confirm3 == 'N'));
                            if(confirm3 == 'Y'){
                                intNoError = false;
                            }else{
                                intNoError = true;
                                break;
                            } 
                    }
                    //set the new value
                    if(confirmChange){
                        allTable[scanTableNo-1].setChildCount(inputInfo);
                        int newValue = showAdultC + showElderC + inputInfo;
                        allTable[scanTableNo-1].setPersonCount(newValue);
                    }
                }while(!(intNoError));
                tableIsEmpty = false;
            }else{
                //confirmation 
                do{
                    System.out.print("The table is empty would you like to continue? (Y/N)");
                    confirm2 = scan.next().charAt(0);   
                    scan.nextLine();
                    confirm2 = Character.toUpperCase(confirm2);                
                    if(!(confirm2 == 'Y' || confirm2 == 'N')){
                        System.out.println("Just enter Y to continue and N to not.");
                    }
                }while(!(confirm2 == 'Y' || confirm2 == 'N'));
                if(confirm2 == 'Y'){
                    tableIsEmpty = true;
                }else{
                    tableIsEmpty = false;
                }
            }
        }while(tableIsEmpty);
    }
    
    public static void editElder(tables[] allTable){
        int scanTableNo = 0;
        Scanner scan = new Scanner(System.in);
        boolean error, tableIsEmpty, intNoError, confirmChange = false;
        int inputInfo;
        char confirm, confirm1, confirm2, confirm3;
        do{
            //scan tableNo
            do{     
                System.out.print("Which table would you like to edit? (1-15) >>");
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
            //read the old value
            String selectedTable = allTable[scanTableNo-1].getOccupy();
            int showAdultC = allTable[scanTableNo-1].getAdultCount();
            int showChildC = allTable[scanTableNo-1].getChildCount();
            int showElderC = allTable[scanTableNo-1].getElderCount();
            if(!selectedTable.equalsIgnoreCase("Empty")){
                do{    
                    System.out.printf("The current Elder value is %d.\n", showElderC);
                    System.out.print("Enter to edit elder number : ");
                    inputInfo = scan.nextInt();  
                    if((showAdultC + showChildC + inputInfo) <= 8){                       
                        if(inputInfo != showElderC)
                        {
                            //confirmation 
                            do{
                                System.out.print("Confirm change the value? (Y/N): ");
                                confirm = scan.next().charAt(0);   
                                scan.nextLine();
                                confirm = Character.toUpperCase(confirm);                
                                if(!(confirm == 'Y' || confirm == 'N')){
                                    System.out.println("Just Enter Y to confirm and N to not.");
                                }
                            }while(!(confirm == 'Y' || confirm == 'N'));
                            if(confirm == 'Y'){
                                intNoError = true;
                                confirmChange = true;
                            }else{
                                System.out.println("Value did not edited.");
                                //ask continue or quit
                                do{
                                    System.out.print("Would like to continue edit or exit (Y/N): ");
                                    confirm1 = scan.next().charAt(0);   
                                    scan.nextLine();
                                    confirm1 = Character.toUpperCase(confirm1);                
                                    if(!(confirm1 == 'Y' || confirm1 == 'N')){
                                        System.out.println("Just Enter Y to continue edit and N to not.");
                                    }
                                }while(!(confirm1 == 'Y' || confirm1 == 'N'));
                                if(confirm1 == 'Y'){
                                    intNoError = false;
                                }else{
                                    intNoError = true;
                                    break;
                                }
                            }
                        }else{
                            intNoError = false;
                            System.out.println("The value you input is the same as old value.");
                        }
                    }else{
                        System.out.println("The table can't fit in all the people please enter the value again.");
                        //ask continue or quit
                            do{
                                System.out.print("Would like to continue edit or exit (Y/N): ");
                                confirm3 = scan.next().charAt(0);   
                                scan.nextLine();
                                confirm3 = Character.toUpperCase(confirm3);                
                                if(!(confirm3 == 'Y' || confirm3 == 'N')){
                                    System.out.println("Just Enter Y to continue edit and N to not.");
                                }
                            }while(!(confirm3 == 'Y' || confirm3 == 'N'));
                            if(confirm3 == 'Y'){
                                intNoError = false;
                            }else{
                                intNoError = true;
                                break;
                            } 
                    }
                    //set the new value
                    if(confirmChange){
                        allTable[scanTableNo-1].setElderCount(inputInfo);
                        int newValue = showAdultC + showChildC + inputInfo;
                        allTable[scanTableNo-1].setPersonCount(newValue);
                    }
                }while(!(intNoError));
                tableIsEmpty = false;
            }else{
                //confirmation 
                do{
                    System.out.print("The table is empty would you like to continue? (Y/N)");
                    confirm2 = scan.next().charAt(0);   
                    scan.nextLine();
                    confirm2 = Character.toUpperCase(confirm2);                
                    if(!(confirm2 == 'Y' || confirm2 == 'N')){
                        System.out.println("Just enter Y to continue and N to not.");
                    }
                }while(!(confirm2 == 'Y' || confirm2 == 'N'));
                if(confirm2 == 'Y'){
                    tableIsEmpty = true;
                }else{
                    tableIsEmpty = false;
                }
            }
        }while(tableIsEmpty);
    }
    
    public static void editComboSet(tables[] allTable){
        int scanTableNo = 0;
        Scanner scan = new Scanner(System.in);
        boolean error, tableIsEmpty, charNoError, confirmChange = false, trueSet;
        char inputInfo;
        char confirm, confirm1, confirm2, confirm3;
        ArrayList<Product> checkComboSet = Product.getProductList();
        do{
            //scan tableNo
            do{     
                System.out.print("Which table would you like to edit? (1-15) >>");
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
            //read the old value
            String selectedTable = allTable[scanTableNo-1].getOccupy();
            char showComboSet = allTable[scanTableNo-1].getComboSet();
            if(!selectedTable.equalsIgnoreCase("Empty")){
                do{    
                    System.out.println("The current Combo Set is "+showComboSet);
                    System.out.print("Enter to edit Combo Set : ");
                    inputInfo = scan.next().charAt(0); 
                    scan.nextLine();
                    inputInfo = Character.toUpperCase(inputInfo);
                    trueSet = true;
                    for(int i = 0; i < checkComboSet.size(); i++){
                            if(inputInfo == checkComboSet.get(i).getProductID()){                             
                                trueSet = true;
                                break;
                            }else{
                                trueSet = false;
                            }
                        }
                        if(trueSet == false){
                            System.out.println("Select one Combo Set that exist.");
                        }
                    if(trueSet){                       
                        if(inputInfo != showComboSet)
                        {
                            //confirmation 
                            do{
                                System.out.print("Confirm change the value? (Y/N): ");
                                confirm = scan.next().charAt(0);   
                                scan.nextLine();
                                confirm = Character.toUpperCase(confirm);                
                                if(!(confirm == 'Y' || confirm == 'N')){
                                    System.out.println("Just Enter Y to confirm and N to not.");
                                }
                            }while(!(confirm == 'Y' || confirm == 'N'));
                            if(confirm == 'Y'){
                                charNoError = true;
                                confirmChange = true;
                            }else{
                                System.out.println("Value did not edited.");
                                //ask continue or quit
                                do{
                                    System.out.print("Would like to continue edit or exit (Y/N): ");
                                    confirm1 = scan.next().charAt(0);   
                                    scan.nextLine();
                                    confirm1 = Character.toUpperCase(confirm1);                
                                    if(!(confirm1 == 'Y' || confirm1 == 'N')){
                                        System.out.println("Just Enter Y to continue edit and N to not.");
                                    }
                                }while(!(confirm1 == 'Y' || confirm1 == 'N'));
                                if(confirm1 == 'Y'){
                                    charNoError = false;
                                }else{
                                    charNoError = true;
                                    break;
                                }
                            }
                        }else{
                            charNoError = false;
                            System.out.println("The value you input is the same as old value.");
                        }
                    }else{
                        System.out.println("Invalid Input combo Set");
                        //ask continue or quit
                            do{
                                System.out.print("Would like to continue edit or exit (Y/N): ");
                                confirm3 = scan.next().charAt(0);   
                                scan.nextLine();
                                confirm3 = Character.toUpperCase(confirm3);                
                                if(!(confirm3 == 'Y' || confirm3 == 'N')){
                                    System.out.println("Just Enter Y to continue edit and N to not.");
                                }
                            }while(!(confirm3 == 'Y' || confirm3 == 'N'));
                            if(confirm3 == 'Y'){
                                charNoError = false;
                            }else{
                                charNoError = true;
                                break;
                            } 
                    }
                    //set the new value
                    if(confirmChange){
                        allTable[scanTableNo-1].setComboSet(inputInfo);
                    }
                }while(!(charNoError));
                tableIsEmpty = false;
            }else{
                //confirmation 
                do{
                    System.out.print("The table is empty would you like to continue? (Y/N)");
                    confirm2 = scan.next().charAt(0);   
                    scan.nextLine();
                    confirm2 = Character.toUpperCase(confirm2);                
                    if(!(confirm2 == 'Y' || confirm2 == 'N')){
                        System.out.println("Just enter Y to continue and N to not.");
                    }
                }while(!(confirm2 == 'Y' || confirm2 == 'N'));
                if(confirm2 == 'Y'){
                    tableIsEmpty = true;
                }else{
                    tableIsEmpty = false;
                }
            }
        }while(tableIsEmpty);
    }
}
