module com.example.transcribeinterface {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens LectureScribeUI to javafx.fxml;
    exports LectureScribeUI;
}