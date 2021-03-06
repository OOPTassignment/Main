/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooptassignment;

import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Lim Chee Ziong
 */
public class ProductDetails {
    public static void ProductM() {
        Scanner scn = new Scanner(System.in);
        int selection=0;
        boolean catchInt;//for the loop condition
        ArrayList<Product> checkProd = Product.getProductList();
        do{
            System.out.printf("%18s%15s\n","","Product Details");
            System.out.println("===================================================");
            System.out.println("| 1 | Add Product ");
            System.out.println("===================================================");
            System.out.println("| 2 | Modify Product ");
            System.out.println("===================================================");
            System.out.println("| 3 | Display Product ");
            System.out.println("===================================================");
            System.out.println("| 4 | Delete Product ");
            System.out.println("===================================================");
            System.out.println("| 5 | Exit ");
            System.out.println("===================================================");
            System.out.print("Please enter your selection :");
            
            //rewind
             try{
                selection = scn.nextInt();
                scn.nextLine();
                catchInt=true;
                switch (selection) {
                case 1:
                    char addMore;
                    do{
                     addProd(checkProd);//call addProd, pass in arraylist checkProd
                     System.out.print("Do you want to add more?(Y/N)");
                     addMore=scn.next().charAt(0);
                     Character.toUpperCase(addMore);//prompt to add more
                     if(!(addMore=='Y'||addMore=='N')){
                         System.out.println("Invalid input entered");
                     }
                    }while(addMore=='Y');
                    break;

                case 2:
                    System.out.print("Please enter the product ID :");
                    char inputID = scn.next().charAt(0);//scan for product ID
                    for (int i = 0; i <checkProd.size();i++) {
                        if (inputID==(checkProd.get(i).getProductID()) ) {
                            modProd(checkProd, i,inputID);//pass in arraylist checkProd , index i , input ID into modProd
                            break;
                        }
                        else {
                            System.out.println("ID is not registered.");
                            break;
                        }
                    }
                    break;
                case 3:
                    dispProd(checkProd);//display product in the arraylist checkProd
                    break;
                case 4:
                    char check;
                    do{
                        delProd(checkProd);
                        System.out.print("Do you want to continue deleting product(Y/N)?");
                        check = scn.next().charAt(0);
                        Character.toUpperCase(check);//condition to loop 
                        if(!(check!='Y'||check!='N')){
                            System.out.println("Invalid input entered");
                        }
                    }while(check=='Y');
                    break;
                case 5:
                    return;//return to manager menu
                default:
                    System.out.println("\n");
                    break;
            }
            }catch(Exception ad){
                catchInt=false;
                scn.nextLine();
                System.out.println("Invalid selection entered !");
            }
        }while(selection!=5 || catchInt==false);
    }
    

