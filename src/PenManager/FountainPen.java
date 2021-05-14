package PenManager;

import DBConnection.FPDBConnection;

import java.sql.*;
import java.time.LocalDate;

public class FountainPen {
    private int penID;
    private String modelName;
    private String brand;
    private String color;
    private String nib;
    private String mechanism;
    private double price;
    private LocalDate dateEntered;

    FountainPen(int penID, String modelName, String brand, String color, double price, String nib, String mechanism, LocalDate dateEntered) {
        this.penID = penID;
        this.modelName = modelName;
        this.brand = brand;
        this.color = color;
        this.price = price;
        this.nib = nib;
        this.mechanism = mechanism;
        this.dateEntered = dateEntered;
    }

    //Getter methods
    public int getPenID() {
        return penID;
    }

    public String getModelName() {
        return modelName;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public String getNib() {
        return nib;
    }

    public LocalDate getDateEntered() {
        return dateEntered;
    }

    public String getMechanism() {
        return mechanism;
    }

    //Setter methods
    public void setPenID(int penID) {
        this.penID = penID;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSize(String nib) {
        this.nib = nib;
    }

    public void setDateEntered(LocalDate dateEntered) {
        this.dateEntered = dateEntered;
    }

    public void setMechanism(String mechanism) {
        this.mechanism = mechanism;
    }

    @Override
    public String toString() {
        return "Pen ID: " + penID + "\n" +
                "Model Name: " + modelName + "\n" +
                "Brand: " + brand + "\n" +
                "Color: " + color + "\n" +
                "Nib: " + nib + "\n" +
                "Price: " + price + "\n" +
                "Filling Mechanism: " + mechanism + "\n" +
                "Date Entered: " + dateEntered.toString();
    }

    public static int getNumberOfPens() throws SQLException {

            Connection connection = FPDBConnection.getConnection();
            String getCount="SELECT COUNT(*) FROM Pens";
            Statement selectAll= connection.createStatement();
            ResultSet rs =  selectAll.executeQuery(getCount);
            rs.next();
            return rs.getInt(1);


    }
}
