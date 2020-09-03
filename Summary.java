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
            System.out.println("1. Total Summary");
            System.out.println("2. Show All Bill History");
            System.out.println("3. Exit");
            
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
                case 3: System.out.println("Back to menu...");break; //Back to menu
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
//        int totalAdultA = 0,totalKidA = 0,totalElderA = 0;
//        int totalAdultB = 0,totalKidB = 0,totalElderB = 0;
//        int totalAdultC = 0,totalKidC = 0,totalElderC = 0;
//        int totalAdultD = 0,totalKidD = 0,totalElderD = 0;
        
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
                totalGrandTotal = 0; 

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
        System.out.println("\t\t\tTotal of Sales Of the day: " + totalGrandTotal);
        
    }
//        double totalA, totalB,totalC,totalD;
//        System.out.printf("Set Name:A\n"); // Set A
//        System.out.printf("Category\tTotal Number of Pax\tPrice Per Person\tTotal\n");
//        System.out.printf("======="Category\tTotal Number of Pax\tPrice Per Person\tTotal\n"=\t===================\t================\t======\n\n");
//        System.out.printf("Adult\t\t\t%d\t\t  RM 18.99\t\t %.2f\t\n",totalAdultA,totalAdultA*18.99);
//        System.out.printf("Kid\t\t\t%d\t\t  RM 12.99\t\t %.2f\t\n",totalKidA,totalKidA*12.99);
//        System.out.printf("Elder\t\t\t%d\t\t  RM 14.99\t\t %.2f\t\n",totalElderA,totalElderA*14.99);
//        totalA = (totalAdultA * 18.99) + (totalKidA * 12.99) + (totalElderA * 14.99);
//        System.out.printf("\t\t\t\t===========\n");
//        System.out.printf("\t\t\t\tTotal :%.2f\n\n",totalA);
//        
//        
//        System.out.printf("Set Name:B\n"); // Set B
//        System.out.printf("Category\tTotal Number of Pax\tPrice Per Person\tTotal\n");
//        System.out.printf("========\t===================\t================\t======\n\n");
//        System.out.printf("Adult\t\t\t%d\t\t  RM 24.99\t\t %.2f\t\n",totalAdultB,totalAdultB*24.99);
//        System.out.printf("Kid\t\t\t%d\t\t  RM 18.99\t\t %.2f\t\n",totalKidB,totalKidB*18.99);
//        System.out.printf("Elder\t\t\t%d\t\t  RM 20.99\t\t %.2f\t\n",totalElderB,totalElderB*20.99);
//        totalB = (totalAdultB * 24.99) + (totalKidB * 18.99) + (totalElderB * 20.99);
//        System.out.printf("\t\t\t\t===========\n");
//        System.out.printf("\t\t\t\tTotal :%.2f\n\n",totalB);
//        
//        System.out.printf("Set Name:C\n"); // Set C
//        System.out.printf("Category\tTotal Number of Pax\tPrice Per Person\tTotal\n");
//        System.out.printf("========\t===================\t================\t======\n\n");
//        System.out.printf("Adult\t\t\t%d\t\t  RM 35.99\t\t %.2f\t\n",totalAdultC,totalAdultC*35.99);
//        System.out.printf("Kid\t\t\t%d\t\t  RM 20.99\t\t %.2f\t\n",totalKidC,totalKidC*20.99);
//        System.out.printf("Elder\t\t\t%d\t\t  RM 30.99\t\t %.2f\t\n",totalElderC,totalElderC*30.99);
//        totalC = (totalAdultC * 35.99) + (totalKidC * 20.99) + (totalElderC * 30.99);
//        System.out.printf("\t\t\t\t===========\n");
//        System.out.printf("\t\t\t\tTotal :%.2f\n\n",totalC);
//        
//        System.out.printf("Set Name:C\n"); // Set D
//        System.out.printf("Category\tTotal Number of Pax\tPrice Per Person\tTotal\n");
//        System.out.printf("========\t===================\t================\t======\n\n");
//        System.out.printf("Adult\t\t\t%d\t\t  RM 40.99\t\t %.2f\t\n",totalAdultD,totalAdultD*40.99);
//        System.out.printf("Kid\t\t\t%d\t\t  RM 35.99\t\t %.2f\t\n",totalKidD,totalKidD*35.99);
//        System.out.printf("Elder\t\t\t%d\t\t  RM 30.99\t\t %.2f\t\n",totalElderD,totalElderD*30.99);
//        totalD = (totalAdultD * 40.99) + (totalKidD * 35.99) + (totalElderD * 30.99);
//        System.out.printf("\t\t\t\t===========\n");
//        System.out.printf("\t\t\t\tTotal :%.2f\n\n",totalD);
//        System.out.printf("//////////////////////////////////////////////////////////////////////\n");
//        System.out.printf("\t\t\t\tSet A: %.2f\n",totalA); //Total price of Set A
//        System.out.printf("\t\t\t\tSet B: %.2f\n",totalB); //Total price of Set B
//        System.out.printf("\t\t\t\tSet C: %.2f\n",totalC); //Total price of Set C
//        System.out.printf("\t\t\t\tSet D: %.2f\n",totalD); //Total price of Set D
//       
//        double totalSales;
//        Scanner scan = new Scanner(System.in);
//        char start;
//        do{
//            System.out.printf("Enter 'Y' to start calculate total sales or 'N' to cancel:"); //Scan
//            start = scan.next().charAt(0);
//            scan.nextLine();
//            start = Character.toUpperCase(start);
//            if(!(start != 'Y'  || start != 'N')){
//                System.out.println("Wrong input.");
//                System.out.println("Please enter 'Y' or 'N'");
//            }
//        }while(!(start != 'Y'  || start != 'N'));
//        if(start == 'Y')
//        {
//            totalSales = totalA + totalB + totalC +totalD;
//            System.out.printf("\t\t\t\t===========\n");
//            System.out.printf("Date :");
//            System.out.println(date);
//            System.out.printf("\t\t\tTotal of Sales Report: %.2f\n\n",totalSales);
//        }
//    }
//    
    public static void billHistory(countSummary[] summarying){
        Scanner scan = new Scanner(System.in);
        char chooseSet;
        boolean checkSet;
        char set;
        System.out.println("Set A");
        System.out.println("Set B");
        System.out.println("Set C");
        System.out.println("Set D");
        do{    
            System.out.printf("\nPlease select a Set (A/B/C/D) >");
            chooseSet = scan.next().charAt(0); 
            scan.nextLine();
            chooseSet = Character.toUpperCase(chooseSet);
           
        }while(!(chooseSet == 'A' || chooseSet == 'B' || chooseSet == 'C' || chooseSet == 'D'));
                
        for(int i= 0;i<150;i++){
            set = summarying[i].getSet();
            
            if(chooseSet == set){
                System.out.printf("\nSet Name:%c\n",set); // Set A
                System.out.printf("Category\tTotal Number of Pax\tPrice Per Person\tTotal\n");
                System.out.printf("========\t===================\t================\t======\n\n");
                
                if(summarying[i].getCountAdult() !=0){
                    double PricePerAdult = summarying[i].getTotalAdult() / summarying[i].getCountAdult();
                    System.out.printf("Adult\t%15d\t\t\t%-12.2f\t%7.2f\n",summarying[i].getCountAdult(),PricePerAdult,summarying[i].getTotalAdult());
                }
                if(summarying[i].getCountKid() !=0){
                    double PricePerKid = summarying[i].getTotalKid()  / summarying[i].getCountKid();
                    System.out.printf("Kid\t%15d\t\t\t%-12.2f\t%7.2f\n",summarying[i].getCountKid(),PricePerKid,summarying[i].getTotalKid());
                }
                if(summarying[i].getCountElder() !=0){
                    double PricePerElder = summarying[i].getTotalElder() / summarying[i].getCountElder();
                    System.out.printf("Elder\t%15d\t\t\t%-12.2f\t%7.2f\n",summarying[i].getCountElder(),PricePerElder,summarying[i].getTotalElder());
                }
            }
        }
        
    }

      
}

