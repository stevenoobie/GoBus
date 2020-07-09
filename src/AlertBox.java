import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox {
    User user;
    Tickets tickets;
    AlertBox(User user,Tickets tickets){
        this.user=user;
        this.tickets=tickets;

    }
    public void display(String Message,Double pricePerTicket,int numOfTickets)
    {

        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Label text=new Label();
        Label value=new Label();
        Button back=new Button("back");
        Button save=new Button("Save");
        ComboBox<String>comboBox=new ComboBox<>();
        comboBox.setPromptText("Payment Method");
        comboBox.getItems().addAll("Cash","Credit card");
        text.setText(Message);
        value.setText(" "+pricePerTicket*numOfTickets);
        HBox hBox=new HBox(20);
        hBox.getChildren().addAll(back,save,comboBox);
        VBox layout=new VBox(10);
        back.setOnAction(e -> window.close());
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                user.save();
            }
        });
        layout.getChildren().addAll(text,value,hBox);
        layout.setAlignment(Pos.CENTER);

        window.setMinWidth(500);
        Scene scene=new Scene(layout);

        window.setScene(scene);
        window.showAndWait();

    }
}

