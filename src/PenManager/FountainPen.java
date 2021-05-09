package PenManager;

import DBConnection.FPDBConnection;

import java.util.Date;

public class FountainPen {
    private int penID;
    private String modelName,
            brand,
            color;
    private double price;
    private Nib size;
    private FillingMechanism mechanism;
    private Date dateEntered;

    FountainPen(String modelName, String brand, String color,double price, FillingMechanism mechanism, Nib nib){
        this.modelName = modelName;
        this.brand = brand;
        this.color = color;
        this.price = price;
        this.size = nib;
        this.mechanism =mechanism;
        this.dateEntered = new java.util.Date();
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

    public Nib getSize(){
        return size;
    }

    public Date getDateEntered(){
        return dateEntered;
    }

    public FillingMechanism getMechanism(){
        return mechanism;
    }
    //Setter methods
    public void setPenID(){
        this.penID=penID;
    }
    public void setModelName(String modelName){
        this.modelName = modelName;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }
    public void setColor(String color){
        this.color=color;
    }
    public void setPrice(double price){
        this.price=price;
    }
    public void setSize(Nib nib){
        this.size = nib;
    }

    public void setDateEntered(Date dateEntered){
        this.dateEntered = dateEntered;
    }

    public void setMechanism(FillingMechanism mechanism){
        this.mechanism=mechanism;
    }


}

// Used to represent the most common nib sizes
enum Nib {
    EXTRAFINE,
    FINE,
    MEDIUM,
    BROAD,
    ITALIC,
    FLEX
}

//Used to represent the most common filliing mechanisms
enum FillingMechanism{
    CONVERTER, // Used for both cartrige and converter pens
    PISTON,
    VACUUM,
    EYEDROPPER
}