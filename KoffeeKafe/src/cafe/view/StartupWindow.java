package cafe.view;

import javafx.scene.text.*;
import javafx.stage.Stage;
import cafe.model.Cafe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.AnchorPane;

public class StartupWindow extends AnchorPane {
    
	
	
    public StartupWindow(Cafe cafeObj) {
        Label label = new Label("KoffeeKafe");
        label.setLayoutX(216.0);
        label.setLayoutY(51.0);
        label.setTextFill(javafx.scene.paint.Color.web("#7c7171"));
        Font font = new Font("Berlin Sans FB", 31.0);
        label.setFont(font);
        
        Button newOrderBtn = new Button("New Order");
        newOrderBtn.setLayoutX(123.0);
        newOrderBtn.setLayoutY(189.0);
        newOrderBtn.setPrefHeight(45.0);
        newOrderBtn.setPrefWidth(344.0);
        newOrderBtn.setTextFill(javafx.scene.paint.Color.web("#f8f4f4"));
        newOrderBtn.setStyle("-fx-background-color: grey;");
        newOrderBtn.setFont(Font.font("System Bold", 21.0));
        
        newOrderBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	int orderid = cafeObj.startOrder();
            	OrderWindow ow=new OrderWindow(cafeObj, orderid);
            	
            	Stage stage=new Stage();
            	Scene scene = new Scene(ow, 600, 675);
            	stage.setScene(scene);
                stage.show();
            	
            }
        });
        
        Button displayOrderBtn = new Button("Display Order");
        displayOrderBtn.setLayoutX(123.0);
        displayOrderBtn.setLayoutY(248.0);
        displayOrderBtn.setPrefHeight(45.0);
        displayOrderBtn.setPrefWidth(344.0);
        displayOrderBtn.setTextFill(javafx.scene.paint.Color.web("#f8f4f4"));
        displayOrderBtn.setStyle("-fx-background-color: grey;");
        displayOrderBtn.setFont(Font.font("System Bold", 21.0));
        
        Button quitBtn = new Button("Quit");
        quitBtn.setLayoutX(123.0);
        quitBtn.setLayoutY(306.0);
        quitBtn.setPrefHeight(45.0);
        quitBtn.setPrefWidth(344.0);
        quitBtn.setTextFill(javafx.scene.paint.Color.web("#f8f4f4"));
        quitBtn.setStyle("-fx-background-color: grey;");
        quitBtn.setFont(Font.font("System Bold", 21.0));
        
        
        
        getChildren().addAll(label, newOrderBtn, displayOrderBtn, quitBtn);
        setPrefHeight(598.0);
        setPrefWidth(619.0);
        
        quitBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	Stage stage = (Stage) quitBtn.getScene().getWindow();
                stage.close();            	
                System.exit(0);
            }
        });
        
        
        displayOrderBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	PlacedOrdersWindow ow=new PlacedOrdersWindow(cafeObj);
            	
            	Stage stage=new Stage();
            	Scene scene = new Scene(ow, 600, 675);
            	stage.setScene(scene);
                stage.show();
            	
            }
        });
        
        
    }
    
    
    
}

