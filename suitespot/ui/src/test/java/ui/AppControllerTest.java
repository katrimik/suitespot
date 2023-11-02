package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import core.manager.interfaces.ICustomerManager;
import core.manager.Manager;
import core.manager.RoomManager;
import core.model.Booking;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ui.utils.ListViewItem;

public class AppControllerTest extends ApplicationTest {
  private Parent root;
  private Scene scene;
  private FxRobot robot = new FxRobot();

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("App.fxml"));
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

  private List<Booking> getListItems(String fxid) {
    @SuppressWarnings("unchecked")
    List<ListViewItem<Booking>> listViewItems = (List<ListViewItem<Booking>>) robot
        .lookup("#bookingListView")
        .queryAs(ListView.class).getItems();

    return listViewItems.stream().map(l -> l.getValue()).toList();
  }

  @Test
  public void selectRoomRadioBtn() {
    robot.clickOn("From date");
    FxAssert.verifyThat("#dateRadioBtn", NodeMatchers.isFocused());

  }

  @Test
  public void clickOnListViewCustomer() {
    robot.clickOn("Customer");
    String name = "John Smith";
    robot.clickOn("#bookingListView").clickOn(name);

    @SuppressWarnings("unchecked")
    ListViewItem<Booking> selectedListViewItem = (ListViewItem<Booking>) robot.lookup("#bookingListView")
        .queryAs(ListView.class).getSelectionModel()
        .getSelectedItem();

    ICustomerManager customerManager = Manager.getCustomerManager();
    String selectedCustomerFullName = customerManager
        .listCustomers()
        .stream()
        .filter(c -> c.getId()
            .equals(selectedListViewItem.getValue().getCustomerId()))
        .findFirst()
        .orElse(null).getFullName();

    String customerFullName = robot.lookup("#customerDataLbl").queryAs(Label.class).getText();

    assertEquals(customerFullName, selectedCustomerFullName);
  }

  @Test
  public void clickOnListViewRoom() {
    robot.clickOn("Room");
    String roomName = "102 (Single)";
    robot.clickOn("#bookingListView").clickOn(roomName);

    @SuppressWarnings("unchecked")
    ListViewItem<Booking> selectedListViewItem = (ListViewItem<Booking>) robot.lookup("#bookingListView")
        .queryAs(ListView.class).getSelectionModel()
        .getSelectedItem();

    RoomManager roomManager = (RoomManager) Manager.getRoomManager();
    Integer selectedRoomNumber = roomManager
        .listRooms()
        .stream()
        .filter(r -> r.getId().equals(selectedListViewItem.getValue().getRoomId()))
        .findFirst()
        .orElse(null).getRoomNumber();

    int roomNumber = Integer.valueOf(robot.lookup("#roomDataLbl").queryAs(Label.class).getText());
    System.out.println(roomNumber + " should be " + selectedRoomNumber);
    assertEquals(roomNumber, selectedRoomNumber);

  }

  @Test
  public void testSearchField() {
    // Click on the search field and enter a search query
    robot.clickOn("Customer");
    clickOn("#searchField").write("John");

    // // Verify that the search field has the expected text
    assertEquals((this.getTextField("#searchField")).getText(), "John");

    // Ensure that the search results contain the expected items
    List<Booking> listViewItemsSearched = this.getListItems("#bookingListView");

    assertTrue(listViewItemsSearched.size() == 2);

  }
}