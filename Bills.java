package ooptassignment;

import java.util.Scanner;
import java.util.Scanner;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Bills{
    static int count = 0;
    
    public static void Billing(tables[] table,countSummary[] summarying)
    {
        Scanner scan = new Scanner(System.in);
        Product.initProd();
        ArrayList<Product> ProductList = Product.getProductList(); //CZ PART for updating the product 
        ArrayList<Member> MemberList = Member.getMemberList();
        if(table[0] == null){
                for(int i = 0; i < 15; i++){
                    table[i] = new tables(i+1, "Empty", 0, 0, 0, 0, '-');
                }
        }
        //fill all the summary to empty !!for running the program at the first time
        if(summarying[0] == null){
            for(int i = 0; i < 150; i++){
                summarying[i] = new countSummary(0, 0, 0, 0, 0, 0, 0 ,'-', "-", 0, 0, 0, 0, false);
            }
        } 
        
        //Show all the tables
            System.out.println("\nTable Details");
            System.out.println("=============");
            System.out.println("No " + "||" + "  Condition " + "||" + " Total Head " + "||" + " Adult " + "||" + " Children " + "||" + " Elder " + "||" + " Combo Set " + "||");
            for(int i = 0; i <15; i++){
                System.out.printf("%2d || %10s || %10d || %5d || %8d || %5d || %9c ||\n" ,table[i].getTableNo(), table[i].getOccupy(), table[i].getPersonCount(),
                        table[i].getAdultCount() , table[i].getChildCount(), table[i].getElderCount(), table[i].getComboSet());
            }
            
        //Enter table number   
        int scanTableNumber = 0;
        char confirm;
        boolean no, notEmpty, notBooking, quitBill;
        String checkEmpty, checkBooking = "";        
        do{
            do{
                System.out.print("Select table No which need to make billing (1-15):");
                try
                {            
                    scanTableNumber = scan.nextInt();     
                    no = true;   
                    scan.nextLine();
                }
                catch (Exception ex){
                    System.out.print("Invalid table No input");
                    no = false;
                    scan.nextLine();
                }    
                if(scanTableNumber <=0 || scanTableNumber > 15){
                    System.out.println("The value must in this range 1-15...");
                }
            }while(!no || scanTableNumber <= 0 || scanTableNumber > 15);
            
            checkEmpty = table[scanTableNumber -1].getOccupy(); //Check empty 
            if(!checkEmpty.equals("Empty") && !checkEmpty.equals("Booking")){  //If the table is empty then cannot proceed to the billing 
                notEmpty = true;
                quitBill = false;
            }

            else{
                notEmpty = false;
                
             
                System.out.println("You can't proceed to bill");
                //ask continue or quit
                do{
                    System.out.print("Would like to continue the bill or exit (Y/N): ");
                    confirm = scan.next().charAt(0);   
                    scan.nextLine();
                    confirm = Character.toUpperCase(confirm);                
                    if(!(confirm == 'Y' || confirm == 'N')){
                        System.out.println("Just Enter Y to continue the bill and N to not.");
                    }
                }while(!(confirm == 'Y' || confirm == 'N'));
                if(confirm == 'Y'){
                    quitBill = false;
                }else{
                    quitBill = true;
                    break;
                }

            }
            
        }while(!notEmpty);
        
        Member askMember = new Member();
        boolean member;
        int dayVisit = 0;
        char confirmMember, continueMember;
        String proveID;
        proveID = "Nothing";
        //Start billing
        if(!quitBill){
            char comboSet;
            comboSet = table[scanTableNumber-1].getComboSet();
            
            // got member > 3 days 
            // got member !>3days
            // no member 
            // got 3 methods of calculation
            do{
                System.out.print("Are you a Member? (Y/N)>");
                confirmMember = scan.next().charAt(0);
                scan.nextLine();
                confirmMember = Character.toUpperCase(confirmMember);
                if(!(confirmMember == 'Y' || confirmMember == 'N')){
                    System.out.println("Please 'Y' or 'N' to continue");
                }
            }while(!(confirmMember == 'Y' || confirmMember == 'N'));
            
            //member validation     
            if(confirmMember == 'Y'){
                do{
                    System.out.print("Enter Member ID : "); 
                    proveID = scan.nextLine();
                    member = askMember.isMember(proveID); 
                    continueMember = 'N';
                    if(!member){
                        System.out.print("Invalid member ID!!!");
                        do{
                            System.out.print("Do you want to enter again? (Y/N)>");
                            continueMember = scan.next().charAt(0);
                            scan.nextLine();
                            continueMember = Character.toUpperCase(continueMember);
                            if(!(continueMember == 'Y' || continueMember == 'N')){
                                System.out.println("Please 'Y' or 'N' to continue");
                            }
                        }while(!(continueMember == 'Y' || continueMember == 'N'));
                    }else{
                        dayVisit = askMember.memberVisitNum(proveID);
                    }
                }while(continueMember == 'Y');             
            }else{
                member = false;
            }    
            
            switch(comboSet){
                case 'A': comboA(table,ProductList,MemberList,scanTableNumber,summarying,member,dayVisit,proveID);break; //8 Parameters
                case 'B': comboB(table,ProductList,MemberList,scanTableNumber,summarying,member,dayVisit,proveID);break; 
                case 'C': comboC(table,ProductList,MemberList,scanTableNumber,summarying,member,dayVisit,proveID);break;
                case 'D': comboD(table,ProductList,MemberList,scanTableNumber,summarying,member,dayVisit,proveID);break;
            }   
        }
    }
    public static void displaywhatever(tables[] table,ArrayList<Product> ProductList,countSummary[] summarying, int scanTableNumber){
        
        LocalDate todayDate = LocalDate.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = todayDate.format(formatDate);
        System.out.printf(date);
        System.out.printf("\t\tTable No :%d\n", scanTableNumber); 
        System.out.printf("Set Name:%c\n", table[scanTableNumber-1].getComboSet()); //Read what kind set first 
        if(summarying[count].isIsMember() == true){
            System.out.printf("Member: Yes\n");
        }else{
            System.out.printf("Member: No\n");
        }
        System.out.printf("Category\tNumber of pax\tPrice per person\t Total\n");
        System.out.printf("========\t=============\t================\t ======\n");
        //Display
        if(summarying[count].getCountAdult() !=0){
            double PricePerAdult = summarying[count].getTotalAdult() / summarying[count].getCountAdult();
            System.out.printf("Adult\t%15d\t\t\t%-12.2f\t%7.2f\n",summarying[count].getCountAdult(),PricePerAdult,summarying[count].getTotalAdult());
        }
        if(summarying[count].getCountKid() !=0){
            double PricePerKid = summarying[count].getTotalKid()  / summarying[count].getCountKid();
            System.out.printf("Kid\t%15d\t\t\t%-12.2f\t%7.2f\n",summarying[count].getCountKid(),PricePerKid,summarying[count].getTotalKid());
        }
        if(summarying[count].getCountElder() !=0){
            double PricePerElder = summarying[count].getTotalElder() / summarying[count].getCountElder();
            System.out.printf("Elder\t%15d\t\t\t%-12.2f\t%7.2f\n",summarying[count].getCountElder(),PricePerElder,summarying[count].getTotalElder());
        }
        System.out.printf("Subtotal :%5.2f\n",summarying[count].getSubTotal()); // Subtotal
        //Discount Given
        if(summarying[count].getDayVisited() >= 3 && summarying[count].isIsMember() == true){
            System.out.println("Discount given : 10%");
        }else if(summarying[count].getDayVisited() < 3 && summarying[count].getDayVisited() >= 0 && summarying[count].isIsMember() == true){
            System.out.println("Discount given :  5%");
        }else{
            System.out.println("Discount given :  0%");
        }
        System.out.printf("SST : %-5.2f\n", summarying[count].getSST());
        System.out.printf("--------------------\n");
        System.out.printf("Total :%-5.2f\n",summarying[count].getGrandTotal()); //GrandTotal
        System.out.printf("--------------------\n");

}
    
    
    public static void comboA(tables[] table,ArrayList<Product> ProductList,ArrayList<Member> MemberList,int scanTableNumber,countSummary[] summarying, boolean member, int dayVisit, String proveID){
        int adult, kid, elder;
        double countAdultPrice, countKidPrice,  countElderPrice;
        double SST = 0.05, subTotal = 0,grandTotal = 0;
        double paid = 0, balance = 0;
        
        Scanner scan = new Scanner(System.in);
        boolean figureEnter, quitBalance;
        char confirmEnter;
        adult = table[scanTableNumber-1].getAdultCount();
        kid = table[scanTableNumber-1].getChildCount();
        elder = table[scanTableNumber-1].getElderCount();
        //Count 
        countAdultPrice = adult * ProductList.get(0).getpPriceAdults(); // get(1)
        countKidPrice = kid * ProductList.get(0).getpPriceKids();
        countElderPrice = elder * ProductList.get(0).getpPriceElders();
        subTotal = countAdultPrice + countKidPrice + countElderPrice; //Total up 
        grandTotal = (subTotal * SST) + subTotal; //Total + SST
        //membership
        if(member){
            if(dayVisit >= 3){               
                grandTotal = grandTotal - (grandTotal * 0.1); //member 10% discount
            }else{
                grandTotal = grandTotal - (grandTotal * 0.05); //member discount
            }
        }      
        summarying[count].setCountAdult(adult);
        summarying[count].setCountKid(kid);
        summarying[count].setCountElder(elder);      
        summarying[count].setSST(SST);
        summarying[count].setTotalAdult(countAdultPrice);
        summarying[count].setTotalKid(countKidPrice);
        summarying[count].setTotalElder(countElderPrice);
        summarying[count].setSet('A');
        summarying[count].setIsMember(member);
        if(member){
            summarying[count].setMemberID(proveID);
            summarying[count].setDayVisited(dayVisit);
        }else{
            summarying[count].setMemberID("-");
            summarying[count].setDayVisited(0);
        }
        summarying[count].setSubTotal(subTotal);
        summarying[count].setGrandTotal(grandTotal);
        //Display method!=class
        displaywhatever(table,ProductList,summarying,scanTableNumber);                                        
        //Balance 
        do{
            do{ //Range of the figure
                System.out.printf("Enter the figure to pay :");
                try
                {            
                    paid = scan.nextDouble();     
                    figureEnter = true;   
                    scan.nextLine(); 
                }
                catch (Exception ex){
                    System.out.println("Invalid input");
                    figureEnter = false;
                    scan.nextLine();
                }  
                if(paid < 0 || paid >= 9999.99){
                    if(paid < 0)
                    {
                        System.out.println("The figure must be positive");
                    }else{
                        System.out.println("The figure must be in the range 0 - 9999.99 only");
                    }                  
                }   
            }while(!figureEnter || paid < 0 || paid >= 9999.99);

            if(paid > grandTotal){
                //ask to confirm or not
                do{
                    System.out.printf("Confirm the figure entered (Y/N): ");
                    confirmEnter = scan.next().charAt(0);   
                    scan.nextLine();
                    confirmEnter = Character.toUpperCase(confirmEnter);                
                    if(!(confirmEnter == 'Y' || confirmEnter == 'N')){
                        System.out.println("Just Enter Y to continue the bill and N to not.");
                    }
                }while(!(confirmEnter == 'Y' || confirmEnter == 'N'));
                if(confirmEnter == 'Y'){
                    quitBalance = true;
                    balance = paid - grandTotal;
                    System.out.printf("Balance : %-5.2f",balance);
                    break;
                            
                }else{
                    quitBalance = false;
                }
                
            }else{
                System.out.println("The figure entered are lower than the grand total.");
                quitBalance = false;
            }
        }while(!quitBalance);
       
        char confirm;
        boolean quitBill;
        //ask to confirm or not
            do{
                System.out.printf("\nConfirm the bill (Y/N): ");
                confirm = scan.next().charAt(0);   
                scan.nextLine();
                confirm = Character.toUpperCase(confirm);                
                if(!(confirm == 'Y' || confirm == 'N')){
                    System.out.println("Just Enter Y to continue the bill and N to not.");
                }
            }while(!(confirm == 'Y' || confirm == 'N'));
            quitBill = confirm != 'Y';
            
        if(!quitBill){
            summarying[count].setBalance(balance); //paid - grandTotal = balance 
            count++;            
            table[scanTableNumber-1].setOccupy("Empty");
            table[scanTableNumber-1].setPersonCount(0);
            table[scanTableNumber-1].setAdultCount(0);
            table[scanTableNumber-1].setChildCount(0);
            table[scanTableNumber-1].setElderCount(0);         
            table[scanTableNumber-1].setComboSet('-');
        }else{
            summarying[count].setCountAdult(0);
            summarying[count].setCountKid(0);
            summarying[count].setCountElder(0);      
            summarying[count].setSST(0);
            summarying[count].setTotalAdult(0);
            summarying[count].setTotalKid(0);
            summarying[count].setTotalElder(0);
            summarying[count].setSet('-');
            summarying[count].setIsMember(false);
            summarying[count].setMemberID("-");
            summarying[count].setDayVisited(0);
            summarying[count].setSubTotal(0);
            summarying[count].setGrandTotal(0);
        }
    }
    
        public static void comboB(tables[] table,ArrayList<Product> ProductList,ArrayList<Member> MemberList,int scanTableNumber,countSummary [] summarying, boolean member, int dayVisit, String proveID){
        int adult, kid, elder;
        double countAdult, countKid,  countElder;
        double SST = 0.05, subTotal = 0,grandTotal = 0;
        double paid = 0, balance = 0;
        
        Scanner scan = new Scanner(System.in);
        boolean figureEnter, quitBalance;
        char confirmEnter;
        adult = table[scanTableNumber-1].getAdultCount();
        kid = table[scanTableNumber-1].getChildCount();
        elder = table[scanTableNumber-1].getElderCount();
        //Count 
        countAdult = adult * ProductList.get(0).getpPriceAdults(); // get(1)
        countKid = kid * ProductList.get(0).getpPriceKids();
        countElder = elder * ProductList.get(0).getpPriceElders();
        subTotal = countAdult + countKid + countElder; //Total up 
        grandTotal = (subTotal * SST) + subTotal; //Total + SST
        //membership
        if(member){
            if(dayVisit >= 3){               
                grandTotal = grandTotal - (grandTotal * 0.1); //member discount
            }else{
                grandTotal = grandTotal - (grandTotal * 0.05); //member discount
            }
        }      
        summarying[count].setCountAdult(adult);
        summarying[count].setCountKid(kid);
        summarying[count].setCountElder(elder);      
        summarying[count].setSST(SST);
        summarying[count].setTotalAdult(countAdult);
        summarying[count].setTotalKid(countKid);
        summarying[count].setTotalElder(countElder);
        summarying[count].setSet('B');
        summarying[count].setIsMember(member);
        if(member){
            summarying[count].setMemberID(proveID);
            summarying[count].setDayVisited(dayVisit);
        }else{
            summarying[count].setMemberID("-");
            summarying[count].setDayVisited(0);
        }
        summarying[count].setSubTotal(subTotal);
        summarying[count].setGrandTotal(grandTotal);
        //Display method!=class
        displaywhatever(table,ProductList,summarying,scanTableNumber);                                        
        //Balance 
        do{
            do{
                System.out.printf("Enter the figure to pay :");
                try
                {            
                    paid = scan.nextDouble();     
                    figureEnter = true;   
                    scan.nextLine(); 
                }
                catch (Exception ex){
                    System.out.println("Invalid input");
                    figureEnter = false;
                    scan.nextLine();
                }  
                if(paid < 0 || paid >= 9999.99){
                    if(paid < 0)
                    {
                        System.out.println("The figure must be positive");
                    }else{
                        System.out.println("The figure must be in the range 0 - 9999.99 only");
                    }                  
                }   
            }while(!figureEnter || paid < 0 || paid >= 9999.99);

            if(paid > grandTotal){
                //ask to confirm or not
                do{
                    System.out.printf("Confirm the figure entered (Y/N): ");
                    confirmEnter = scan.next().charAt(0);   
                    scan.nextLine();
                    confirmEnter = Character.toUpperCase(confirmEnter);                
                    if(!(confirmEnter == 'Y' || confirmEnter == 'N')){
                        System.out.println("Just Enter Y to continue the bill and N to not.");
                    }
                }while(!(confirmEnter == 'Y' || confirmEnter == 'N'));
                if(confirmEnter == 'Y'){
                    quitBalance = true;
                    balance = paid - grandTotal;
                    System.out.printf("Balance : %-5.2f",balance);
                    break;
                }else{
                    quitBalance = false;
                    
                }
            }else{
                System.out.println("The figure entered are lower than the balance.");
                quitBalance = false;
            }
        }while(!quitBalance);
       
        char confirm;
        boolean quitBill;
        //ask to confirm or not
            do{
                System.out.printf("\nConfirm the bill (Y/N): ");
                confirm = scan.next().charAt(0);   
                scan.nextLine();
                confirm = Character.toUpperCase(confirm);                
                if(!(confirm == 'Y' || confirm == 'N')){
                    System.out.println("Just Enter Y to continue the bill and N to not.");
                }
            }while(!(confirm == 'Y' || confirm == 'N'));
            quitBill = confirm != 'Y';
            
        if(!quitBill){
            summarying[count].setBalance(balance);
            count++;            
            table[scanTableNumber-1].setOccupy("Empty");
            table[scanTableNumber-1].setPersonCount(0);
            table[scanTableNumber-1].setAdultCount(0);
            table[scanTableNumber-1].setChildCount(0);
            table[scanTableNumber-1].setElderCount(0);         
            table[scanTableNumber-1].setComboSet('-');
        }else{
            summarying[count].setCountAdult(0);
            summarying[count].setCountKid(0);
            summarying[count].setCountElder(0);      
            summarying[count].setSST(0);
            summarying[count].setTotalAdult(0);
            summarying[count].setTotalKid(0);
            summarying[count].setTotalElder(0);
            summarying[count].setSet('-');
            summarying[count].setIsMember(false);
            summarying[count].setMemberID("-");
            summarying[count].setDayVisited(0);
            summarying[count].setSubTotal(0);
            summarying[count].setGrandTotal(0);
        }
    }
        
        public static void comboC(tables[] table,ArrayList<Product> ProductList,ArrayList<Member> MemberList,int scanTableNumber,countSummary [] summarying, boolean member, int dayVisit, String proveID){
        int adult, kid, elder;
        double countAdult, countKid,  countElder;
        double SST = 0.05, subTotal = 0,grandTotal = 0;
        double paid = 0, balance = 0;
        
        Scanner scan = new Scanner(System.in);
        boolean figureEnter, quitBalance;
        char confirmEnter;
        adult = table[scanTableNumber-1].getAdultCount();
        kid = table[scanTableNumber-1].getChildCount();
        elder = table[scanTableNumber-1].getElderCount();
        //Count 
        countAdult = adult * ProductList.get(0).getpPriceAdults(); // get(1)
        countKid = kid * ProductList.get(0).getpPriceKids();
        countElder = elder * ProductList.get(0).getpPriceElders();
        subTotal = countAdult + countKid + countElder; //Total up 
        grandTotal = (subTotal * SST) + subTotal; //Total + SST
        //membership
        if(member){
            if(dayVisit >= 3){               
                grandTotal = grandTotal - (grandTotal * 0.1); //member discount
            }else{
                grandTotal = grandTotal - (grandTotal * 0.05); //member discount
            }
        }      
        summarying[count].setCountAdult(adult);
        summarying[count].setCountKid(kid);
        summarying[count].setCountElder(elder);      
        summarying[count].setSST(SST);
        summarying[count].setTotalAdult(countAdult);
        summarying[count].setTotalKid(countKid);
        summarying[count].setTotalElder(countElder);
        summarying[count].setSet('C');
        summarying[count].setIsMember(member);
        if(member){
            summarying[count].setMemberID(proveID);
            summarying[count].setDayVisited(dayVisit);
        }else{
            summarying[count].setMemberID("-");
            summarying[count].setDayVisited(0);
        }
        summarying[count].setSubTotal(subTotal);
        summarying[count].setGrandTotal(grandTotal);
        //Display method!=class
        displaywhatever(table,ProductList,summarying,scanTableNumber);                                        
        //Balance 
        do{
            do{
                System.out.printf("Enter the figure to pay :");
                try
                {            
                    paid = scan.nextDouble();     
                    figureEnter = true;   
                    scan.nextLine(); 
                }
                catch (Exception ex){
                    System.out.println("Invalid input");
                    figureEnter = false;
                    scan.nextLine();
                }  
                if(paid < 0 || paid >= 9999.99){
                    if(paid < 0)
                    {
                        System.out.println("The figure must be positive");
                    }else{
                        System.out.println("The figure must be in the range 0 - 9999.99 only");
                    }                  
                }   
            }while(!figureEnter || paid < 0 || paid >= 9999.99);

            if(paid > grandTotal){
                //ask to confirm or not
                do{
                    System.out.printf("Confirm the figure entered (Y/N): ");
                    confirmEnter = scan.next().charAt(0);   
                    scan.nextLine();
                    confirmEnter = Character.toUpperCase(confirmEnter);                
                    if(!(confirmEnter == 'Y' || confirmEnter == 'N')){
                        System.out.println("Just Enter Y to continue the bill and N to not.");
                    }
                }while(!(confirmEnter == 'Y' || confirmEnter == 'N'));
                if(confirmEnter == 'Y'){
                    quitBalance = true;
                    balance = paid - grandTotal;
                    System.out.printf("Balance : %-5.2f",balance);
                    break;
                }else{
                    quitBalance = false;
                }
            }else{
                System.out.println("The figure entered are lower than the balance.");
                quitBalance = false;
            }
        }while(!quitBalance);
       
        char confirm;
        boolean quitBill;
        //ask to confirm or not
            do{
                System.out.printf("\nConfirm the bill (Y/N): ");
                confirm = scan.next().charAt(0);   
                scan.nextLine();
                confirm = Character.toUpperCase(confirm);                
                if(!(confirm == 'Y' || confirm == 'N')){
                    System.out.println("Just Enter Y to continue the bill and N to not.");
                }
            }while(!(confirm == 'Y' || confirm == 'N'));
            quitBill = confirm != 'Y';
            
        if(!quitBill){
            summarying[count].setBalance(balance);
            count++;            
            table[scanTableNumber-1].setOccupy("Empty");
            table[scanTableNumber-1].setPersonCount(0);
            table[scanTableNumber-1].setAdultCount(0);
            table[scanTableNumber-1].setChildCount(0);
            table[scanTableNumber-1].setElderCount(0);         
            table[scanTableNumber-1].setComboSet('-');
        }else{
            summarying[count].setCountAdult(0);
            summarying[count].setCountKid(0);
            summarying[count].setCountElder(0);      
            summarying[count].setSST(0);
            summarying[count].setTotalAdult(0);
            summarying[count].setTotalKid(0);
            summarying[count].setTotalElder(0);
            summarying[count].setSet('-');
            summarying[count].setIsMember(false);
            summarying[count].setMemberID("-");
            summarying[count].setDayVisited(0);
            summarying[count].setSubTotal(0);
            summarying[count].setGrandTotal(0);
        }
    }
        
        public static void comboD(tables[] table,ArrayList<Product> ProductList,ArrayList<Member> MemberList,int scanTableNumber,countSummary [] summarying, boolean member, int dayVisit, String proveID){
        int adult, kid, elder;
        double countAdult, countKid,  countElder;
        double SST = 0.05, subTotal = 0,grandTotal = 0;
        double paid = 0, balance = 0;
        
        Scanner scan = new Scanner(System.in);
        boolean figureEnter, quitBalance;
        char confirmEnter;
        adult = table[scanTableNumber-1].getAdultCount();
        kid = table[scanTableNumber-1].getChildCount();
        elder = table[scanTableNumber-1].getElderCount();
        //Count 
        countAdult = adult * ProductList.get(0).getpPriceAdults(); // get(1)
        countKid = kid * ProductList.get(0).getpPriceKids();
        countElder = elder * ProductList.get(0).getpPriceElders();
        subTotal = countAdult + countKid + countElder; //Total up 
        grandTotal = (subTotal * SST) + subTotal; //Total + SST
        //membership
        if(member){
            if(dayVisit >= 3){               
                grandTotal = grandTotal - (grandTotal * 0.1); //member discount
            }else{
                grandTotal = grandTotal - (grandTotal * 0.05); //member discount
            }
        }      
        summarying[count].setCountAdult(adult);
        summarying[count].setCountKid(kid);
        summarying[count].setCountElder(elder);      
        summarying[count].setSST(SST);
        summarying[count].setTotalAdult(countAdult);
        summarying[count].setTotalKid(countKid);
        summarying[count].setTotalElder(countElder);
        summarying[count].setSet('D');
        summarying[count].setIsMember(member);
        if(member){
            summarying[count].setMemberID(proveID);
            summarying[count].setDayVisited(dayVisit);
        }else{
            summarying[count].setMemberID("-");
            summarying[count].setDayVisited(0);
        }
        summarying[count].setSubTotal(subTotal);
        summarying[count].setGrandTotal(grandTotal);
        //Display method!=class
        displaywhatever(table,ProductList,summarying,scanTableNumber);                                        
        //Balance 
        do{
            do{
                System.out.printf("Enter the figure to pay :");
                try
                {            
                    paid = scan.nextDouble();     
                    figureEnter = true;   
                    scan.nextLine(); 
                }
                catch (Exception ex){
                    System.out.println("Invalid input");
                    figureEnter = false;
                    scan.nextLine();
                }  
                if(paid < 0 || paid >= 9999.99){
                    if(paid < 0)
                    {
                        System.out.println("The figure must be positive");
                    }else{
                        System.out.println("The figure must be in the range 0 - 9999.99 only");
                    }                  
                }   
            }while(!figureEnter || paid < 0 || paid >= 9999.99);

            if(paid > grandTotal){
                //ask to confirm or not
                do{
                    System.out.println("Confirm the figure entered (Y/N): ");
                    confirmEnter = scan.next().charAt(0);   
                    scan.nextLine();
                    confirmEnter = Character.toUpperCase(confirmEnter);                
                    if(!(confirmEnter == 'Y' || confirmEnter == 'N')){
                        System.out.println("Just Enter Y to continue the bill and N to not.");
                    }
                }while(!(confirmEnter == 'Y' || confirmEnter == 'N'));
                if(confirmEnter == 'Y'){
                    quitBalance = true;
                    balance = paid - grandTotal;
                    System.out.printf("Balance : %-5.2f",balance);
                    break;
                }else{
                    quitBalance = false;
                }
            }else{
                System.out.println("The figure entered are lower than the balance.");
                quitBalance = false;
            }
        }while(!quitBalance);
       
        char confirm;
        boolean quitBill;
        //ask to confirm or not
            do{
                System.out.printf("\nConfirm the bill (Y/N): ");
                confirm = scan.next().charAt(0);   
                scan.nextLine();
                confirm = Character.toUpperCase(confirm);                
                if(!(confirm == 'Y' || confirm == 'N')){
                    System.out.println("Just Enter Y to continue the bill and N to not.");
                }
            }while(!(confirm == 'Y' || confirm == 'N'));
            quitBill = confirm != 'Y';
            
        if(!quitBill){
            summarying[count].setBalance(balance);
            count++;            
            table[scanTableNumber-1].setOccupy("Empty");
            table[scanTableNumber-1].setPersonCount(0);
            table[scanTableNumber-1].setAdultCount(0);
            table[scanTableNumber-1].setChildCount(0);
            table[scanTableNumber-1].setElderCount(0);         
            table[scanTableNumber-1].setComboSet('-');
        }else{
            summarying[count].setCountAdult(0);
            summarying[count].setCountKid(0);
            summarying[count].setCountElder(0);      
            summarying[count].setSST(0);
            summarying[count].setTotalAdult(0);
            summarying[count].setTotalKid(0);
            summarying[count].setTotalElder(0);
            summarying[count].setSet('-');
            summarying[count].setIsMember(false);
            summarying[count].setMemberID("-");
            summarying[count].setDayVisited(0);
            summarying[count].setSubTotal(0);
            summarying[count].setGrandTotal(0);
        }
    }
}
