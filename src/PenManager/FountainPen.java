package PenManager;

import DBConnection.DatabaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

/**
 *
 */
public class FountainPen {
    private int penID;
    private String modelName;
    private String brand;
    private String color;
    private String nib;
    private String mechanism;
    private double price;
    private LocalDate dateEntered;

    /**
     *
     */
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

    /**
     * @return - a int representing a pen ID number.
     */
    public int getPenID() {
        return penID;
    }

    /**
     * @return - a String representing the name of a pen.
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * @return - a String representing the manufacturer of a pen.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @return - a String representing the primary body color or theme.
     */
    public String getColor() {
        return color;
    }

    /**
     * @return - a double representing the price paid.
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return - a String representing the nib size.
     */
    public String getNib() {
        return nib;
    }

    /**
     * @return - a String representing the filling mechanism.
     */
    public String getMechanism() {
        return mechanism;
    }

    /**
     * @return - a LocalDate representing the entry date.
     */
    public LocalDate getDateEntered() {
        return dateEntered;
    }

    /**
     * @param penID ID number to set.
     */
    public void setPenID(int penID) {
        this.penID = penID;
    }

    /**
     * @param modelName model name to set.
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * @param brand brand to set.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @param color body color to set.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @param price price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @param nib nib size to set.
     */
    public void setSize(String nib) {
        this.nib = nib;
    }

    /**
     * @param mechanism filling mechanism to set.
     */
    public void setMechanism(String mechanism) {
        this.mechanism = mechanism;
    }

    /**
     * @param dateEntered entry date to set.
     */
    public void setDateEntered(LocalDate dateEntered) {
        this.dateEntered = dateEntered;
    }

    /**
     * Returns a formatted String listing all relevant pen attributes.
     *
     * @return - a String listing the details of a specific FountainPen object.
     */
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

    /**
     * Uses the database connection to execute a query that retrieves the number of records in the database.
     *
     * @return - a int representing the number of pens in the database.
     * @throws SQLException if any SQL related errors occurs when attempting retrieve the count.
     */
    public static int getNumberOfPens() throws SQLException {
        Connection connection = DatabaseManager.getConnection();
        String getCount = "SELECT COUNT(*) FROM Pens";
        Statement selectAll = connection.createStatement();
        ResultSet rs = selectAll.executeQuery(getCount);
        rs.next();
        return rs.getInt(1);
    }

    /**
     * Generates a new pen ID number for a new record.
     *
     * @return - an int that can be used as a pen ID.
     * @throws SQLException if any SQL related errors occurs when calling getNumberOfPens().
     */
    public static int getNewPenID() throws SQLException {
        return getNumberOfPens() + 1;
    }
}
