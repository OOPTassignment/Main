/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooptassignment;

import java.util.ArrayList;

/*import java.util.Scanner;

/**
 *
 * @author Lim Chee Ziong
 */
public class Product {
    private char productID;
    private String prodName;
    private double pPriceKids;
    private double pPriceAdults;
    private double pPriceElders;
    private static ArrayList<Product> ProductList;
    public Product() {
        
    }
    
    public Product(char productID,String prodName, double pPriceKids, double pPriceAdults,double pPriceElders){
        this.productID = productID;
        this.prodName=prodName;
        this.pPriceKids=pPriceKids;
        this.pPriceAdults=pPriceAdults;
        this.pPriceElders=pPriceElders;

    }

    public char getProductID() {
        return productID;
    }

    public void setProductID(char productID) {
        this.productID = productID;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public double getpPriceKids() {
        return pPriceKids;
    }

    public void setpPriceKids(double pPriceKids) {
        this.pPriceKids = pPriceKids;
    }

    public double getpPriceAdults() {
        return pPriceAdults;
    }

    public void setpPriceAdults(double pPriceAdults) {
        this.pPriceAdults = pPriceAdults;
    }

    public double getpPriceElders() {
        return pPriceElders;
    }

    public void setpPriceElders(double pPriceElders) {
        this.pPriceElders = pPriceElders;
    }

    public static ArrayList<Product> getProductList() {
        return ProductList;
    }

    public static void setProductList(ArrayList<Product> ProductList) {
        Product.ProductList = ProductList;
    }

    public static void initProd(){
        ProductList = new ArrayList<>();
        ProductList.add(new Product('A', "Family Dinner", 12.99, 18.99, 14.99));
        ProductList.add(new Product('B', "Pork Festive", 18.99, 24.99, 20.99));
        ProductList.add(new Product('C', "All in One", 14.99, 30.99,24.99 ));
    }
   
   
}
