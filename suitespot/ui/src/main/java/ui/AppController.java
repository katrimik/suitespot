package ui;

import javafx.fxml.FXML;

public class AppController {
  @FXML
  public void openCustomerView() {
    App.SetView("Customer");
  }

  @FXML
  public void openRoomView() {
    App.SetView("Room");
  }
}
