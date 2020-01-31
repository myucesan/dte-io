module org.deltaproto {
    requires javafx.controls;
    requires javafx.fxml;
    requires Java.WebSocket;

    opens org.deltaproto to javafx.fxml;
    exports org.deltaproto;
}