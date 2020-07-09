import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Table {
    Stage stage;
    Scene scene;
    UserPage1 userPage1;
    Tickets tickets=new Tickets();
    private static int selectedIndex=0;
    private static int numberOfTickets=0;
    User user;
    Table(Stage stage,User user){
        this.stage=stage;
        this.user=user;
    }
    AlertBox alertBox;

    public Scene getScene() {
        return scene;
    }

    public void prepareScene(){
        tickets.load();
        TableView<Product>table;
        TableColumn<Product,String>descriptionColumn=new TableColumn<>("Description");
        descriptionColumn.setMinWidth(200);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Product,String>typeColumn=new TableColumn<>("Type");
        typeColumn.setMinWidth(200);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Product,Integer>stopColumn=new TableColumn<>("Stops");
        stopColumn.setMinWidth(200);
        stopColumn.setCellValueFactory(new PropertyValueFactory<>("stops"));

        TableColumn<Product,Double>priceColumn=new TableColumn<>("Price");
        priceColumn.setMinWidth(200);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product,Double>seatsColumn=new TableColumn<>("Available seats");
        seatsColumn.setMinWidth(200);
        seatsColumn.setCellValueFactory(new PropertyValueFactory<>("availableSeats"));

        table=new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(descriptionColumn,typeColumn,stopColumn,priceColumn,seatsColumn);

        TextField textField=new TextField("Enter the no. of tickets.");
        Button button=new Button("Back");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(userPage1.getScene());
            }
        });
        Button button1=new Button("Add");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 numberOfTickets=Integer.parseInt(textField.getText());
                 selectedIndex=table.getSelectionModel().getSelectedIndex();
                user.addTrip(selectedIndex,numberOfTickets);

            }
        });
        Button button2=new Button("Proceed to payment");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                alertBox=new AlertBox(user,tickets);
                alertBox.display("The total price is: ",tickets.getPrice(selectedIndex),numberOfTickets);
                table.setItems(getProduct());

            }
        });
        VBox vBox=new VBox();
        HBox hBox=new HBox(50);
        hBox.getChildren().addAll(button,textField,button1,button2);
        vBox.getChildren().addAll(table,hBox);

        scene=new Scene(vBox);


    }

    public ObservableList<Product>getProduct(){
        tickets.load();
        ObservableList<Product>products=FXCollections.observableArrayList();
        for(int i=0;i<tickets.getIndex();i++)
        products.add(new Product(tickets.Description.get(i),tickets.Type.get(i),tickets.Stop.get(i),tickets.Price.get(i),tickets.AvailableSeats.get(i)));
        return products;
    }

    public void setUserPage1(UserPage1 userPage1) {
        this.userPage1 = userPage1;
    }
}
