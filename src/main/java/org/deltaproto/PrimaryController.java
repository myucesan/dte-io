package org.deltaproto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class PrimaryController extends Thread{

    @FXML
    private Label pedalLabel;
    @FXML
    private VBox vb;
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
// https://stackoverflow.com/questions/54088768/how-to-set-text-to-a-label-on-keyboard-key-press-in-javafx-application
    //https://stackoverflow.com/questions/32806068/how-to-change-fxml-lable-text-by-id
    //https://www.tutorialspoint.com/javafx/javafx_event_handling.htm

    // KeyCode = BACK_SLASH
    @FXML
    private void initialize() throws IOException, InterruptedException {
        pedalLabel.setText("Starting server...");

        pedalLabel.setText("Started webserver!");
        vb.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode().equals(KeyCode.BACK_SLASH)) {
                    pedalLabel.setText("Wire feeder running");
                }

            }
        });

        vb.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                pedalLabel.setText("Wire feeder dormant");
            }
        });

        FeederServer s = new FeederServer(9000);
        Thread t = new Thread(s);
    }




}
