package ooptassignment;

import java.util.Scanner;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Summary {
    
    public static void Summary2(tables [] table, countSummary[] summarying)
    {
        //fill all the summary to empty !!for running the program at the first time
        if(summarying[0] == null){
        for(int i = 0; i < 150; i++){
            summarying[i] = new countSummary(0, 0, 0, 0, 0, 0, 0 ,'-', "-", 0, 0, 0, 0, false);
            }
        } 
        Scanner scan = new Scanner(System.in);
        int scanOption =0;
        do{
            System.out.println("Summary Function");
            System.out.println("1. Total Summary");
            System.out.println("2. Show All Bill History");
            System.out.println("3. Back");
            
            boolean choice ;

            do{
                System.out.printf("Which part you wanted to proceed? (1-3) :");
                try
                {
                    scanOption = scan.nextInt();
                    choice = true;
                    scan.nextLine();
                }
                catch (Exception ex)
                {
                    System.out.println("!!!Invalid integer input!!!");
                    choice = false;
                    scan.nextLine();
                }
                if (scanOption <=0|| scanOption > 3)
                {
                System.out.println("The option must be in (1-4)");
                }
            }while(!choice || scanOption <=0 || scanOption >3);

            switch (scanOption){
                case 1: totalSummary(summarying);break;
                case 2: billHistory(summarying);break;
                case 3: System.out.println("Back to menu...");return; //Back to menu
            }
        }while(scanOption != 3);
        
    }
    
    public static void totalSummary(countSummary[] summarying){
        ArrayList<Product> checkComboSet = Product.getProductList();
        int totalAdult = 0, totalKid = 0, totalElder = 0, totalCust = 0;
        double totalPrice = 0, totalGrandTotal = 0; 
        System.out.println("\t\t\t --------------");
        System.out.println("\t\t\t|Report Summary|");
        System.out.println("\t\t\t --------------");
        System.out.println("*********************************************************************");
        //Date
        LocalDate todayDate = LocalDate.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = todayDate.format(formatDate);
        System.out.printf("Date :");
        System.out.println(date);
        
        int setSize = checkComboSet.size();
        int prodID = 65;
        if(summarying != null){
            for(int i=0; i<setSize; i++){            
                char convert = (char)(prodID);
                totalAdult = 0;
                totalKid = 0;
                totalElder = 0;
                totalCust = 0;
                totalPrice = 0; 

                    for (countSummary summarying1 : summarying) {
                        if (prodID == summarying1.getSet()) {
                            totalAdult += summarying1.getCountAdult();
                            totalKid += summarying1.getCountKid();
                            totalElder += summarying1.getCountAdult();
                            totalCust = totalAdult + totalKid + totalElder;
                            totalPrice += summarying1.getGrandTotal();
                            totalGrandTotal += totalPrice;
                            break;
                        }
                    }
                String summaryMsg = countSummary.toString(convert, totalAdult, totalKid, totalElder, totalCust, totalPrice, i, checkComboSet);
                System.out.print(summaryMsg);
                prodID += 1;


            }
        }
        System.out.println("\t\t\tTotal Sales Of the day: " + totalGrandTotal);
        
    }

    public static void billHistory(countSummary[] summarying){
        ArrayList<Product> checkComboSet = Product.getProductList();
        Scanner scan = new Scanner(System.in);
        char chooseSet;
        boolean checkSet;
        System.out.print("Set :");
        for(int i = 0; i < checkComboSet.size(); i++){
            System.out.print(checkComboSet.get(i).getProductID() + "/");
        }
        
        do{   
            checkSet = true;
            System.out.printf("\nPlease select a Set from above >");
            chooseSet = scan.next().charAt(0); 
            scan.nextLine();
            chooseSet = Character.toUpperCase(chooseSet);
            for(int i = 0; i < checkComboSet.size(); i++){
                if(chooseSet == checkComboSet.get(i).getProductID()){
                    checkSet = true;
                    break;
                }else
                    checkSet = false;
            }
        }while(checkSet == false);
        
        int checkNull = 0;
        for(int i= 0;i<summarying.length; i++){
            
            if(chooseSet == summarying[i].getSet()){
                for(int j = 0; j < checkComboSet.size(); j++){
                    if(chooseSet == checkComboSet.get(j).getProductID()){
                        System.out.printf("\nSet Name:%c\n", checkComboSet.get(j).getProductID());
                        System.out.printf("Category\tTotal Number of Pax\tPrice Per Person\tTotal\n");
                        System.out.printf("========\t===================\t================\t======\n");

                        if(summarying[i].getCountAdult() !=0){
                            System.out.printf("Adult\t%15d\t\t\t%-12.2f\t%7.2f\n",summarying[i].getCountAdult(),checkComboSet.get(j).getpPriceAdults(),summarying[i].getTotalAdult());
                        }
                        if(summarying[i].getCountKid() !=0){

                            System.out.printf("Kid\t%15d\t\t\t%-12.2f\t%7.2f\n",summarying[i].getCountKid(),checkComboSet.get(j).getpPriceKids(),summarying[i].getTotalKid());
                        }
                        if(summarying[i].getCountElder() !=0){
                            System.out.printf("Elder\t%15d\t\t\t%-12.2f\t%7.2f\n",summarying[i].getCountElder(),checkComboSet.get(j).getpPriceElders(),summarying[i].getTotalElder());
                        }
                        checkNull++;
                    }
                }
            }
        }
        
        if(checkNull == 0){
            System.out.println("\nThere is no any bill history yet\n");
        }
        
    }

      
}

