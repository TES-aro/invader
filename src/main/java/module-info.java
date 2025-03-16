module com.invader.naytto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.invader.naytto to javafx.fxml;
    exports com.invader.naytto;
}