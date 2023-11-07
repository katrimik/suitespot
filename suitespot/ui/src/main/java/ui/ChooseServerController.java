package ui;

import java.net.URISyntaxException;
import java.net.URL;

import core.apiManager.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ui.utils.ServerUtils;

public class ChooseServerController {
  @FXML
  TextField linkTextField;

  @FXML
  Label statusErrorLbl;

  @FXML
  public void connectToServer() {
    statusErrorLbl.setText("");
    String serverUrl = linkTextField.getText();

    try {
      new URL(serverUrl);
    } catch (Exception e) {
      statusErrorLbl.setText("Invalid format for URL");
      return;
    }

    try {
      Manager.setApiUrl(serverUrl);
    } catch (URISyntaxException | IllegalArgumentException e) {
      statusErrorLbl.setText("Invalid format for URL");
      return;
    }

    boolean isActive = ServerUtils.isServerActive(serverUrl);
    if (!isActive) {
      statusErrorLbl.setText("URL format correct, but server isn't running");
      return;
    }

    App.setView("App");
  }
}
