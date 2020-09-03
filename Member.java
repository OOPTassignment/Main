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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Membership extends Member{
    
    
    public void Membership (){
        Scanner scan = new Scanner(System.in);
        initial();
        
        boolean checkSelect;
        int select = 0;
        do{
            System.out.println("Membership Functions");
            System.out.println("====================");
            System.out.println("1 . Add Member");
            System.out.println("2 . Modify Member");
            System.out.println("3 . Remove Member");
            System.out.println("4 . Display Member");
            System.out.println("5 . Search Member");
            System.out.println("0 . Back to Staff Menu");
            System.out.print("Enter the selection : ");
            try{
                select = scan.nextInt();
                scan.nextLine();
                checkSelect = true;
                switch(select){
                    case 1: addMember(); break;
                    case 2: modifyMember(); break;
                    case 3: removeMember(); break;
                    case 4: displayMember(); break;
                    case 5: searchMember(); break;
                    case 0: return; 
                    default: System.out.println("Please enter number above!\n");
                }
            }catch(Exception ez){
                System.out.println("Please enter number above!\n");
                checkSelect = false;
                scan.nextLine();
            }
    
        }while(select != 0 || checkSelect == false);
    }
    
    public static void addMember(){
        Scanner scan = new Scanner(System.in);
        LocalDate memDate = LocalDate.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate expireDate = memDate.plusMonths(6);
        
        char continueAdd, confirmAdd;
        
        do{
            String memberId; 
            System.out.print("\nEnter new member's name: ");
            String memName = scan.nextLine();
            
            String memPhoneNum;
            boolean checkPhoneNum;
            do{
                checkPhoneNum = true;
                System.out.print("Enter new member's phone number: ");
                memPhoneNum = scan.nextLine();
                
                for(int i = 0; i < memPhoneNum.length(); i++){
                    if(memPhoneNum.length() >= 11 || memPhoneNum.length() <= 9 || !(Character.isDigit(memPhoneNum.charAt(i)))){
                        checkPhoneNum = false;
                    }
                }
                if(checkPhoneNum == false){
                    System.out.println("Please enter a valid Phone Number!");
                }
            }while(checkPhoneNum == false);
                    
            int memVisit = 0;
            boolean catchInt; //check number of days error
     
            do{
                System.out.print("Enter days member had visit: ");
                try{
                    memVisit = scan.nextInt();
                    scan.nextLine();
                    catchInt = true;
                }catch(Exception ex){
                    System.out.println("Please enter valid number of days");
                    catchInt = false;
                    scan.nextLine();
                }
                if(memVisit < 0){
                    System.out.println("Number of days cannot be negative");
                }
            }while(!(catchInt) || memVisit < 0);
            
            String memJoinedDate = memDate.format(formatDate); //current date 
            String memExpireDate = expireDate.format(formatDate); //member expire date
            
            int array = memberList.size() - 1;
            int unit = memberList.get(array).getMemberId().charAt(4);
            int hundred = memberList.get(array).getMemberId().charAt(2);
            int tenth = memberList.get(array).getMemberId().charAt(3); 
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
            memberId = "MC" + convertHundred + convertTenth + convertUnit;
            
            do{
                System.out.print("Confirm to add? (Y/N)>");
                confirmAdd = scan.next().charAt(0);
                scan.nextLine();
                confirmAdd = Character.toUpperCase(confirmAdd);
                if(!(confirmAdd == 'Y' || confirmAdd == 'N')){
                    System.out.println("Please 'Y' or 'N' to continue");
                }
            }while(!(confirmAdd == 'Y' || confirmAdd == 'N'));
            
            if(confirmAdd == 'Y'){
                memberList.add(new Member(memberId, memName, memPhoneNum, memVisit, memJoinedDate, memExpireDate));
                System.out.println("Successfully Added New Member.");
            }else
                System.out.println("Fail to Add New Member.");
           
            do{
                System.out.print("Add Another? (Y/N)>");
                continueAdd = scan.next().charAt(0);
                scan.nextLine();
                System.out.print("\n");
                continueAdd = Character.toUpperCase(continueAdd);
                if(!(continueAdd == 'Y' || continueAdd == 'N')){
                    System.out.println("Please 'Y' or 'N' to continue");
                }
            }while(!(continueAdd == 'Y' || continueAdd == 'N'));
        }while(continueAdd == 'Y');
    }
    
    public static void modifyMember(){
        Scanner scan = new Scanner(System.in);
        
        displayMember();
        
        System.out.print("Enter the Member Id you want to Modify : ");
        String inputId = scan.nextLine();
        int countId = 0;
        for(int i = 0; i < memberList.size(); i++){
            if(inputId.equalsIgnoreCase(memberList.get(i).getMemberId())){
                boolean checkSelect;
                int select = 0;
                do{
                    System.out.println("\nModify Member Details");
                    System.out.println("=====================");
                    System.out.println("1 . Name");
                    System.out.println("2 . Phone Number");
                    System.out.println("3 . Days Visited");
                    System.out.println("0 . Back to Member Menu");
                    System.out.print("Enter the selection : ");
                    try{
                        select = scan.nextInt();
                        scan.nextLine();
                        System.out.println();
                        checkSelect = true;
                        switch(select){
                            case 1: System.out.print("Enter a new Name : ");
                                    String newName = scan.nextLine();

                                    if(confirmChanges() == 'Y'){
                                        memberList.get(i).setName(newName);
                                    }
                                break;
                            case 2: String newPhoneNum;
                                    boolean checkPhoneNum;
                                    do{
                                        checkPhoneNum = true;
                                        System.out.print("Enter new member's phone number: ");
                                        newPhoneNum = scan.nextLine();

                                        for(int j = 0; j < newPhoneNum.length(); j++){
                                            if(newPhoneNum.length() >= 11 || newPhoneNum.length() <= 9 || !(Character.isDigit(newPhoneNum.charAt(j)))){
                                                checkPhoneNum = false;
                                            }
                                        }
                                        if(checkPhoneNum == false){
                                            System.out.println("Please enter a valid Phone Number!");
                                        }
                                    }while(checkPhoneNum == false);
                                    
                                    if(confirmChanges() == 'Y'){
                                        memberList.get(i).setPhoneNum(newPhoneNum);
                                    }
                                break;
                            case 3: boolean catchInt;
                                    int newVisitDays = 0;
                                    do{
                                        try{
                                            System.out.print("Enter a new Days Visited : ");
                                            newVisitDays = scan.nextInt();
                                            catchInt = true;
                                        }catch(Exception ex){
                                            System.out.println("Please enter valid number of days");
                                            catchInt = false;
                                            scan.nextLine();
                                        }   
                                        if(newVisitDays < 0){
                                            System.out.println("Please enter valid number of days");
                                        }
                                    }while(!(catchInt) || newVisitDays < 0);
                                    if(confirmChanges() == 'Y'){
                                        memberList.get(i).setDaysVisit(newVisitDays);
                                    }
                                break;
                            case 0: return;
                            default: System.out.println("Please enter number above!\n");
                        }
                    }catch(Exception ep){
                        System.out.println("Please enter number above!\n");
                        checkSelect = false;
                        scan.nextLine();
                    }
                    
                }while(select != 0 || checkSelect == false);
                countId++;
            } 
        }
        if(countId == 0){
            System.out.println("Member ID entered doesn't exist!\n");
        }
    }

    
    public static void removeMember(){
        Scanner scan = new Scanner(System.in);
        
        displayMember();
        System.out.print("Enter the member id you want to remove : ");
        String inputId = scan.nextLine();
        
        int countId = 0;
        for(int i = 0; i < memberList.size(); i++){
            if(inputId.equalsIgnoreCase(memberList.get(i).getMemberId())){
                char confirmRemove;
                do{
                    System.out.print("Confirm to Remove? (Y/N)>");
                    confirmRemove = scan.next().charAt(0);
                    scan.nextLine();
                    confirmRemove = Character.toUpperCase(confirmRemove);
                    if(!(confirmRemove == 'Y' || confirmRemove == 'N')){
                        System.out.println("Please 'Y' or 'N' to continue");
                    }
                     
                }while(!(confirmRemove == 'Y' || confirmRemove == 'N'));
                
                if (confirmRemove == 'Y'){
                    memberList.remove(i);
                    System.out.println("Succssfully Remove Member \n");
                    break;
                }
                countId++;
            }
        }
        if(countId == 0){
            System.out.println("Member ID entered doesn't exist!\n");
        }
        
    }
    
    public static void searchMember(){
        Scanner scan = new Scanner(System.in);
        
        boolean checkSelect;
        int select = 0;
        do{
            System.out.println("\nSearch By");
            System.out.println("=========");
            System.out.println("1 . ID");
            System.out.println("2 . Name");
            System.out.println("3 . Phone Number");
            System.out.println("4 . Days Visit");
            System.out.println("0 . Back to Member Menu");
            System.out.print("Enter the selection : ");
            try{
                select = scan.nextInt();
                scan.nextLine();
                checkSelect = true;
                switch(select){
                    case 1: System.out.print("Search by ID : "); 
                            String searchID = scan.nextLine();
                            
                            displayMember(searchID);
                        break;
                    case 2: System.out.print("Search by Name : ");
                            String searchName = scan.nextLine();
                            
                            displayMember(searchName);
                        break;
                    case 3: String searchPhone;
                            boolean checkPhoneNum;
                            do{
                                checkPhoneNum = true;
                                System.out.print("Enter new member's phone number: ");
                                searchPhone = scan.nextLine();

                                for(int j = 0; j < searchPhone.length(); j++){
                                    if(searchPhone.length() >= 11 || searchPhone.length() <= 9 || !(Character.isDigit(searchPhone.charAt(j)))){
                                        checkPhoneNum = false;
                                    }
                                }
                                if(checkPhoneNum == false){
                                    System.out.println("Please enter a valid Phone Number!");
                                }
                            }while(checkPhoneNum == false);
                            
                            displayMember(searchPhone);
                        break;
                    case 4: boolean catchInt;
                            int searchDaysVisit = 0;
                            do{
                                try{
                                    System.out.print("Search by Days Visited : ");
                                    searchDaysVisit = scan.nextInt();
                                    catchInt = true;
                                }catch(Exception ex){
                                    System.out.println("Please enter valid number of days");
                                    catchInt = false;
                                    scan.nextLine();
                                }   
                                if(searchDaysVisit < 0){
                                    System.out.println("Please enter valid number of days");
                                }
                            }while(!(catchInt) || searchDaysVisit < 0);
                      
                            displayMember(searchDaysVisit);
                        break;
                    case 0: return;
                    default: System.out.println("Please enter number above!");
                }
            }catch(Exception ez){
                System.out.println("Please enter number above!\n");
                checkSelect = false;
                scan.nextLine();
            }
    
        }while(select != 0 || checkSelect == false);
        System.out.println("\n");
    } 

public static void displayMember(){
        System.out.printf("\n%25sMember List\n", "");
        System.out.printf("ID%4sName%11sPhone Number%2sDays Visited%2sDate Joined\tExpire Date", "", "", "", "");
        System.out.printf("\n===========================================================================");
        for(int i = 0; i < memberList.size(); i++){
   
            System.out.printf("\n%-6s%-15s%12s%8d%8s%-15s%s",memberList.get(i).getMemberId(), memberList.get(i).getName(), memberList.get(i).getPhoneNum()
                    , memberList.get(i).getDaysVisit(),"", memberList.get(i).getDateJoined(), memberList.get(i).getExpireDate());
 
        }
        System.out.println("\n");
}
    
    public static void displayMember(String searchIdNamePhone){
        int countMember = 0;
        System.out.printf("\n%25sMember List\n", "");
        System.out.printf("ID%4sName%11sPhone Number%2sDays Visited%2sDate Joined\tExpire Date", "", "", "", "");
        System.out.printf("\n===========================================================================");
        for(int i = 0; i < memberList.size(); i++){
            if(memberList.get(i).getMemberId().equalsIgnoreCase(searchIdNamePhone)){
                System.out.printf("\n%-6s%-15s%12s%8d%8s%-15s%s",memberList.get(i).getMemberId(), memberList.get(i).getName(), memberList.get(i).getPhoneNum()
                    , memberList.get(i).getDaysVisit(),"", memberList.get(i).getDateJoined(), memberList.get(i).getExpireDate());
                countMember++;
            }
            if(memberList.get(i).getName().equalsIgnoreCase(searchIdNamePhone)){
                System.out.printf("\n%-6s%-15s%12s%8d%8s%-15s%s",memberList.get(i).getMemberId(), memberList.get(i).getName(), memberList.get(i).getPhoneNum()
                    , memberList.get(i).getDaysVisit(),"", memberList.get(i).getDateJoined(), memberList.get(i).getExpireDate());
                countMember++;
            }
            if(memberList.get(i).getPhoneNum().equalsIgnoreCase(searchIdNamePhone)){
                System.out.printf("\n%-6s%-15s%12s%8d%8s%-15s%s",memberList.get(i).getMemberId(), memberList.get(i).getName(), memberList.get(i).getPhoneNum()
                    , memberList.get(i).getDaysVisit(),"", memberList.get(i).getDateJoined(), memberList.get(i).getExpireDate());
                countMember++;
            }
        }  
        if(countMember == 0){
            System.out.println("\nNo Matched Found");
        } 
        System.out.println();
    }
    
    public static void displayMember(int searchDaysVisit){
            System.out.printf("\n%25sMember List\n", "");
            System.out.printf("ID%4sName%11sPhone Number%2sDays Visited%2sDate Joined\tExpire Date", "", "", "", "");
            System.out.printf("\n===========================================================================");
        for(int i = 0; i < memberList.size(); i++){
            if(memberList.get(i).getDaysVisit() == searchDaysVisit){
                System.out.printf("\n%-6s%-15s%12s%8d%8s%-15s%s",memberList.get(i).getMemberId(), memberList.get(i).getName(), memberList.get(i).getPhoneNum()
                    , memberList.get(i).getDaysVisit(),"", memberList.get(i).getDateJoined(), memberList.get(i).getExpireDate());
            }
        
        }    
        System.out.println("\n");  
    }
    
    public static char confirmChanges(){
        Scanner scan = new Scanner(System.in);
        char confirmChange;
        
        do{
                System.out.print("Confirm Changes? (Y/N)>");
                confirmChange = scan.next().charAt(0);
                scan.nextLine();
                System.out.print("\n");
                confirmChange = Character.toUpperCase(confirmChange);
                if(!(confirmChange == 'Y' || confirmChange == 'N')){
                    System.out.println("Please 'Y' or 'N' to continue");
                }
        }while(!(confirmChange == 'Y' || confirmChange == 'N'));
        
        return confirmChange;
    }
}
