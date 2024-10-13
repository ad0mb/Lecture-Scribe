package LectureScribeUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;


public class StartGUI extends Application {

    @Override
    public void start(Stage stage) {
        //Label txt = new Label("Hey");
        BorderPane parentPane = new BorderPane();

        new Panels(parentPane).panelsMain();




        Scene scene = new Scene(parentPane, 1000, 600);
        stage.setTitle("Lecture-Scribe");
        stage.setScene(scene);
        stage.show();
    }

    private String toRgbString(Color color) {
        return String.format("rgb(%d,%d,%d)",
                (int)(color.getRed() * 255),
                (int)(color.getGreen() * 255),
                (int)(color.getBlue() * 255));
    }

    public static void main( String[] args ) {
        launch(args);
    }
}
