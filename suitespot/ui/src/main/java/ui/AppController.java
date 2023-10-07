package ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class AppController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    public void openCustomerView() {
        System.out.println("customer");
        openFXML("Customer");
    }

    @FXML
    public void openRoomView() {
        System.out.println("room");
        openFXML("Room");
    }

    public void openFXML(String fileName) {
        try {
            AnchorPane details = FXMLLoader.load(getClass().getResource(fileName + ".fxml"));
            rootPane.getChildren().setAll(details);

        } catch (IOException e) {
            System.out.println("Failed to open page");
            e.printStackTrace();
        }
    }

    public static void GoToMainPage() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(AppController.class.getResource("App.fxml").openStream());
            AppController thisController = (AppController) fxmlLoader.getController();
            thisController.openFXML("Room");
        } catch (IOException e) {
            System.out.println("Failed to go back to page");
            e.printStackTrace();
        }
    }
}
