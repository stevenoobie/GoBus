
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class UserPage1  {
    LoginPage loginPage;
    Scene scene;
    Stage stage;
    Table table;
    User user;
    UserPage2 userPage2;

    UserPage1(Stage stage,User user){this.stage=stage;
        this.user=user;}
    public void prepareScene(){

        Button viewTrips=new Button("View trips");
        Button addTrip=new Button("Add trips");
        VBox vbox=new VBox(20);
        Label welcoming = new Label("**  Welcome  ** ");

        GridPane grid = new GridPane() ;
        vbox.getChildren().addAll(viewTrips,addTrip);
        grid.add(welcoming, 6, 0);
        grid.add(vbox,6,3);
        grid.setVgap(20);
        grid.setHgap(20);


        Button button=new Button("Back");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(loginPage.getScene());
            }
        });

        viewTrips.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                userPage2.prepareScene();
                stage.setScene(userPage2.getScene());


            }
        });
        // addTrip.setOnAction();
        addTrip.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(table.getScene());
            }
        });
        grid.add(button, 9, 8);
        scene=new Scene(grid,350,300);
    }

    public Scene getScene(){return scene;}
    public void setScene(Scene scene){this.scene = scene;}
    public void setLogin(LoginPage loginPage){this.loginPage=loginPage;}

    public void setUserPage2(UserPage2 userPage2) {
        this.userPage2 = userPage2;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
