package ooptassignment;

public class countSummary {
    private int countAdult;  //head
    private int countKid;
    private int countElder;
    private double SST;
    private double totalAdult; //Price
    private double totalKid; //Price
    private double totalElder; //Price
    private char set; //ComboSet
    private String memberID; 
    private int dayVisited; 
    private double balance; 
    private double subTotal; //before discount and SST
    private double grandTotal; //after discount and SST
    private boolean isMember;

    public countSummary(int countAdult, int countKid, int countElder, double SST, double totalAdult, double totalKid, double totalElder, char set, String memberID, int dayVisited, double balance, double subTotal, double grandTotal, boolean isMember) {
        this.countAdult = countAdult;
        this.countKid = countKid;
        this.countElder = countElder;
        this.SST = SST;
        this.totalAdult = totalAdult;
        this.totalKid = totalKid;
        this.totalElder = totalElder;
        this.set = set;
        this.memberID = memberID;
        this.dayVisited = dayVisited;
        this.balance = balance;
        this.subTotal = subTotal;
        this.grandTotal = grandTotal;
        this.isMember = isMember;
    }

    public int getCountAdult() {
        return countAdult;
    }

    public void setCountAdult(int countAdult) {
        this.countAdult = countAdult;
    }

    public int getCountKid() {
        return countKid;
    }

    public void setCountKid(int countKid) {
        this.countKid = countKid;
    }

    public int getCountElder() {
        return countElder;
    }

    public void setCountElder(int countElder) {
        this.countElder = countElder;
    }

    public double getSST() {
        return SST;
    }

    public void setSST(double SST) {
        this.SST = SST;
    }

    public double getTotalAdult() {
        return totalAdult;
    }

    public void setTotalAdult(double totalAdult) {
        this.totalAdult = totalAdult;
    }

    public double getTotalKid() {
        return totalKid;
    }

    public void setTotalKid(double totalKid) {
        this.totalKid = totalKid;
    }

    public double getTotalElder() {
        return totalElder;
    }

    public void setTotalElder(double totalElder) {
        this.totalElder = totalElder;
    }

    public char getSet() {
        return set;
    }

    public void setSet(char set) {
        this.set = set;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public int getDayVisited() {
        return dayVisited;
    }

    public void setDayVisited(int dayVisited) {
        this.dayVisited = dayVisited;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public boolean isIsMember() {
        return isMember;
    }

    public void setIsMember(boolean isMember) {
        this.isMember = isMember;
    }
    
}
