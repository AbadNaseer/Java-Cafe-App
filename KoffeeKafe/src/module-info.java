/**
 * 
 */
/**
 * @author Dell
 *
 */
module KoffeeKafe {
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.base;
	opens cafe to javafx.graphics, javafx.fxml;
}