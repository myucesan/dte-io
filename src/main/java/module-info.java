module org.deltaproto {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.deltaproto to javafx.fxml;
    exports org.deltaproto;
}