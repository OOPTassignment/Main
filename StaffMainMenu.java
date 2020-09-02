
package ooptassignment;
import java.util.Scanner;

public class StaffMainMenu {
    
    public static void main(String[] args) {
        //creates array
        int totalTable = 15;
        tables[] allTable = new tables[totalTable];
        countSummary[] summarying = new countSummary[150];
        //classes
        OpenTheTable option1 = new OpenTheTable();
        EditTheTable option2 = new EditTheTable();
        SearchTheTable option3 = new SearchTheTable();
        Membership option4 = new Membership();
        Bills option5 = new Bills();
        //optionSix option6 = new optionSix();
        
        int choice = 0;
        
        Scanner scan = new Scanner(System.in);
        
        while (choice != 7){
            System.out.println("\tStaff Module"
                                + "\n1.Open Table"
                                + "\n2.Edit Table"
                                + "\n3.Show All Table"
                                + "\n4.MemberShip"
                                + "\n5.Bill"
                                + "\n6.Summary"
                                + "\n7.Logout"
                                + "\n======================"
                                + "");
           choice = scan.nextInt();
           
           switch (choice) {
                case 1:
                   option1.openTable(allTable);
                   break;
                case 2:
                   option2.editTable(allTable);
                   break;
                case 3:
                   option3.showTable(allTable);
                   break;
                case 4:
                   
                   break;
                case 5:
                   Bills.Billing(allTable, summarying);
                   break;
                case 6:
                   
                   break;
                case 7: 
                    System.out.println("Loging out to of staff menu...");
                    return;
                default:
                    System.out.println("That wasn't a choice...");
           }
        }
    }
    
}
