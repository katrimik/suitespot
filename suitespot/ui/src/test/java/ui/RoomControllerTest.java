package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import core.model.Room;
import core.model.RoomType;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RoomControllerTest extends ApplicationTest {
  private Parent root;
  private Scene scene;
  private FxRobot robot = new FxRobot();

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Room.fxml"));
    root = fxmlLoader.load();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public Parent getRootNode() {
    return root;
  }

  private TextField getTextField(String fxid) {
    return robot.lookup(fxid).queryAs(TextField.class);
  }

  private Spinner<Integer> getSpinner(String fxid) {
    @SuppressWarnings("unchecked")
    Spinner<Integer> spinner = robot.lookup(fxid).queryAs(Spinner.class);
    return spinner;
  }

  private ComboBox<RoomType> getComboBox(String fxid) {
    @SuppressWarnings("unchecked")
    ComboBox<RoomType> spinner = robot.lookup(fxid).queryAs(ComboBox.class);
    return spinner;
  }

  private Label getLabel(String fxid) {
    return robot.lookup(fxid).queryAs(Label.class);
  }

  @Test
  public void testCreateRoomType() {
    getTextField("#roomTypeNameTxt").setText("hei");
    robot.clickOn("#roomTypeCreateBtn");
    assertNull(getTextField("#roomTypeNameTxt").getText());
  }

  @Test
  public void testCreateRoom() {
    getSpinner("#roomNumberTxt").getValueFactory().setValue(211);
    robot.clickOn("#roomCreateBtn");
    assertNotEquals(211, getSpinner("#roomNumberTxt").getValue());
  }

  @Test
  public void testSaveDeleteRoomType() {
    String name = "test-room-type-unique";
    robot.clickOn("#roomTypeCreateBtn");
    getTextField("#roomTypeNameTxt").setText(name);
    getSpinner("#roomTypePriceTxt").getValueFactory().setValue(1111);
    robot.clickOn("#roomTypeSaveBtn");

    robot.clickOn("#roomTypeListView").clickOn(name);
    assertTrue(getTextField("#roomTypeNameTxt").getText().equals(name));
    assertEquals(1111, getSpinner("#roomTypePriceTxt").getValue());

    robot.clickOn("#roomTypeDeleteBtn");

    @SuppressWarnings("unchecked")
    List<RoomType> allTypes = (List<RoomType>) robot.lookup("#roomTypeListView")
        .queryAs(ListView.class).getItems();

    assertEquals(0, allTypes.stream().filter(t -> t.getName().equals(name)).count());
  }

  @Test
  public void testSaveDeleteRoom() {
    robot.clickOn("#roomCreateBtn");
    getSpinner("#roomNumberTxt").getValueFactory().setValue(512);

    Platform.runLater(() -> {
      getComboBox("#roomTypeComboBox").getSelectionModel().selectFirst();
    });

    robot.clickOn("#roomSaveBtn");

    robot.clickOn("#roomListView").clickOn("512");
    assertEquals(512, getSpinner("#roomNumberTxt").getValue());
    assertNotNull(getComboBox("#roomTypeComboBox").getSelectionModel().getSelectedItem());

    robot.clickOn("#roomDeleteBtn");

    @SuppressWarnings("unchecked")
    List<Room> allRooms = (List<Room>) robot.lookup("#roomListView")
        .queryAs(ListView.class).getItems();

    assertEquals(0, allRooms.stream().filter(t -> t.getRoomNumber() == 512).count());
  }

  @Test
  public void testSaveInvalidRoomType() {
    robot.clickOn("#roomTypeSaveBtn");
    assertFalse(getLabel("#roomTypeErrorLbl").getText().isEmpty());
  }

  @Test
  public void testSaveInvalidRoom() {
    robot.clickOn("#roomSaveBtn");
    assertFalse(getLabel("#roomErrorLbl").getText().isEmpty());
  }
}