    public static void addProd(ArrayList<Product> addP){
        Scanner scn = new Scanner(System.in);
        int checkProductID = 65;
        int array = addP.size();
        checkProductID += array;
        char convert =(char)(checkProductID);/*product id */
        

        System.out.print("Enter the Product Name :");
        String productName= scn.nextLine();//enter product name
        
        
        boolean catchDouble;
        double kidPrice=0;
        do{
            System.out.print("Enter the product price for Kids :");
            try{
                kidPrice = scn.nextDouble();//input product price for kids
                scn.nextLine();
                catchDouble=true;
            }catch(Exception ad){
                catchDouble=false;
                scn.nextLine();
                System.out.println("Invalid price entered !");
            }
            if(kidPrice < 0)
                System.out.println("Price must not be negative number !");
        }while(!(catchDouble) || kidPrice < 0);
        
        double adultPrice=0;
        do{
            System.out.print("Enter the product price for Adults :");
            try{
                adultPrice = scn.nextDouble();//input product price for adults
                scn.nextLine();
                catchDouble=true;
            }catch(Exception ae){
                catchDouble=false;
                scn.nextLine();
                System.out.println("Invalid price entered !");
            }
            if(adultPrice < 0)
                System.out.println("Price must not be negative number !");
        }while(!(catchDouble) || adultPrice < 0);

        double elderPrice=0;
        do{
            System.out.print("Enter the product price for Elders :");
            try{
                elderPrice = scn.nextDouble();//input product price for elders
                scn.nextLine();
                catchDouble=true;
            }catch(Exception ae){
                catchDouble=false;
                scn.nextLine();
                System.out.println("Invalid price entered !");
            }
            if(elderPrice < 0)
                System.out.println("Price must not be negative number !");
        }while(!(catchDouble) || elderPrice < 0);
        
        
        addP.add(new Product(convert, productName, kidPrice, adultPrice, elderPrice));
        
    }
    public static void modProd(ArrayList<Product> modP,int i , char input){
        Scanner scn = new Scanner(System.in);
        String modPName="";
        double modKidPrice = -1.00;
        double modAdultPrice = -1.00;
        double modElderPrice = -1.00;
        int selection=0;
        boolean catchInt;
        do{
            System.out.println("===================================================");
            System.out.println("| 1 | Modify Product Name");
            System.out.println("===================================================");
            System.out.println("| 2 | Modify Product Price for Kids");
            System.out.println("===================================================");
            System.out.println("| 3 | Modify Product Price for Adults ");
            System.out.println("===================================================");
            System.out.println("| 4 | Modify Product Price for Elders ");
            System.out.println("===================================================");
            System.out.println("| 5 | Save the changes ");
            System.out.println("===================================================");
            System.out.println("| 6 | Exit ");
            System.out.println("===================================================");
            System.out.print("Please enter your selection :");
            boolean catchDouble;
            
            try{
                selection = scn.nextInt();
                scn.nextLine();
                catchInt=true;
                switch(selection){
                    case 1:
                        System.out.print("Please enter the Name :");
                        modPName = scn.nextLine();//modifying name of product
                        break;

                    case 2:
                        do{
                            System.out.print("Enter the modified product price for Kids :");
                            try{
                                modKidPrice = scn.nextDouble();//modifying price for kids
                                scn.nextLine();
                                catchDouble=true;
                            }catch(Exception ad){
                                catchDouble=false;
                                scn.nextLine();
                                System.out.println("Invalid price entered !");
                            }
                            if(modKidPrice < 0)
                                System.out.println("Price must not be negative number !");
                        }while(!(catchDouble) || modKidPrice < 0);
                        break;

                    case 3:
                        do{
                            System.out.print("Enter the modified product price for Adults :");
                            try{
                                modAdultPrice = scn.nextDouble();//modifying price for adults
                                scn.nextLine();
                                catchDouble=true;
                            }catch(Exception ae){
                                catchDouble=false;
                                scn.nextLine();
                                System.out.println("Invalid price entered !");
                            }
                            if(modAdultPrice < 0)
                                System.out.println("Price must not be negative number !");
                        }while(!(catchDouble) || modAdultPrice < 0);
                        break;
                    case 4:
                        do{
                            System.out.print("Enter the product price for Elders :");
                            try{
                                modElderPrice = scn.nextDouble();//modifying price for elders
                                scn.nextLine();
                                catchDouble=true;
                            }catch(Exception ae){
                                catchDouble=false;
                                scn.nextLine();
                                System.out.println("Invalid price entered !");
                            }
                            if(modElderPrice < 0)
                                System.out.println("Price must not be negative number !");
                        }while(!(catchDouble) || modElderPrice < 0);
                        break;
                    case 5:
                        if(!modPName.equals("")){
                                modP.get(i).setProdName(modPName); //saving the changes from new modified input
                        }
                        if(!(modKidPrice == -1.00)){
                                modP.get(i).setpPriceKids(modKidPrice);
                        }
                        if(!(modElderPrice == -1.00)){
                                modP.get(i).setpPriceElders(modElderPrice);
                        }
                        if(!(modAdultPrice == -1.00)){
                                modP.get(i).setpPriceAdults(modAdultPrice);
                        }
                        break;

                    case 6:
                        return;
                    default :
                        System.out.println("invalid input entered, please choose a number between 1 to 6 only!");
                }
            }catch(Exception ae){
                catchInt=false;
                scn.nextLine();
                System.out.println("Invalid selection entered !");
            }
            
        }while(selection!=6 || catchInt==false);
    }
    public static void dispProd(ArrayList<Product> checkProd){
        
        checkProd = Product.getProductList();//
        System.out.println("");  
        System.out.printf("\n%20sProduct Information\n", "");
        System.out.printf("|%-12s|%-20s|%-20s|%-20s|%-20s|","Product ID","Product Name","Price for Kids","Price for Adults","Price for Elders");
        System.out.printf("\n==================================================================================================");
        for(int x = 0; x < checkProd.size(); x++){
            System.out.printf("\n%s%-12c%s%-20s%s%20.2f%s%20.2f%s%20.2f"," ",checkProd.get(x).getProductID()," ",checkProd.get(x).getProdName()," ", checkProd.get(x).getpPriceKids()," "
                    , checkProd.get(x).getpPriceAdults()," ", checkProd.get(x).getpPriceElders());//use getter to call the product information
        }
        System.out.println("\n");
        System.out.println("\n==================================================================================================");
    }
    
    public static void delProd(ArrayList<Product> checkProd){
        checkProd = Product.getProductList();//passing in getProductList
        Scanner scn = new Scanner(System.in);
        char deleteID;

        System.out.print("Enter the product ID wanted to delete : ");
        deleteID = scn.next().charAt(0);//get input for delete
        int temp = 0;
        for(int i =0;i<checkProd.size();i++){
         if(checkProd.get(i).getProductID()== deleteID){
             checkProd.remove(i);//remove the id from the input
             temp=1;
            System.out.println("Record deleted successfully !");
            }   
        }
        if(temp!=1){
            System.out.println("ID does not exist ");
        }

    }

        
}
