package PenManager;

import DBConnection.FPDBConnection;

import java.util.Date;

public class FountainPen {
    private int penID;
    private String modelName;
    private String brand;
    private String color;
    private String nib;
    private String mechanism;
    private double price;
    private Date dateEntered;

    FountainPen(String modelName, String brand, String color, String nib,String mechanism,double price, Date dateEntered ){
        this.modelName = modelName;
        this.brand = brand;
        this.color = color;
        this.price = price;
        this.nib = nib;
        this.mechanism =mechanism;
        this.dateEntered = dateEntered;
    }
    //Getter methods
    public double getPenID(){
        return penID;
    }
    public String getModelName(){
        return modelName;
    }

    public String getBrand(){
        return brand;
    }

    public String getColor(){
        return color;
    }
    public double getPrice(){
        return price;
    }

    public String getNib(){
        return nib;
    }

    public Date getDateEntered(){
        return dateEntered;
    }

    public String getMechanism(){
        return mechanism;
    }
    //Setter methods
    public void setPenID(int penID){
        this.penID=penID;
    }

    public void setModelName(String modelName){
        this.modelName = modelName;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public void setColor(String color){
        this.color = color;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setSize(String nib){
        this.nib = nib;
    }

    public void setDateEntered(Date dateEntered){
        this.dateEntered = dateEntered;
    }

    public void setMechanism(String mechanism){
        this.mechanism = mechanism;
    }


}
