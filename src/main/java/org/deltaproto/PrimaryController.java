package org.deltaproto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.java_websocket.client.WebSocketClient;


public class PrimaryController {

    WsClient client = new WsClient(new URI("http://192.168.8.9:9000/"));
    @FXML
    private Label pedalLabel;

    @FXML Label connectedStatus;
    @FXML Button connectButton;
    @FXML
    TextField messageTB;
    @FXML
    Button sendMessage;
    @FXML
    private VBox vb;

    public PrimaryController() throws URISyntaxException {
    }


//    @FXML
//    private void switchToSecondary() throws IOException {
//        App.setRoot("secondary");
//    }

    @FXML
    private void connectWS() {
        System.out.println(12345);
//        try {
//            PrimaryController wsClient = new PrimaryController(new URI("ws://demos.kaazing.com/echo"));
//        } catch (URISyntaxException e) {
//            System.out.println("The URI is incorrect.");
//        }

    }
//     KeyCode = BACK_SLASH
    @FXML
    private void initialize() throws IOException, InterruptedException {
        pedalLabel.setText("Starting server...");

        pedalLabel.setText("Started webserver!");
        vb.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode().equals(KeyCode.BACK_SLASH)) {
                    pedalLabel.setText("Wire feeder running");
                    client.send("KBQP");
                }

            }
        });

        vb.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                pedalLabel.setText("Wire feeder dormant");
                client.send("KBQR");
            }
        });

    }

    @FXML
    private void connectToServer() throws URISyntaxException, IOException  {
        client.connect();

        client.send("Hello world!");
        if(client.isClosed() == false) {
            connectedStatus.setText("Not connected with WebSocket server.");
            System.out.println();

        } else {
            connectedStatus.setText("Connected with WebSocket server.");
            connectButton.setVisible(false);

        }

    }

    @FXML
    private void sendMessage() {
        client.send(messageTB.getText());
    }

}
