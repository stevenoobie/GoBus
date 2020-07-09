import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginPage {
    Scene scene;
    Stage stage;
    UserPage1 userPage1;
    DriverPage1 driverPage1;
    public void setUserPage1(UserPage1 userPage1){this.userPage1=userPage1;}
    LoginPage(Stage stage,User user,Driver driver,Manager manager){this.stage=stage;this.user=user;this.driver=driver;this.manager=manager;}
    User user;
    Driver driver;
    Manager manager;
    ManagerPage1 managerPage1;
    public void prepareScene(){
     RadioButton radioButton1=new RadioButton("User");
     RadioButton radioButton2=new RadioButton("Driver");
     RadioButton radioButton3=new RadioButton("Manager");
        Label label1=new Label("Email: ");
        Label label2=new Label("Password: ");
        TextField email=new TextField();
        Label Error=new Label();
        PasswordField password=new PasswordField();
        Button submit=new Button("Submit");
        GridPane grid=new GridPane();
        grid.add(label1,0,0);
        grid.add(email,1,0);
        grid.add(label2,0,1);
        grid.add(password,1,1);
        grid.add(radioButton1,0,3);
        grid.add(radioButton2,0,4);
        grid.add(radioButton3,0,5);
        ToggleGroup group=new ToggleGroup();// 3shan ynzmhom sawa
        radioButton1.setToggleGroup(group);
        radioButton2.setToggleGroup(group);
        radioButton3.setToggleGroup(group);
        grid.add(submit,2,0);
        grid.add(Error,2,1);
        scene=new Scene(grid,600,400);
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(radioButton1.isSelected())
                {
                    String Email=email.getText();
                    String Password=password.getText();
                    boolean x=user.validate(Email,Password);
                    if(x==true) {
                        //"Set Scene to user's page"
                        email.setText("");
                        password.setText("");
                        stage.setScene(userPage1.getScene());

                    }else
                        Error.setText("Invalid Email/Password");
                }
                else if(radioButton2.isSelected())
                {
                    String Email=email.getText();
                    String Password=password.getText();
                    boolean x=driver.validate(Email,Password);
                    if(x==true)
                    {email.setText("");
                        password.setText("");
                        driverPage1.prepareScene();

                        stage.setScene(driverPage1.getScene());
                    }
                    else
                        Error.setText("Invalid Email/Password");
                }
                else if(radioButton3.isSelected())
                {
                    String Email=email.getText();
                    String Password=password.getText();
                    boolean x=manager.validate(Email,Password);
                    if(x==true)
                    {email.setText("");
                        password.setText("");
                        managerPage1.prepareScene();
                        stage.setScene(managerPage1.getScene());
                    }
                    else
                        Error.setText("Invalid Email/Password");
                }
                else
                    Error.setText("Invalid Email/Password");

            }
        });




    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setDriverPage1(DriverPage1 driverPage1) {
        this.driverPage1 = driverPage1;
    }

    public void setManagerPage1(ManagerPage1 managerPage1) {
        this.managerPage1 = managerPage1;
    }
}


