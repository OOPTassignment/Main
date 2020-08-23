/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooptassignment;
import java.util.Scanner;

/**
 *
 * @author Lim Chee Ziong
 */
public class ManagerMenu {

    public static void main (String [] args){
        Scanner scn = new Scanner(System.in);
        int selection;
        do{
            System.out.printf("%7s%18s\n","","Manager Menu");
            System.out.println("===================================================");
            System.out.println("| 1 | Product Details");
            System.out.println("===================================================");
            System.out.println("| 2 | Staff Details");
            System.out.println("===================================================");
            System.out.println("| 3 | Manager Details");
            System.out.println("===================================================");
            System.out.println("| 4 | Exit ");
            System.out.println("===================================================");
            System.out.print("Please enter your selection :");
            
            selection = scn.nextInt();
            scn.nextLine();//rewind        
            switch (selection) {
                case 1:
                    ProductDetails.ProductM();
                    break;
                case 2:
                    StaffDetails.StaffM();
                    break;
                case 3:
                    ManagerDetails.ManagerM();
                    break;
                case 4:
                    return;
                default:
                    System.out.print("Invalid code entered.\nPlease enter an existing code :");
            }
        }while(selection!=4);
    }

}
