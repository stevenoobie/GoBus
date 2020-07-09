import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Scanner;
public class Main extends Application {
    public static void main(String[]args){
        launch(args);
    }
    public void start(Stage primaryStage)throws Exception{
        User user=new User();
        Driver driver=new Driver();
        Manager manager=new Manager();

        primaryStage.setTitle("GoBus");
        LoginPage loginPage=new LoginPage(primaryStage,user,driver,manager);
        UserPage1 userPage1=new UserPage1(primaryStage,user);
        DriverPage1 driverPage1=new DriverPage1(primaryStage,driver);
        DriverPage2 driverPage2=new DriverPage2(primaryStage,driver);
        UserPage2 userPage2=new UserPage2(primaryStage,user);
        Table table=new Table(primaryStage,user);
        ManagerPage1 managerPage1=new ManagerPage1(primaryStage,manager,driver,user);

        loginPage.prepareScene();
        userPage1.prepareScene();
        //userPage2.prepareScene();
        table.prepareScene();
        //driverPage1.prepareScene();
        //managerPage1.prepareScene();
//        driverPage2.prepareScene();

        primaryStage.setScene(loginPage.getScene());
        loginPage.setUserPage1(userPage1);
        userPage1.setUserPage2(userPage2);
        userPage2.setUserPage1(userPage1);
        userPage1.setTable(table);
        table.setUserPage1(userPage1);
        userPage1.setLogin(loginPage);
        driverPage1.setLoginPage(loginPage);
        loginPage.setDriverPage1(driverPage1);
        driverPage1.setDriverPage2(driverPage2);
        driverPage2.setDriverPage1(driverPage1);
        loginPage.setManagerPage1(managerPage1);
        managerPage1.setLoginPage(loginPage);

        primaryStage.show();
    }
}
