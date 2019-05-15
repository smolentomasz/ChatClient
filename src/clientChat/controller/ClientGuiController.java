package clientChat.controller;

import clientChat.ChatClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ClientGuiController {
    @FXML
    private TextField clientPortField;

    @FXML
    private TextField clientAddressField;

    @FXML
    private Button btConnect;
    @FXML
    public void connectToServer(){
    String connectionPort = clientPortField.getText();
    String connectionAddress = clientAddressField.getText();

    ChatClient.createClient(connectionAddress, connectionPort);
    clientPortField.setEditable(false);
    clientAddressField.setEditable(false);
    btConnect.setDisable(true);
    }
}
