package clientChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.xml.soap.Text;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatClient {
    private static Socket newClient;
    private static DataInputStream inputData;
    private static DataOutputStream outputData;

    public static void createClient(String serverAddress, String serverPort){
        TextArea clientChatWindow = (TextArea) Main.getMainScene().lookup("#clientChatWindow");
        TextField messageSendInput = (TextField) Main.getMainScene().lookup("#clientSendMessageField");
        TextField clientNickname = (TextField) Main.getMainScene().lookup("#nickClientLabel");
        Button sendMessage = (Button) Main.getMainScene().lookup("#btClientSendMessage");
        clientChatWindow.setEditable(false);
        clientNickname.setEditable(false);
        new Thread(() -> {
            try {
                String recievedMessage = "";
                newClient = new Socket(serverAddress, Integer.parseInt(serverPort));
                inputData = new DataInputStream(newClient.getInputStream());
                outputData = new DataOutputStream(newClient.getOutputStream());

                while(!recievedMessage.equals("logout")){
                    recievedMessage = inputData.readUTF();
                    clientChatWindow.appendText(recievedMessage + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        sendMessage.setOnAction(event -> {
            try {
                String messageToSend;
                String clientNick = clientNickname.getText();
                if(clientNick.equals("")){
                    clientNick = "Guest";
                }
                messageToSend = messageSendInput.getText();
                clientChatWindow.appendText(clientNick + ": " + messageToSend + "\n");
                outputData.writeUTF(clientNick + ": " + messageToSend);
                messageSendInput.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
