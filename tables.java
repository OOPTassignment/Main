package ooptassignment;

public class tables{
    private int tableNo;
    private String occupy;
    private int personCount;
    private int adultCount;
    private int childCount;
    private int elderCount;
    private char comboSet;
    private String tableTime;

    public tables(int tableNo, String occupy, int personCount, int adultCount, int childCount, int elderCount, char comboSet, String tableTime) {
        this.tableNo = tableNo;
        this.occupy = occupy;
        this.personCount = personCount;
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.elderCount = elderCount;
        this.comboSet = comboSet;
        this.tableTime = tableTime;
    }

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public String getOccupy() {
        return occupy;
    }

    public void setOccupy(String occupy) {
        this.occupy = occupy;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public int getElderCount() {
        return elderCount;
    }

    public void setElderCount(int elderCount) {
        this.elderCount = elderCount;
    }

    public char getComboSet() {
        return comboSet;
    }

    public void setComboSet(char comboSet) {
        this.comboSet = comboSet;
    }

    public String getTableTime() {
        return tableTime;
    }

    public void setTableTime(String tableTime) {
        this.tableTime = tableTime;
    }

   
               
}
