package clientChat.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import clientChat.ChatClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class ClientGuiController implements Initializable {
    private Color color = Color.WHITE;
    @FXML
    private TextField clientPortField;

    @FXML
    private TextField clientAddressField;

    @FXML
    private Button btConnect;

    @FXML
    private ColorPicker labelColorPicker;

    @FXML
    private ColorPicker fontColorPicker;

    @FXML
    private ColorPicker bgColorPicker;

    @FXML
    private ColorPicker textColorPicker;

    @FXML
    private TextArea clientChatWindow;

    @FXML
    private TextField clientSendMessageField;

    @FXML
    private TextField nickClientLabel;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private Label portLabel;

    @FXML
    private Label addLabel;

    @FXML
    private Label nickLabel;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private ScrollPane areaScrollPane;

    @FXML
    private AnchorPane areaAnchor;
    @FXML
    public void connectToServer() throws IOException {
    String connectionPort = clientPortField.getText();
    String connectionAddress = clientAddressField.getText();

    ChatClient.createClient(connectionAddress, connectionPort);
    clientPortField.setEditable(false);
    clientAddressField.setEditable(false);
    btConnect.setDisable(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelColorPicker.setOnAction(event -> {
            color = labelColorPicker.getValue();
            InnerShadow innerShadow = new InnerShadow();
            innerShadow.setOffsetX(0);
            innerShadow.setOffsetY(0);
            innerShadow.setRadius(2);
            innerShadow.setColor(Color.web("0x000000"));

            clientPortField.setBackground(new Background(new BackgroundFill(color, new CornerRadii(3), Insets.EMPTY)));
            clientPortField.setEffect(innerShadow);

            clientAddressField.setBackground(new Background(new BackgroundFill(color, new CornerRadii(3), Insets.EMPTY)));
            clientAddressField.setEffect(innerShadow);

            nickClientLabel.setBackground(new Background(new BackgroundFill(color, new CornerRadii(3), Insets.EMPTY)));
            nickClientLabel.setEffect(innerShadow);

            clientSendMessageField.setBackground(new Background(new BackgroundFill(color, new CornerRadii(3), Insets.EMPTY)));
            clientSendMessageField.setEffect(innerShadow);

            clientChatWindow.setEffect(innerShadow);

            areaAnchor.setStyle("-fx-base: "+ format(color) +";");
            areaScrollPane.setStyle("-fx-base: "+ format(color) +";");
        });
        fontColorPicker.setOnAction(event -> {
            nickClientLabel.setStyle("-fx-text-fill: " + format(fontColorPicker.getValue()) + ";");
            clientPortField.setStyle("-fx-text-fill: " + format(fontColorPicker.getValue()) + ";");
            clientAddressField.setStyle("-fx-text-fill: " + format(fontColorPicker.getValue()) + ";");
            clientSendMessageField.setStyle("-fx-text-fill: " + format(fontColorPicker.getValue()) + ";");
            clientChatWindow.setStyle("-fx-text-fill: " + format(fontColorPicker.getValue()) + ";");
        });
        bgColorPicker.setOnAction(event -> mainAnchor.setStyle("-fx-background-color: "+ format(bgColorPicker.getValue()) +";"));

        textColorPicker.setOnAction(event -> {
            String url = "-fx-text-fill: " + format(textColorPicker.getValue()) + ";";
            portLabel.setStyle(url);
            addLabel.setStyle(url);
            nickLabel.setStyle(url);
            label1.setStyle(url);
            label2.setStyle(url);
            label3.setStyle(url);
            label4.setStyle(url);
        });
    }
    private String format(Color c) {
        int r = (int) (255 * c.getRed());
        int g = (int) (255 * c.getGreen());
        int b = (int) (255 * c.getBlue());
        return String.format("#%02x%02x%02x", r, g, b);
    }
}
