package LectureScribeUI;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.awt.*;

public class Panels {

    BorderPane parentPane;

    public Panels(BorderPane parentPane) {
        this.parentPane = parentPane;
    }

    public void panelsMain() {
        topPanel();
        sidePanel();
        anchorPanel();
    }

    public void topPanel() {
        HBox topbar = new HBox();
        topbar.setSpacing(20);
        topbar.setAlignment(Pos.BASELINE_LEFT);
        topbar.setStyle("-fx-background-color: " + "rgb(0,26,51)" + ";");
        topbar.setPrefSize(800, 85);

        Button startButton = new Button("Start"); startButton.setOnAction(e -> startPopUp());
        Button playButton = new Button("Play");
        Button pauseButton = new Button("Stop");
        Button settingsButton = new Button();

        startButton.setStyle("-fx-background-color: #0078D4; " + "-fx-text-fill: white; " + "-fx-pref-width: 100; " + "-fx-pref-height: 100; " + "-fx-background-radius: 50; " + "-fx-border-radius: 50; " + "-fx-border-color: black; " + "-fx-border-width: 0;");
        playButton.setStyle("-fx-background-color: #0078D4; " + "-fx-text-fill: white; " + "-fx-pref-width: 60; " + "-fx-pref-height: 60; " + "-fx-background-radius: 50; " + "-fx-border-radius: 50; " + "-fx-border-color: black; " + "-fx-border-width: 0;");
        pauseButton.setStyle("-fx-background-color: #0078D4; " + "-fx-text-fill: white; " + "-fx-pref-width: 60; " + "-fx-pref-height: 60; " + "-fx-background-radius: 50; " + "-fx-border-radius: 50; " + "-fx-border-color: black; " + "-fx-border-width: 0;");


        HBox.setMargin(startButton, new Insets(30,10, 10, 20));
        HBox.setMargin(playButton, new Insets(30,5, 10, 20));
        HBox.setMargin(pauseButton, new Insets(30,5, 10, 20));
        HBox.setMargin(settingsButton, new Insets(30, 10, 10, 300));

        topbar.getChildren().addAll(startButton, playButton, pauseButton, settingsButton);
        parentPane.setTop(topbar);
    }

    public void sidePanel() {
        HBox sidebar = new HBox();
        sidebar.setStyle("-fx-background-color: " + "rgb(0,53,102)" + ";");
        sidebar.setPrefSize(250, 400);

        TextField liveTranscriptBox = new TextField();
        liveTranscriptBox.setPrefSize(230, 500);
        liveTranscriptBox.setStyle("-fx-background-color: rgb(0, 53, 102);" + "-fx-text-fill: black;");
        HBox.setMargin(liveTranscriptBox, new Insets(10, 10, 10, 10)); // Top, Right, Bottom, Left



        sidebar.getChildren().add(liveTranscriptBox);
        parentPane.setRight(sidebar);
    }

    public void anchorPanel() {
        HBox hBox = new HBox();
        Pane anchorbar = new Pane();
        anchorbar.setStyle("-fx-background-color: " + "rgb(0,0,51)" + ";");
        anchorbar.setPrefSize(200, 100);
        BorderPane.setAlignment(anchorbar, javafx.geometry.Pos.BOTTOM_RIGHT);




        parentPane.setCenter(anchorbar);
    }




    public void startPopUp() {
        Stage popupStage = new Stage();
        popupStage.setTitle("Select an Option");

        popupStage.initModality(Modality.APPLICATION_MODAL);

        // Create a GridPane for layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Live Listen", "Screen Recording", "Uplaod File");

        Button applyButton = new Button("Apply");
        applyButton.setOnAction(e -> {
            popupStage.close();
        });

        gridPane.add(comboBox, 0, 0);
        gridPane.add(applyButton, 0, 1);

        Scene popupScene = new Scene(gridPane, 200, 100);
        popupStage.setScene(popupScene);


        popupStage.showAndWait();
    }

    private static String toRgbString(Color color) {
        return String.format("rgb(%d,%d,%d)",
                (int)(color.getRed() * 255),
                (int)(color.getGreen() * 255),
                (int)(color.getBlue() * 255));
    }
}
