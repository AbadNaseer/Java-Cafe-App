package cafe.view;

import java.util.List;

import cafe.model.Cafe;
import cafe.model.Order;
import cafe.model.OrderItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class PlacedOrdersWindow extends AnchorPane{

	public PlacedOrdersWindow(Cafe cafeObj) {
		Label ordersLbl = new Label("Placed Orders");
        ordersLbl.setLayoutX(192.0);
        ordersLbl.setLayoutY(41.0);
        ordersLbl.setPrefWidth(234.0);
        ordersLbl.setPrefHeight(35.0);
        ordersLbl.setTextFill(javafx.scene.paint.Color.valueOf("#7c7171"));
        ordersLbl.setTextAlignment(TextAlignment.CENTER);
        ordersLbl.setFont(new Font("Berlin Sans FB", 31.0));

        Button returnBtn = new Button("Cancel");
        returnBtn.setLayoutX(224.0);
        returnBtn.setLayoutY(485.0);
        returnBtn.setPrefWidth(166.0);
        returnBtn.setPrefHeight(37.0);
        returnBtn.setStyle("-fx-background-color: red;");
        returnBtn.setTextFill(javafx.scene.paint.Color.valueOf("#ebe8e8"));
        returnBtn.setFont(new Font("System Bold", 16.0));

        Label nameLbl = new Label();
        nameLbl.setLayoutX(292.0);
        nameLbl.setLayoutY(185.0);
        nameLbl.setPrefWidth(148.0);
        nameLbl.setPrefHeight(27.0);
        nameLbl.setFont(new Font(18.0));

        Label Lbl = new Label();
        Lbl.setLayoutX(292.0);
        Lbl.setLayoutY(230.0);
        Lbl.setPrefWidth(148.0);
        Lbl.setPrefHeight(27.0);
        Lbl.setFont(new Font(18.0));

        TextArea OrderTextArea = new TextArea();
        OrderTextArea.setLayoutX(79.0);
        OrderTextArea.setLayoutY(112.0);
        OrderTextArea.setPrefWidth(479.0);
        OrderTextArea.setPrefHeight(330.0);
        OrderTextArea.setEditable(false);

        getChildren().addAll(ordersLbl, returnBtn, nameLbl, Lbl, OrderTextArea);
    
        displayPlacedOrders(OrderTextArea,cafeObj);
        
        returnBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	Stage stage = (Stage) returnBtn.getScene().getWindow();
                stage.close();            	
            }
        });
        
	}
	
	public void displayPlacedOrders(TextArea area, Cafe cafeObj) {
		
		List<Order> orders = cafeObj.getOrderList();
		
		for(int i=0;i<orders.size();i++) {
			area.appendText("\n\t\t ------  Order Id {"+orders.get(i).getOrderId()+"}  |  Total Cost {"+orders.get(i).getTotalCost()+"} ---------\n");
			
			List<OrderItem> items = orders.get(i).getOrderedItems();
			
			for(int j=0;j<items.size();j++) {
				area.appendText("\t\t "+ (j+1) +".  "+items.get(j).getItemName()+ "  |  Cost : {"+items.get(j).getCost()+"} | Qty : {"+items.get(j).getNumSold()+"} \n");
			}
			
		}
		
	}
	
}
