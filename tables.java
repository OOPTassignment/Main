public class tables{
    private int tableNo;
    private String occupy;
    private int personCount;
    private int adultCount;
    private int childCount;
    private int elderCount;
    private char comboSet;

    public tables(int tableNo, String occupy, int personCount, int adultCount, int childCount, int elderCount, char comboSet) {
        this.tableNo = tableNo;
        this.occupy = occupy;
        this.personCount = personCount;
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.elderCount = elderCount;
        this.comboSet = comboSet;
    }

    public char getComboSet() {
        return comboSet;
    }

    public void setComboSet(char comboSet) {
        this.comboSet = comboSet;
    }
    
    
    public int getTableNo() {
        return tableNo;
    }

    public String getOccupy() {
        return occupy;
    }

    public int getPersonCount() {
        return personCount;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public int getElderCount() {
        return elderCount;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public void setOccupy(String occupy) {
        this.occupy = occupy;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public void setElderCount(int elderCount) {
        this.elderCount = elderCount;
    }
       
 
               
}