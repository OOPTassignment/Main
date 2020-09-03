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
        initial(); //get all the initialed member list
        
        boolean checkSelect; //for checking user input
        int select = 0;
        do{
            System.out.println("Membership Functions"); //membership function menu
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
            }catch(Exception ez){ //if user input character
                System.out.println("Please enter number above!\n");
                checkSelect = false;
                scan.nextLine();
            }
    
        }while(select != 0 || checkSelect == false); //when user input not 0 or invalid input will loop again
    }
    
    public static void addMember(){
        Scanner scan = new Scanner(System.in);
        LocalDate memDate = LocalDate.now(); //allocate today date
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate expireDate = memDate.plusMonths(6); //auto count 6 month later for expire date
        
        char continueAdd, confirmAdd;
        
        do{
            String memberId; 
            System.out.print("\nEnter new member's name: ");
            String memName = scan.nextLine(); //input new name
            
            String memPhoneNum;
            boolean checkPhoneNum; //for checking phone number validation
            do{
                checkPhoneNum = true;
                System.out.print("Enter new member's phone number: ");
                memPhoneNum = scan.nextLine();
                
                for(int i = 0; i < memPhoneNum.length(); i++){ //check input is greater than 11 or lesser than 9 digit or not digit
                    if(memPhoneNum.length() >= 11 || memPhoneNum.length() <= 9 || !(Character.isDigit(memPhoneNum.charAt(i)))){
                        checkPhoneNum = false;
                    }
                }
                if(checkPhoneNum == false){
                    System.out.println("Please enter a valid Phone Number!"); //print error message
                }
            }while(checkPhoneNum == false);
                    
            int memVisit = 1; //new member first day visit
            
            String memJoinedDate = memDate.format(formatDate); //current date 
            String memExpireDate = expireDate.format(formatDate); //member expire date
            
            int array = memberList.size() - 1; //initial memberList array
            int unit = memberList.get(array).getMemberId().charAt(4); //get memberid last number
            int hundred = memberList.get(array).getMemberId().charAt(2); //get memberid third last number
            int tenth = memberList.get(array).getMemberId().charAt(3); //get memberid second last number
            int convertUnit = Character.getNumericValue(unit);          
            int convertTenth = Character.getNumericValue(tenth);    //convert character to numeric value
            int convertHundred = Character.getNumericValue(hundred);
            
            convertUnit = convertUnit + 1; //for new member id 
            if(convertUnit == 10){ //when the digit more than just a unit 
                convertTenth++; //tenth will add 1
                convertUnit = 0; //change back to 0
            }
            if(convertTenth == 10){ //when the digit more than tenth
                convertHundred++; //hundred value add 1
                convertTenth = 0; //tenth change back to 0
            }
            memberId = "MC" + convertHundred + convertTenth + convertUnit; //auto generate member ID
            
            do{
                System.out.print("Confirm to add? (Y/N)>"); //confirmation to add new member
                confirmAdd = scan.next().charAt(0);
                scan.nextLine();
                confirmAdd = Character.toUpperCase(confirmAdd);
                if(!(confirmAdd == 'Y' || confirmAdd == 'N')){
                    System.out.println("Please 'Y' or 'N' to continue");
                }
            }while(!(confirmAdd == 'Y' || confirmAdd == 'N'));
            
            if(confirmAdd == 'Y'){
                memberList.add(new Member(memberId, memName, memPhoneNum, memVisit, memJoinedDate, memExpireDate));
                System.out.println("Successfully Added New Member."); //insert value to new arrayList
            }else
                System.out.println("Fail to Add New Member."); //when user uncomfirm to add new member
           
            do{
                System.out.print("Add Another? (Y/N)>"); //ask for new member
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
        
        displayMember(); //show all member in the arrayList
        
        System.out.print("Enter the Member Id you want to Modify : "); //input the member id that user want to modify
        String inputId = scan.nextLine();
        int countId = 0;
        for(int i = 0; i < memberList.size(); i++){
            if(inputId.equalsIgnoreCase(memberList.get(i).getMemberId())){ //check member id match with the use input
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
                        select = scan.nextInt(); //select which item to modify
                        scan.nextLine();
                        System.out.println();
                        checkSelect = true;
                        switch(select){
                            case 1: System.out.print("Enter a new Name : ");
                                    String newName = scan.nextLine();

                                    if(confirmChanges() == 'Y'){//ask confirm change
                                        memberList.get(i).setName(newName); //pass in new name that entered by user
                                    }
                                break;
                            case 2: String newPhoneNum;
                                    boolean checkPhoneNum;
                                    do{
                                        checkPhoneNum = true;
                                        System.out.print("Enter new member's phone number: ");
                                        newPhoneNum = scan.nextLine();

                                        for(int j = 0; j < newPhoneNum.length(); j++){ //check phone validation
                                            if(newPhoneNum.length() >= 11 || newPhoneNum.length() <= 9 || !(Character.isDigit(newPhoneNum.charAt(j)))){
                                                checkPhoneNum = false;
                                            }
                                        }
                                        if(checkPhoneNum == false){
                                            System.out.println("Please enter a valid Phone Number!");
                                        }
                                    }while(checkPhoneNum == false);
                                    
                                    if(confirmChanges() == 'Y'){ //ask confirm change
                                        memberList.get(i).setPhoneNum(newPhoneNum); //pass in new phone number that entered by user
                                    }
                                break;
                            case 3: boolean catchInt;
                                    int newVisitDays = 0;
                                    do{
                                        try{
                                            System.out.print("Enter a new Days Visited : ");
                                            newVisitDays = scan.nextInt();//input the number of days visit to change
                                            catchInt = true;
                                        }catch(Exception ex){
                                            System.out.println("Please enter valid number of days");
                                            catchInt = false; //when user enter character value
                                            scan.nextLine();
                                        }   
                                        if(newVisitDays < 0){
                                            System.out.println("Please enter valid number of days"); //when the input is < 0
                                        }
                                    }while(!(catchInt) || newVisitDays < 0);
                                    if(confirmChanges() == 'Y'){ //pass in new days visit that entered by user
                                        memberList.get(i).setDaysVisit(newVisitDays);
                                    }
                                break;
                            case 0: return; //back to staff menu
                            default: System.out.println("Please enter number above!\n");
                        }
                    }catch(Exception ep){
                        System.out.println("Please enter number above!\n");
                        checkSelect = false;
                        scan.nextLine();
                    }
                    
                }while(select != 0 || checkSelect == false); //when the input is nt 0 or invalid input
                countId++; //check the id is valid or not
            } 
        }
        if(countId == 0){ //display error message when input doesnt match
            System.out.println("Member ID entered doesn't exist!\n");
        }
    }

    
    public static void removeMember(){
        Scanner scan = new Scanner(System.in);
        
        displayMember();
        System.out.print("Enter the member id you want to remove : ");
        String inputId = scan.nextLine(); //input which id user want to remove
        
        int countId = 0;
        for(int i = 0; i < memberList.size(); i++){
            if(inputId.equalsIgnoreCase(memberList.get(i).getMemberId())){ //if the input match with the id
                char confirmRemove;
                do{
                    System.out.print("Confirm to Remove? (Y/N)>"); //ask to confirm
                    confirmRemove = scan.next().charAt(0);
                    scan.nextLine();
                    confirmRemove = Character.toUpperCase(confirmRemove);
                    if(!(confirmRemove == 'Y' || confirmRemove == 'N')){
                        System.out.println("Please 'Y' or 'N' to continue");
                    }
                     
                }while(!(confirmRemove == 'Y' || confirmRemove == 'N'));
                
                if (confirmRemove == 'Y'){
                    memberList.remove(i); //remove member from the array list
                    System.out.println("Succssfully Remove Member \n");
                    break;
                }
                countId++; //check if the any id matched
            }
        }
        if(countId == 0){  //display error message when input doesnt match
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
                scan.nextLine(); //select which item to search the member
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

                                for(int j = 0; j < searchPhone.length(); j++){ //phone number validation
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
                                    catchInt = false; //when user enter character value
                                    scan.nextLine();
                                }   
                                if(searchDaysVisit < 0){ //input < 0
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
        for(int i = 0; i < memberList.size(); i++){ //display all member 
   
            System.out.printf("\n%-6s%-15s%12s%8d%8s%-15s%s",memberList.get(i).getMemberId(), memberList.get(i).getName(), memberList.get(i).getPhoneNum()
                    , memberList.get(i).getDaysVisit(),"", memberList.get(i).getDateJoined(), memberList.get(i).getExpireDate());
 
        }
        System.out.println("\n");
}
    
    public static void displayMember(String searchIdNamePhone){
        int countMember = 0; //check if the input matched
        System.out.printf("\n%25sMember List\n", "");
        System.out.printf("ID%4sName%11sPhone Number%2sDays Visited%2sDate Joined\tExpire Date", "", "", "", "");
        System.out.printf("\n===========================================================================");
        for(int i = 0; i < memberList.size(); i++){ //display by matching with the input 
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
        if(countMember == 0){ //display error message when input unexisted
            System.out.println("\nNo Matched Found");
        } 
        System.out.println();
    }
    
    public static void displayMember(int searchDaysVisit){
            int countMember = 0;
            System.out.printf("\n%25sMember List\n", "");
            System.out.printf("ID%4sName%11sPhone Number%2sDays Visited%2sDate Joined\tExpire Date", "", "", "", "");
            System.out.printf("\n===========================================================================");
        for(int i = 0; i < memberList.size(); i++){ //check input matched or not
            if(memberList.get(i).getDaysVisit() == searchDaysVisit){
                System.out.printf("\n%-6s%-15s%12s%8d%8s%-15s%s",memberList.get(i).getMemberId(), memberList.get(i).getName(), memberList.get(i).getPhoneNum()
                    , memberList.get(i).getDaysVisit(),"", memberList.get(i).getDateJoined(), memberList.get(i).getExpireDate());
                countMember++;
            }
        
        }    
        if(countMember == 0){ //display error message when input unexisted
            System.out.println("\nNo Matched Found");
        } 
        System.out.println("\n");  
    }
    
    public static char confirmChanges(){
        Scanner scan = new Scanner(System.in);
        char confirmChange;
        
        do{ //a method that ask user to confirm make changes for modify module
                System.out.print("Confirm Changes? (Y/N)>");
                confirmChange = scan.next().charAt(0);
                scan.nextLine();
                System.out.print("\n");
                confirmChange = Character.toUpperCase(confirmChange);
                if(!(confirmChange == 'Y' || confirmChange == 'N')){
                    System.out.println("Please 'Y' or 'N' to continue");
                }
        }while(!(confirmChange == 'Y' || confirmChange == 'N'));
        
        return confirmChange; //return Y or N
    }
}
