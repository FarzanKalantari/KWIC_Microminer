

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.IOException;
import java.util.Optional;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * KWICdriver: Main driver program to parse the file, process, sort and show the
 * output
 *
 */
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.geometry.Insets;

import javafx.geometry.Pos;

import java.io.IOException;
import java.util.Optional;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * KWICdriver: Main driver program to parse the file, process, sort and show the
 * output
 *
 */
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import com.sql.db.*;

public class KWICdriver extends Application{

	/**
	 * Main program

	 */
	public static TextArea textArea;
	public static Label headerLabel;
	Button submitButton;
	Button resetButton;
	Button exitButton;
	Button addNoiseWordButton;
	Button removeNoiseWordButton;


	public static void main(String args[]) throws IOException {
		launch(args);

	}

	public void start(Stage stage) throws Exception {

		final Output output = new Output();

		VBox vBox = new VBox();
		vBox.setPadding(new Insets(15));
		vBox.setSpacing(20);
		vBox.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

		HBox buttonBar = new HBox();
		buttonBar.setPadding(new Insets(15));
		buttonBar.setSpacing(20);
		buttonBar.setCenterShape(true);
		buttonBar.setAlignment(Pos.BOTTOM_CENTER);
		buttonBar.setStyle("-fx-highlight-fill: lightgray; -fx-font-weight: bold; -fx-font-size: 18px;");

		Scene scene = new Scene(vBox, 1080, 920);
		scene.setFill(Color.BLACK);

		textArea = new TextArea();
		textArea.setMinHeight(scene.getHeight()*.9);
		textArea.setStyle("-fx-highlight-fill: lightgray; -fx-font-weight: bold; -fx-highlight-text-fill: firebrick; -fx-font-size: 16px; -fx-control-inner-background: mintcream; ");
		textArea.setBackground(new Background(new BackgroundFill(Color.NAVY, CornerRadii.EMPTY, Insets.EMPTY)));

		headerLabel = new Label("Enter message: ");
		headerLabel.setStyle("-fx-highlight-fill: lightgray; -fx-font-weight: bold; -fx-highlight-text-fill: firebrick; -fx-font-size: 18px;");

		submitButton = new Button("Submit");
		exitButton = new Button("Exit");
		addNoiseWordButton = new Button("Add Noise Word");
		removeNoiseWordButton = new Button("Remove Noise Word");
		resetButton = new Button("Reset");

		buttonBar.getChildren().addAll(submitButton, addNoiseWordButton, removeNoiseWordButton, resetButton, exitButton);
		vBox.getChildren().addAll(headerLabel, textArea, buttonBar);


		submitButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e)
			{
				long startTime = System.currentTimeMillis();

				// Initialize Input get text from gui
				Input input = new Input();
				MinerSearch miner = new MinerSearch();
				StorageI lineStorage = new LineStorage();
				try {
					input.readAndStore("C:\\Users\\Zach\\Documents\\NetBeansProjects\\KWIC_Microminer\\src\\java\\test.txt", lineStorage);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//input.getUserInput(lineStorage);

				textArea.setText("");
				// Initialize Circular Shift based on the line storage and process shift

				StorageI circularShift = new CircularShift();
				( (CircularShift) circularShift).setup(lineStorage);

				// Initialize Alphabetizer based on the Circular Shift and sort
				StorageI alphabetizer = new Alphabetizer();

				((Alphabetizer) alphabetizer).alpha(circularShift);

				SQLCreate.connect();
				SQLCreate.removeAllRecords();

				for(int i = 0; i < alphabetizer.getLineCount(); i++) {
					SQLCreate.insertRecords(i, alphabetizer.getLine(i), circularShift.getLine(i), lineStorage.getLine(i));
				}

				miner.getOutputFromKeywords("University");

				output.print(alphabetizer);

				submitButton.setDisable(true);
				resetButton.setVisible(true);
				long endTime = System.currentTimeMillis();

				System.out.println("\ntime to run prog: " + (endTime-startTime) + " milliseconds");
			}
		});

		exitButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e)
			{
				System.exit(0);
			}
		});

		addNoiseWordButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e)
			{
				TextInputDialog dialog = new TextInputDialog();
				dialog.setTitle("Add a New Noise Word");
				dialog.setHeaderText(null);
				dialog.setGraphic(null);
				dialog.setContentText("Please enter a new noise word:");

				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
					// System.out.println("Your name: " + result.get().toString() );
					output.addNoiseWord(result.get());
				}
			}
		});

		removeNoiseWordButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e)
			{
				ChoiceDialog<String> dialog = new ChoiceDialog<>(output.getNoiseWordList().get(0), output.getNoiseWordList());
				dialog.setTitle("Remove a Noise Word");
				dialog.setHeaderText(null);
				dialog.setGraphic(null);
				dialog.setContentText("Choose a noise word to remove:");

				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
					// System.out.println("Your choice: " + result.get());
					output.removeNoiseWord((String) result.get());
				}
			}
		});

		resetButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e)
			{
				headerLabel.setText("Enter message: ");
				textArea.setText("");
				submitButton.setDisable(false);
			}
		});

		stage.setMaximized(true);
		stage.setTitle("Key Word in Context");
		stage.setScene(scene);
		stage.show();
	}

	public TextArea getTextArea(){
		return textArea;
	}

	public Label getHeaderLaber(){
		return headerLabel;
	}
}
