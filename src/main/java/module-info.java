module com.example.renderingandalgorithmvisualizer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.renderingandalgorithmvisualizer to javafx.fxml;
    exports com.example.renderingandalgorithmvisualizer;
    exports com.example.renderingandalgorithmvisualizer.MenuButtonControllers;
    opens com.example.renderingandalgorithmvisualizer.MenuButtonControllers to javafx.fxml;
}