import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class DriverPage1 {
    Scene scene;
    Stage stage;
    LoginPage loginpage;

    Driver driver;

   // Table2 table;
    DriverPage2 driverPage2;

    DriverPage1(Stage stage , Driver driver){
        this.stage=stage;
        this.driver= driver;
    }



    public void prepareScene(){


        String x=driver.getName();
        int y=driver.getAge();
        Button addTrip = new Button("View Your Assigned Trips");
        Label name = new Label ("Name : "+ x);
        Label Age =new Label ("Age : " +y);
        Button back=new Button("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(loginpage.getScene());
            }
        });
        //***********************
        //***********************
        GridPane root = new GridPane();
        VBox vbDriver = new VBox(20 ,name , Age , addTrip,back);
        vbDriver.setPadding(new Insets(10));
        root.addRow(0, vbDriver);
        //***********************
        //***********************
        addTrip.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                driverPage2.prepareScene();
                stage.setScene(driverPage2.getScene());
            }
        });
        //***********************
        //***********************
        scene=new Scene(root , 400 , 400);

    }


    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setLoginPage(LoginPage loginpage) {
        this.loginpage = loginpage;
    }


    public void setDriverPage2(DriverPage2 driverPage2) {
        this.driverPage2 = driverPage2;
    }
}