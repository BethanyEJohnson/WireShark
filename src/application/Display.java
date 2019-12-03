package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;

public class Display extends Application {

	public static void main(String[] args) {
		launch();
		CsvParser parse = new CsvParser();
		parse.readCsv("capture.csv");
	}

	public void start(Stage primaryStage) throws Exception {
		Pane displayWindow = new Pane();
		displayWindow.setStyle("-fx-background-color: grey");

		Button analyze = new Button("Analyze Capture");

		  
		displayWindow.getChildren().addAll(analyze);
		Scene mainScene = new Scene(displayWindow, 1200, 800);

		primaryStage.setScene(mainScene);
		primaryStage.setTitle("WireShark Packet Analyzer");
		primaryStage.show();
	}
}
