module com.viikko7.naytto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.viikko7.naytto to javafx.fxml;
    exports com.viikko7.naytto;
}