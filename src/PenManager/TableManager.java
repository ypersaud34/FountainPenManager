package PenManager;

import DBConnection.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableManager {

    private static final ObservableList<FountainPen> collection = FXCollections.observableArrayList();

    private static TableView<FountainPen> displayCollection;
    private static FountainPen penToTransfer;

    public static void populateTable(TableView<FountainPen> table,
                                     TableColumn<FountainPen, Integer> penID,
                                     TableColumn<FountainPen, String> modelName,
                                     TableColumn<FountainPen, String> brand,
                                     TableColumn<FountainPen, String> color,
                                     TableColumn<FountainPen, Double> price,
                                     TableColumn<FountainPen, String> nib,
                                     TableColumn<FountainPen, String> fillingMechanism,
                                     TableColumn<FountainPen, Date> dateEntered) throws SQLException {

        ResultSet pens = DatabaseManager.getConnection().createStatement().executeQuery("SELECT * " +
                "FROM pens " +
                "ORDER BY pen_id;");

        while (pens.next()) {
            FountainPen pen = new FountainPen(
                    pens.getInt("pen_id"),
                    pens.getString("model_name"),
                    pens.getString("brand"),
                    pens.getString("color"),
                    pens.getDouble("price"),
                    pens.getString("nib"),
                    pens.getString("filling_mechanism"),
                    pens.getDate("date_entered").toLocalDate());
            collection.add(pen);
        }


        penID.setCellValueFactory(new PropertyValueFactory<>("PenID"));
        modelName.setCellValueFactory(new PropertyValueFactory<>("ModelName"));
        brand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        color.setCellValueFactory(new PropertyValueFactory<>("Color"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        nib.setCellValueFactory(new PropertyValueFactory<>("Nib"));
        fillingMechanism.setCellValueFactory(new PropertyValueFactory<>("Mechanism"));
        dateEntered.setCellValueFactory(new PropertyValueFactory<>("DateEntered"));

        table.setItems(collection);
        displayCollection = table;
        DatabaseManager.close();

    }

    public static void emptyTable() {
        displayCollection.getItems().clear();
    }

    public static TableView<FountainPen> getTable() {
        return displayCollection;
    }

    /**
     * @return the stored FountainPen object.
     */
    public static FountainPen getDataToTransfer() {
        return penToTransfer;
    }

    /**
     * @param penToTransfer - the pen to store.
     */
    public static void setDataToTransfer(FountainPen penToTransfer) {
        TableManager.penToTransfer = penToTransfer;
    }
}
