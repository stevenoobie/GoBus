import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DriverPage2 {
    Stage stage;
    Scene scene;
    DriverPage1 driverpage1;


    Driver driver;
    DriverPage2(Stage stage,Driver driver){
        this.stage=stage;
        this.driver=driver;
    }

    public Scene getScene() {
        return scene;
    }


    public void prepareScene(){



        TableView<Product>table;

        TableColumn<Product,String>descriptionColumn=new TableColumn<>("Description");
        descriptionColumn.setMinWidth(400);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Product,String> typeColumn=new TableColumn<>("Type");
        typeColumn.setMinWidth(200);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Product,String> stopsColumn=new TableColumn<>("Number of Stops");
        stopsColumn.setMinWidth(200);
        stopsColumn.setCellValueFactory(new PropertyValueFactory<>("stops"));

        TableColumn<Product,String> vehicleColumn=new TableColumn<>("Vehicle");
        vehicleColumn.setMinWidth(200);
        vehicleColumn.setCellValueFactory(new PropertyValueFactory<>("vehicle"));

        table =new TableView<>();
        table.setItems(getTrips());
        table.getColumns().addAll(descriptionColumn,typeColumn,stopsColumn,vehicleColumn);


        Button button=new Button("Back");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(driverpage1.getScene());
            }
        });

        HBox hBox=new HBox(50);
        hBox.getChildren().addAll(button);
        VBox vBox=new VBox();
        vBox.getChildren().addAll(table,hBox);

        scene=new Scene(vBox);
    }




    public ObservableList<Product> getTrips(){

        ObservableList<Product> trips =FXCollections.observableArrayList();

        for(int i=0;i<driver.getValidDriverJ();i++)
            trips.add(new Product (driver.getDriverDescription(i),driver.getDriverType(i),driver.getDriverStops(i),driver.getDriverVehicle(i)));
        return trips;
    }

    public void setDriverPage1(DriverPage1 driverpage) {
        this.driverpage1 = driverpage;
    }
}