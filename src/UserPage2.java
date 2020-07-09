import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserPage2 {
    User user;
    UserPage1 userPage1;
    Scene scene;
    Stage stage;
    UserPage2(Stage stage,User user){this.stage=stage;this.user=user;}
    public ObservableList<Product> getProduct(){

        ObservableList<Product>products=FXCollections.observableArrayList();
        for(int i=0;i<user.getWords().size();i++) {

            products.add(new Product(user.getUserDescription(i),user.getUserType(i),user.getUserStops(i),user.getUserTickets(i)));
        }
        return products;
    }
    public void prepareScene(){

        Button button=new Button("Back");
        TableView<Product>table;
        //Trips Column
        TableColumn<Product,String> descriptionColumn=new TableColumn<>("Description");
        descriptionColumn.setMinWidth(200);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Product,String> typeColumn=new TableColumn<>("Type");
        typeColumn.setMinWidth(200);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Product,String> stopsColumn=new TableColumn<>("Number of Stops");
        stopsColumn.setMinWidth(200);
        stopsColumn.setCellValueFactory(new PropertyValueFactory<>("stops"));

        TableColumn<Product,String> ticketsColumn=new TableColumn<>("No. of Tickets");
        ticketsColumn.setMinWidth(200);
        ticketsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfTickets"));


        table=new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(descriptionColumn,typeColumn,stopsColumn,ticketsColumn);
        Button button1=new Button("Delete");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               user.DeleteUserTrip(table.getSelectionModel().getSelectedIndex());
                ObservableList<Product>allProducts,deletedProduct;
                allProducts=table.getItems();
                deletedProduct=table.getSelectionModel().getSelectedItems();
                deletedProduct.forEach(allProducts::remove);
            }
        });
        Button button2=new Button("Save");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                user.save();
            }
        });
        VBox vbox=new VBox();
        HBox hbox=new HBox(50);
        hbox.getChildren().addAll(button,button1,button2);
        vbox.getChildren().addAll(table,hbox);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(userPage1.getScene());
            }
        });
        scene=new Scene(vbox);

    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setUserPage1(UserPage1 userPage1) {
        this.userPage1 = userPage1;
    }
}
