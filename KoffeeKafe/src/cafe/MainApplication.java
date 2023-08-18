package cafe;

import cafe.model.Cafe;
import cafe.view.OrderWindow;
import cafe.view.StartupWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Cafe cafe = new Cafe("cafeMenu.txt");
		StartupWindow root = new StartupWindow(cafe);
		//OrderWindow root = new OrderWindow();
		Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
