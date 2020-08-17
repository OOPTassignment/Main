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
public class Employees {
    private String name;
    private String id;
    private String pss;

    public Employees() {
    }

    public Employees(String name, String id, String pss) {
        this.name = name;
        this.id = id;
        this.pss = pss;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPss() {
        return pss;
    }

    public void setPss(String pss) {
        this.pss = pss;
    }
    
    
}
