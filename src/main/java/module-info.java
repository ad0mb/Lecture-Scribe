module com.example.transcribeinterface {
    requires javafx.controls;
    requires javafx.fxml;


    opens LectureScribeUI to javafx.fxml;
    exports LectureScribeUI;
}