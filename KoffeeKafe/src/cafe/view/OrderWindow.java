package cafe.view;

import java.util.ArrayList;
import java.util.List;

import cafe.model.Cafe;
import cafe.model.MenuItem;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
//import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class OrderWindow extends AnchorPane{

	static String selected;
	
	public OrderWindow(Cafe cafeObj, int orderId){
		
		Label label = new Label("Place Order");
        label.setLayoutX(218.0);
        label.setLayoutY(43.0);
        label.setTextFill(Color.web("#7c7171"));
        label.setFont(Font.font("Berlin Sans FB", 31.0));

        ListView<String> menuList = new ListView<>();
        menuList.setLayoutX(49.0);
        menuList.setLayoutY(123.0);
        menuList.setPrefWidth(507.0);
        menuList.setPrefHeight(279.0);

        Button addItemBtn = new Button("Add selected item");
        addItemBtn.setLayoutX(68.0);
        addItemBtn.setLayoutY(462.0);
        addItemBtn.setPrefWidth(171.0);
        addItemBtn.setPrefHeight(37.0);
        addItemBtn.setStyle("-fx-background-color: blue;");
        addItemBtn.setTextFill(Color.web("#ebe8e8"));
        addItemBtn.setFont(Font.font("System Bold", 16.0));

        Button placeOrderBtn = new Button("Place Order");
        placeOrderBtn.setLayoutX(259.0);
        placeOrderBtn.setLayoutY(462.0);
        placeOrderBtn.setPrefWidth(137.0);
        placeOrderBtn.setPrefHeight(37.0);
        placeOrderBtn.setStyle("-fx-background-color: green;");
        placeOrderBtn.setTextFill(Color.web("#ebe8e8"));
        placeOrderBtn.setFont(Font.font("System Bold", 16.0));

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setLayoutX(410.0);
        cancelBtn.setLayoutY(462.0);
        cancelBtn.setPrefWidth(137.0);
        cancelBtn.setPrefHeight(37.0);
        cancelBtn.setStyle("-fx-background-color: red;");
        cancelBtn.setTextFill(Color.web("#ebe8e8"));
        cancelBtn.setFont(Font.font("System Bold", 16.0));

        // javafx.scene.control.MenuItem
        
        ComboBox<String> filterComboBox = new ComboBox<>();
        filterComboBox.setLayoutX(440.0);
        filterComboBox.setLayoutY(51.0);
        filterComboBox.setPrefWidth(150.0);
        filterComboBox.setPromptText("Filter By");

       

        filterComboBox.getItems().addAll("Bakery", "Sandwich", "Beverage","Remove Filter");

        // add a listener for changes to the selected item
        filterComboBox.setOnAction(e -> {
            String st = filterComboBox.getSelectionModel().getSelectedItem();
            if(st.equals("Remove Filter"))  displayMenuInList(menuList, cafeObj); 
            else displayMenuInListByCategory(menuList, cafeObj,st);
            
        });
        
        
        getChildren().addAll(label, menuList, addItemBtn, placeOrderBtn, cancelBtn,filterComboBox);
    
        displayMenuInList(menuList, cafeObj);
       
        menuList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                String selectedItem = menuList.getSelectionModel().getSelectedItem();
                String[] parts = selectedItem.split("\\|"); // split the input using the pipe character
                String firstPart = parts[0].trim(); // get the first part and trim any leading or trailing spaces
                selected=firstPart;
                
            }
        });
        
        
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	cafeObj.cancelOrder(orderId);
            	Stage stage = (Stage) cancelBtn.getScene().getWindow();
                stage.close();            	
            }
        });
        
        addItemBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	AddPurchaseWindow ow=new AddPurchaseWindow(cafeObj,selected, orderId);
            	
            	Stage stage=new Stage();
            	Scene scene = new Scene(ow, 600, 675);
            	stage.setScene(scene);
                stage.show();           	
            }
        });
        
        placeOrderBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	ConfirmationWindow ow=new ConfirmationWindow(orderId,cafeObj);
            	
            	Stage stage=new Stage();
            	Scene scene = new Scene(ow, 600, 675);
            	stage.setScene(scene);
                stage.show();
            	
            }
        });

       
        
        
	}
	
	
	public void displayMenuInList(ListView<String> menuList, Cafe cafe) {
		
		 menuList.getItems().clear();
		List<MenuItem> menuItems = new ArrayList<>();
		menuItems = cafe.getAllMenuItems();
		for(int i=0;i<menuItems.size();i++)
			menuList.getItems().add(" "+menuItems.get(i).getName()+" | "+menuItems.get(i).getBaseCost()+" | "+menuItems.get(i).getType());
		
			
		
	}
	
public void displayMenuInListByCategory(ListView<String> menuList, Cafe cafe, String category) {
		menuList.getItems().clear();
		List<MenuItem> menuItems = new ArrayList<>();
		menuItems = cafe.getAllMenuItems();
		for(int i=0;i<menuItems.size();i++)
			if(menuItems.get(i).getType().equalsIgnoreCase(category)) {
				menuList.getItems().add(" "+menuItems.get(i).getName()+" | "+menuItems.get(i).getBaseCost()+" | "+menuItems.get(i).getType());	
				
			}
		
	}
	
}
