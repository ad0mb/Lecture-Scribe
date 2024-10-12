package LectureScribeUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartGUI extends Application {

    @Override
    public void start(Stage stage) {
        Label txt = new Label("Hey");

        Button button = new Button("Test");
        button.setOnAction(event -> System.out.println("I was clicked!"));

        VBox pane = new VBox(10);
        pane.getChildren().addAll(txt, button);

        Scene scene = new Scene(pane, 300, 300);
        stage.setTitle("Lecture-Scribe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main( String[] args ) {
        launch(args);
    }
}
