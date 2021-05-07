package PenManager;

import java.util.Date;

public class FountainPen {
    private String name, //Refers to model name
            brand,
            color;
    private Nib size;
    private FillingMechanism mechanism;
    private Date dateEntered;

    FountainPen(String name, String brand, String color, FillingMechanism mechanism, Nib size){
        this.name = name;
        this.brand = brand;
        this.color = color;
        this.size = size;
        this.mechanism =mechanism;
        this.dateEntered = new java.util.Date();
    }
    //Getter methods
    public String getName(){
        return name;
    }

    public String getBrand(){
        return brand;
    }

    public String getColor(){
        return color;
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
    public void setName(String name){
        this.name = name;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public void setSize(Nib size){
        this.size = size;
    }

    public void setDateEntered(Date dateEntered){
        this.dateEntered = dateEntered;
    }
    public void setColor(String color){
        this.color=color;
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