package manhattanDistance; 
import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class UI extends Application {
    @Override
    public void start(Stage primaryStage) {
        int tFieldWidth = 50; // preferred width to set textfields to

        primaryStage.setTitle("Manhattan Distance Calculator");
        FlowPane mainPane = new FlowPane(Orientation.VERTICAL); // will place all visual components into this pane
        mainPane.setVgap(10); // vertical space between visual components

        Text instructionText = new Text("When entering (X, Y), enter each coordinate in its associated text field");

        TextField aXInput = new TextField("X"); // 2 textfields to take x and y coordinate input for point A
        TextField aYInput = new TextField("Y");
        FlowPane aInputs = new FlowPane(Orientation.HORIZONTAL); // place textfields onto a horizontal pane
        aInputs.getChildren().addAll(aXInput, aYInput);

        TextField bXInput = new TextField("X"); // same as above code for point B
        TextField bYInput = new TextField("Y");
        FlowPane bInputs = new FlowPane(Orientation.HORIZONTAL);
        bInputs.getChildren().addAll(bXInput, bYInput);

        aXInput.setPrefWidth(tFieldWidth); // set widths of textfields
        aYInput.setPrefWidth(tFieldWidth);
        bXInput.setPrefWidth(tFieldWidth);
        bYInput.setPrefWidth(tFieldWidth);

        Label pointALabel = new Label("Enter in coordinates for point A", aInputs); // place labels next to
        Label pointBLabel = new Label("Enter in coordinates for point B", bInputs); // point A and B textfields

        pointALabel.setContentDisplay(ContentDisplay.RIGHT); // textfields will be to the right of the labels
        pointBLabel.setContentDisplay(ContentDisplay.RIGHT);

        Button calculateManDistButton = new Button(); // button to calculate distance when pressed
        calculateManDistButton.setPrefSize(100, 20);
        calculateManDistButton.setStyle("-fx-background-color: #C0B9DD; ");
        calculateManDistButton.setText("Calculate");

        Label buttonLabel = new Label("Calculate Manhattan Distance", calculateManDistButton); // label above button
        buttonLabel.setContentDisplay(ContentDisplay.BOTTOM);

        Text outputText = new Text(); // text to show calculation result
        outputText.setVisible(false); // invisible by default

        UnaryOperator<Change> integerFilter = change -> { // textfilter to only allow +/- and integer user input
            String newText = change.getControlNewText();
            if (newText.matches("-?([0-9]*)?")) {
                return change;
            }
            return null;
        };

        // For each setOnMouseclicked, when clicking on a textfield, the X/Y chararcter
        // disappears and user can input whatever integerFilter allows
        aXInput.setOnMouseClicked(e -> {
            if (aXInput.getText().matches("X")) {
                aXInput.clear();
            }
            aXInput.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
        });
        aYInput.setOnMouseClicked(e -> {
            if (aYInput.getText().matches("Y")) {
                aYInput.clear();
            }
            aYInput.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
        });
        bXInput.setOnMouseClicked(e -> {
            if (bXInput.getText().matches("X")) {
                bXInput.clear();
            }
            bXInput.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
        });
        bYInput.setOnMouseClicked(e -> {
            if (bYInput.getText().matches("Y")) {
                bYInput.clear();
            }
            bYInput.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
        });

        // Calculate button reveals a text that either shows calculated distance or an
        // error message
        calculateManDistButton.setOnAction(e -> {
            String outputString;

            // check if the coordinate textfields contain integer values
            if (aXInput.getText().matches(".*\\d.*") && aYInput.getText().matches(".*\\d.*")
                    && bXInput.getText().matches(".*\\d.*") && bYInput.getText().matches(".*\\d.*")) {

                // sends coordinate values of A and B to Calculator class
                Calculator.setPointA(Integer.parseInt(aXInput.getText()), Integer.parseInt(aYInput.getText()));
                Calculator.setPointB(Integer.parseInt(bXInput.getText()), Integer.parseInt(bYInput.getText()));
                outputString = String.format("The Manhattan Distance of %nA = %s,%nB = %s,%nis:%n%d", Calculator.getPointA(),
                        Calculator.getPointB(), Calculator.manhattanDistance());
            } else {
                outputString = "Input format was invalid, please enter an integer value in each textfield";
            }
            outputText.setVisible(true);
            outputText.setText(outputString);
        });

        mainPane.getChildren().addAll(instructionText, pointALabel, pointBLabel, buttonLabel, outputText); // add all elements to mainPane
        mainPane.setAlignment(Pos.CENTER_RIGHT);

        primaryStage.setScene(new Scene(mainPane, 580, 250));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
