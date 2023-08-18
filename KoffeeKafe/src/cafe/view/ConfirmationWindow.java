package cafe.view;

import java.util.List;

import cafe.model.Cafe;
import cafe.model.Order;
import cafe.model.OrderItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ConfirmationWindow extends AnchorPane{

	public ConfirmationWindow(int orderid, Cafe cafeObj) {
		Label checkoutLbl = new Label("CheckOut");
        checkoutLbl.setLayoutX(245);
        checkoutLbl.setLayoutY(41);
        checkoutLbl.setTextFill(javafx.scene.paint.Color.web("#7c7171"));
        checkoutLbl.setFont(new Font("Berlin Sans FB", 31.0));

        Label nameLbl = new Label();
        nameLbl.setLayoutX(292);
        nameLbl.setLayoutY(185);
        nameLbl.setPrefWidth(148);
        nameLbl.setPrefHeight(27);
        nameLbl.setFont(new Font(18));

        Label priceLbl = new Label();
        priceLbl.setLayoutX(292);
        priceLbl.setLayoutY(230);
        priceLbl.setPrefWidth(148);
        priceLbl.setPrefHeight(27);
        priceLbl.setFont(new Font(18));

        TextArea checkoutTextArea = new TextArea();
        checkoutTextArea.setLayoutX(79);
        checkoutTextArea.setLayoutY(112);
        checkoutTextArea.setPrefWidth(479);
        checkoutTextArea.setPrefHeight(255);

        Button checkoutBtn = new Button("Place Order");
        checkoutBtn.setLayoutX(179);
        checkoutBtn.setLayoutY(419);
        checkoutBtn.setPrefWidth(137);
        checkoutBtn.setPrefHeight(37);
        checkoutBtn.setTextFill(javafx.scene.paint.Color.web("#ebe8e8"));
        checkoutBtn.setStyle("-fx-background-color: green;");
        checkoutBtn.setFont(new Font("System Bold", 16));

        Button returnBtn = new Button("Cancel");
        returnBtn.setLayoutX(327);
        returnBtn.setLayoutY(419);
        returnBtn.setPrefWidth(137);
        returnBtn.setPrefHeight(37);
        returnBtn.setTextFill(javafx.scene.paint.Color.web("#ebe8e8"));
        returnBtn.setStyle("-fx-background-color: red;");
        returnBtn.setFont(new Font("System Bold", 16));

        getChildren().addAll(checkoutLbl, nameLbl, priceLbl, checkoutTextArea, checkoutBtn, returnBtn);
    
        
        displaytext(checkoutTextArea,orderid,cafeObj);
        
        returnBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	Stage stage = (Stage) returnBtn.getScene().getWindow();
                stage.close();            	
            }
        });
        
        checkoutBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	cafeObj.placeOrder(orderid);
            	Stage stage = (Stage) returnBtn.getScene().getWindow();
                stage.close();            	
            }
        });
        
        
	}
	
	public void displaytext(TextArea area, int orderid, Cafe cafe) {
		Order order = cafe.findPendingOrder(orderid);
		
		if(order!=null) {
			area.appendText("\n\n\t\tOrder Id: "+orderid+"\n\n");
			area.appendText("---------------------------------------------------------------------");
			List<OrderItem> items = order.getOrderedItems();
			for(int i=0;i<items.size();i++) {
				area.appendText("\n\t "+items.get(i).getItemName()+"  | Cost : {"+items.get(i).getCost() +"}  | Qty : {"+items.get(i).getNumSold()+"}");
			}
			
			area.appendText("\n\n--------------------------------------------------------------------");
			area.appendText("\n\t\tTotal-Cost: "+order.getTotalCost()+"\n");
		}
		
	}
	
	
	
}
