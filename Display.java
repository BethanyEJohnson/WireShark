package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class Display extends Application {
	private TableView table = new TableView();

	public static void main(String[] args) {
		launch();
	}

	public void start(Stage primaryStage) throws Exception {
		Pane displayWindow = new Pane();
		Scene mainScene = new Scene(displayWindow, 1200, 800);
		CsvParser parse = new CsvParser();
		ArrayList<Packet> packet = parse.readCsv("capture.csv");
		CaptureAnalyzer analyzer = new CaptureAnalyzer(packet);

		// Average packets per second
		final Label ppsLabel = new Label("Average Packets Per Second\t" + analyzer.pps());
		ppsLabel.setLayoutX(50);
		ppsLabel.setLayoutY(50);
		ppsLabel.setTextFill(Color.WHITE);
		ppsLabel.setStyle("-fx-font: 24 arial;");

		// Average size of packet
		final Label sizeLabel = new Label("Average Packets Size\t\t" + analyzer.avgSize());
		sizeLabel.setLayoutX(50);
		sizeLabel.setLayoutY(150);
		sizeLabel.setTextFill(Color.WHITE);
		sizeLabel.setStyle("-fx-font: 24 arial;");

		// Button that opens Protocol Percentage Chart
		Button pieChart = new Button("Protocol Percentage");
		pieChart.setLayoutX(800);
		pieChart.setLayoutY(150);
		pieChart.setScaleX(1.5);
		pieChart.setScaleY(1.5);
		pieChart.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				double tcp = analyzer.percentProtocols("TCP");
				double udp = analyzer.percentProtocols("UDP");
				double other = 100 - tcp - udp;

				pieChart(tcp, udp, other);
			}
		});

		// Button that opens Packets Per Second Graph
		Button pps = new Button("Packets Per Second");
		pps.setLayoutX(800);
		pps.setLayoutY(50);
		pps.setScaleX(1.5);
		pps.setScaleY(1.5);
		pps.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				ppsGraph(packet);
			}
		});

		// Creates Columns For table
		TableColumn number = new TableColumn("number");
		TableColumn time = new TableColumn("time");
		TableColumn source = new TableColumn("source");
		TableColumn destination = new TableColumn("destination");
		TableColumn protocol = new TableColumn("protocol");
		TableColumn length = new TableColumn("length");
		TableColumn info = new TableColumn("info");

		// Sets Columns to variables
		number.setCellValueFactory(new PropertyValueFactory<>("number"));
		time.setCellValueFactory(new PropertyValueFactory<>("time"));
		source.setCellValueFactory(new PropertyValueFactory<>("source"));
		destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
		protocol.setCellValueFactory(new PropertyValueFactory<>("protocol"));
		length.setCellValueFactory(new PropertyValueFactory<>("length"));
		info.setCellValueFactory(new PropertyValueFactory<>("info"));

		// Fills Columns with packet data
		table.getItems().addAll(packet);
		table.getColumns().addAll(number, time, source, destination, protocol, length, info);

		final VBox vbox = new VBox();
		vbox.setPrefWidth(1200);
		vbox.setSpacing(300);
		vbox.setPadding(new Insets(300, 20, 20, 20));
		vbox.getChildren().addAll(table);

		displayWindow.getChildren().addAll(vbox);

		displayWindow.setStyle("-fx-background-color: grey");
		displayWindow.getChildren().addAll(pieChart);
		displayWindow.getChildren().addAll(pps);
		displayWindow.getChildren().addAll(ppsLabel);
		displayWindow.getChildren().addAll(sizeLabel);

		primaryStage.setScene(mainScene);
		primaryStage.setTitle("WireShark Packet Analyzer");
		primaryStage.show();
	}

	// Creates percentage pie chart
	public void pieChart(double tcp, double udp, double other) {

		Stage stage = new Stage();
		Scene scene = new Scene(new Group());
		stage.setTitle("Packet Percentage");
		stage.setWidth(500);
		stage.setHeight(500);

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(

				new PieChart.Data("TCP", tcp), new PieChart.Data("UDP", udp), new PieChart.Data("other", other));
		final PieChart chart = new PieChart(pieChartData);
		chart.setTitle("Protocols");

		final Label caption = new Label("");
		caption.setTextFill(Color.BLACK);
		caption.setStyle("-fx-font: 24 arial;");

		for (final PieChart.Data data : chart.getData()) {
			data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					caption.setTranslateX(e.getSceneX());
					caption.setTranslateY(e.getSceneY());
					caption.setText(String.valueOf(data.getPieValue()) + "%");
				}
			});
		}
		((Group) scene.getRoot()).getChildren().add(chart);
		((Group) scene.getRoot()).getChildren().add(caption);
		stage.setScene(scene);
		stage.show();

	}

	// Creates the packets per second line graph
	public void ppsGraph(ArrayList<Packet> packet) {
		Stage stage = new Stage();
		stage.setTitle("Packets Per Second");

		// defining the axes
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Seconds");
		yAxis.setLabel("Packets");

		// creating the chart
		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		lineChart.setTitle("Packets Per Seconds");

		// defining a series
		XYChart.Series series = new XYChart.Series();

		// populating the series with data
		for (int i = 0; i < packet.size(); i++) {
			series.getData().add(new XYChart.Data(packet.get(i).getTime(), i));
		}

		Scene scene = new Scene(lineChart, 800, 600);
		lineChart.getData().add(series);

		stage.setScene(scene);
		stage.show();
	}

}
