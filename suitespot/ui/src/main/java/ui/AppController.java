package ui;

import javafx.fxml.FXML;

public class AppController {
  @FXML
  public void openCustomerView() {
    App.setView("Customer");
  }

  @FXML
  public void openRoomView() {
    App.setView("Room");
  }
}
