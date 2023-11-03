package ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CreateBookingControllerTest extends ApplicationTest {
  private Parent root;
  private Scene scene;
  private FxRobot robot = new FxRobot();

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("CreateBooking.fxml"));
    root = fxmlLoader.load();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @Test
  public void runThroughTest() {
    robot.clickOn("#forwardBtn");
    robot.clickOn("#roomTypeComboBox").clickOn("Double");
    robot.clickOn("#roomListView").clickOn("302");
    robot.clickOn("#backBtn");

    DatePicker fromDatePicker = robot.lookup("#fromDatePicker").queryAs(DatePicker.class);
    Label roomLabel = robot.lookup("#roomLabel").queryAs(Label.class);

    assertFalse(fromDatePicker.isDisable());
    robot.clickOn("#forwardBtn");
    assertTrue(fromDatePicker.isDisable());
    assertFalse(roomLabel.isDisable());
    robot.clickOn("#forwardBtn");
    assertTrue(roomLabel.isDisable());
  }
}
