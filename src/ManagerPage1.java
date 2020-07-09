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

public class ManagerPage1 {
    Manager manager;
    Stage stage;
    Scene scene;
    LoginPage loginPage;
    Driver driver;
    User user;
     static int flag=0;
     static String S,St;
    ManagerPage1(Stage stage,Manager manager,Driver driver,User user){
        this.stage=stage;
        this.manager=manager;
        this.driver=driver;
        this.user=user;
    }
    Tickets tickets=new Tickets();


    public Scene getScene() {
        return scene;
    }

    public void prepareScene(){
        tickets.load();
        TableView<Product> table;
        TableColumn<Product,String> descriptionColumn=new TableColumn<>("Description");
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

        TextField description=new TextField("Description");
        TextField stops=new TextField("No. of Stops");
        Label Error=new Label();
        ComboBox <String> type=new ComboBox<>();
        type.getItems().addAll("Single trip","Double trip");
        type.setPromptText("Pick a type");
        TextField price=new TextField("Price");
        ComboBox<String>vehicle=new ComboBox<>();//for the vehicle type and set Seat number by default
        vehicle.getItems().addAll("Bus","Minibus","Limosine");
        vehicle.setPromptText("Pick a vehicle");
        ComboBox<String>comboBox2=new ComboBox<>();
        for(int i=0;i<driver.getI();i++)
            comboBox2.getItems().add(driver.getNameDrivers(i));
        comboBox2.setPromptText("Choose Driver");

        Button assign= new Button("Assign Trip to Chosen driver");
        assign.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                driver.AddTripsToDriver(comboBox2.getSelectionModel().getSelectedIndex(),table.getSelectionModel().getSelectedIndex());
            }
        });
        Button add=new Button("Add");


        Button back=new Button("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(loginPage.getScene());
            }
        });

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tickets.AddTrip(description.getText(),type.getValue(),Integer.parseInt(stops.getText()),Double.parseDouble(price.getText()),vehicle.getValue());

            }
        });
        Button save=new Button("Save");
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    if(flag==1) {

                        user.save();
                        driver.save();
                    }    tickets.save();
                    table.setItems(getProduct());

            }
        });
        Button delete=new Button("Delete");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                flag=1;
                int k=table.getSelectionModel().getSelectedIndex();
                S=tickets.getDescription(k)+"-"+tickets.getType(k)+"-"+tickets.getStop(k)+"-"+tickets.getVehicle(k);
                System.out.println("S(in delete managerpage): "+S);
                St=tickets.getDescription(k)+"-"+tickets.getType(k)+"-"+tickets.getStop(k);
                tickets.DeleteTrip(table.getSelectionModel().getSelectedIndex());
                Driver.DeleteAfterTripDeletion(S);
                User.DeleteAfterTripDeletion(St);

            }
        });
        VBox vBox=new VBox();
        HBox hBox1=new HBox(10);
        hBox1.getChildren().addAll(description,type,stops,price,vehicle,add);
        HBox hbox2=new HBox(40);
        hbox2.getChildren().addAll(comboBox2,assign);
        HBox hBox3=new HBox(40);
        hBox3.getChildren().addAll(back,delete,save);

        vBox.getChildren().addAll(table,hBox1,hbox2,hBox3);

        scene=new Scene(vBox);


    }

    public ObservableList<Product> getProduct(){
        tickets.load();
        ObservableList<Product>products=FXCollections.observableArrayList();
        for(int i=0;i<tickets.getIndex();i++)
            products.add(new Product(tickets.Description.get(i),tickets.Type.get(i),tickets.Stop.get(i),tickets.Price.get(i),tickets.AvailableSeats.get(i)));
        return products;
    }

    public void setLoginPage(LoginPage loginPage) {
        this.loginPage = loginPage;
    }
}


