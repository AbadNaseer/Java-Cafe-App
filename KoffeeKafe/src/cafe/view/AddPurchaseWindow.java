package cafe.view;

import cafe.model.Cafe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AddPurchaseWindow extends AnchorPane{

	public Label titleLbl;
	public Button placeOrderBtn;
	public Button cancelBtn;
	public Label itemNameLbl;
	public Label itemPriceLbl;
	public Label nameLbl;
	public Label priceLbl;
	public TextField qtyField;
    
    public AddPurchaseWindow(Cafe cafe,String name, int orderid) {
    	
    	System.out.println(name);
    	
        titleLbl = new Label("Purchase Item");
        titleLbl.setText("#7c7171");
        titleLbl.setFont(new Font("Berlin Sans FB", 31.0));
        titleLbl.setLayoutX(218.0);
        titleLbl.setLayoutY(43.0);
        
        placeOrderBtn = new Button("Ok");
        placeOrderBtn.setPrefWidth(137.0);
        placeOrderBtn.setPrefHeight(37.0);
        placeOrderBtn.setStyle("-fx-background-color: green;");
        placeOrderBtn.setText("Ok");
        placeOrderBtn.setFont(new Font("System Bold", 16.0));
        placeOrderBtn.setLayoutX(179.0);
        placeOrderBtn.setLayoutY(419.0);
        
        cancelBtn = new Button("Cancel");
        cancelBtn.setPrefWidth(137.0);
        cancelBtn.setPrefHeight(37.0);
        cancelBtn.setStyle("-fx-background-color: red;");
        cancelBtn.setText("Cancel");
        cancelBtn.setFont(new Font("System Bold", 16.0));
        cancelBtn.setLayoutX(327.0);
        cancelBtn.setLayoutY(419.0);
        
        itemNameLbl = new Label("Item Name:");
        itemNameLbl.setFont(new Font(18.0));
        itemNameLbl.setLayoutX(154.0);
        itemNameLbl.setLayoutY(185.0);
        
        itemPriceLbl = new Label("Item Price:");
        itemPriceLbl.setFont(new Font(18.0));
        itemPriceLbl.setLayoutX(154.0);
        itemPriceLbl.setLayoutY(230.0);
        
        nameLbl = new Label();
        nameLbl.setPrefWidth(148.0);
        nameLbl.setPrefHeight(27.0);
        nameLbl.setFont(new Font(18.0));
        nameLbl.setLayoutX(292.0);
        nameLbl.setLayoutY(185.0);
        
        priceLbl = new Label();
        priceLbl.setPrefWidth(148.0);
        priceLbl.setPrefHeight(27.0);
        priceLbl.setFont(new Font(18.0));
        priceLbl.setLayoutX(292.0);
        priceLbl.setLayoutY(230.0);
        
        qtyField = new TextField();
        qtyField.setPromptText("Enter quantity you want to purchase");
        qtyField.setPrefWidth(284.0);
        qtyField.setPrefHeight(37.0);
        qtyField.setLayoutX(173.0);
        qtyField.setLayoutY(315.0);
        
        getChildren().addAll(titleLbl, placeOrderBtn, cancelBtn, itemNameLbl, itemPriceLbl, nameLbl, priceLbl, qtyField);
        
        displayPricedata(cafe,name,nameLbl,priceLbl);
        
        placeOrderBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	addToCurrOrder(cafe,name,qtyField.getText(),orderid);        	
            }
        });
        
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	closeWindow();        	
            }
        });
    }
   
	
    public void displayPricedata(Cafe cafe, String name, Label nameL, Label priceL) {
    	if(name!=null) {
    		cafe.model.MenuItem mi = cafe.getMenuItemByName(name);
    		if(mi!=null) {
    			nameL.setText(name);
    			priceL.setText(""+mi.getBaseCost());
    			return;
    		}
    	}
    		nameL.setText("NULL");
    		priceL.setText("NULL");
    	
    	
    	
    }
    
    public void addToCurrOrder(Cafe cafe, String name, String qty, int orderid) {
    	
    	int qt = -1;
    	
    	if(qty.equals("")) {
    		System.out.println("Empty value not allowed");
    		return;
    	}
    		
    	
    	try {
    		qt=Integer.parseInt(qty);
    	}catch(Exception e) {
    		System.out.println(e);
    	}
    	
    	if(qt!=-1) {
    		cafe.addLineItem(orderid, name, qt);
    		closeWindow();
    	}else {
    		System.out.println(" Could process qty!");
    	}
    }
    
    public void closeWindow() {
    	Stage stage = (Stage) cancelBtn.getScene().getWindow();
    	System.out.println("Done!");
        stage.close();   
    }
}
