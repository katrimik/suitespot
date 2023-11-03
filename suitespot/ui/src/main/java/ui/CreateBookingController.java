package ui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import core.apiManager.Manager;
import core.manager.interfaces.IBookingManager;
import core.manager.interfaces.ICustomerManager;
import core.manager.interfaces.IRoomManager;
import core.manager.interfaces.IRoomTypeManager;
import core.model.Booking;
import core.model.Customer;
import core.model.Room;
import core.model.RoomType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class CreateBookingController implements Initializable {
  @FXML
  DatePicker fromDatePicker, toDatePicker;

  @FXML
  ComboBox<RoomType> roomTypeComboBox;

  @FXML
  ListView<Room> roomListView;

  @FXML
  ListView<Customer> customerListView;

  @FXML
  Label dateLabel, roomLabel, customerLabel, dateFromLabel, dateToLabel, errorLabel;

  @FXML
  Button backBtn, forwardBtn;

  private final ICustomerManager customerManager = Manager.getCustomerManager();
  private final IRoomManager roomManager = Manager.getRoomManager();
  private final IRoomTypeManager roomTypeManager = Manager.getRoomTypeManager();
  private final IBookingManager bookingManager = Manager.getBookingManager();

  private List<Room> allRooms = new ArrayList<Room>();
  private int currentSection = 0;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    setInitialState();
  }

  private void setInitialState() {
    loadCustomers();
    loadRoomTypes();
    loadRooms();

    roomTypeComboBox.getSelectionModel().selectFirst();
    roomTypeChanged();
    forwardBtn.setDisable(false);

    currentSection = 0;
    setCorrectSection();

    fromDatePicker.setValue(LocalDate.now());
    toDatePicker.setValue(LocalDate.now().plusDays(1));
  }

  @FXML
  public void backwardBtnClicked() {
    if (currentSection == 0) {
      goToMainPage();
      return;
    }

    forwardBtn.setDisable(false);
    currentSection -= 1;
    setCorrectSection();
  }

  @FXML
  public void forwardBtnClicked() {
    if (currentSection == 0) {
      roomTypeChanged();
    }

    if (currentSection < 2) {
      currentSection += 1;
      setCorrectSection();
    } else {
      saveBooking();
    }
  }

  private void saveBooking() {
    LocalDate fromDate = fromDatePicker.getValue();
    LocalDate toDate = toDatePicker.getValue();
    Room room = roomListView.getSelectionModel().getSelectedItem();
    Customer customer = customerListView.getSelectionModel().getSelectedItem();

    try {
      Booking booking = new Booking(room.getId(), customer.getId(), fromDate, toDate);
      bookingManager.saveBooking(booking);
    } catch (Exception e) {
      errorLabel.setText(e.getLocalizedMessage());
      return;
    }

    App.callFunction("updatebooking");
    goToMainPage();
  }

  private void setCorrectSection() {
    forwardBtn.setText("Forward");
    errorLabel.setText("");

    switch (currentSection) {
      case 0:
        setDateSectionEnabled(true);
        setRoomSectionEnabled(false);
        setCustomerSectionEnabled(false);
        break;
      case 1:
        setDateSectionEnabled(false);
        setRoomSectionEnabled(true);
        setCustomerSectionEnabled(false);
        break;
      case 2:
        setDateSectionEnabled(false);
        setRoomSectionEnabled(false);
        setCustomerSectionEnabled(true);
        forwardBtn.setText("Complete booking");
        break;
      default:
        break;
    }
  }

  private void setDateSectionEnabled(boolean enabled) {
    dateLabel.setDisable(!enabled);
    fromDatePicker.setDisable(!enabled);
    toDatePicker.setDisable(!enabled);
    dateFromLabel.setDisable(!enabled);
    dateToLabel.setDisable(!enabled);
    backBtn.setDisable(enabled);
  }

  private void setRoomSectionEnabled(boolean enabled) {
    roomLabel.setDisable(!enabled);
    roomTypeComboBox.setDisable(!enabled);
    roomListView.setDisable(!enabled);
  }

  private void setCustomerSectionEnabled(boolean enabled) {
    customerLabel.setDisable(!enabled);
    customerListView.setDisable(!enabled);
  }

  private void loadCustomers() {
    List<Customer> customers = customerManager.listCustomers();
    ObservableList<Customer> observable = FXCollections.observableArrayList(customers);
    customerListView.setItems(observable);
    customerListView.getSelectionModel().selectFirst();
  }

  private void loadRoomTypes() {
    List<RoomType> roomTypes = roomTypeManager.listRoomTypes();
    ObservableList<RoomType> observable = FXCollections.observableArrayList(roomTypes);
    roomTypeComboBox.setItems(observable);
  }

  private void loadRooms() {
    List<Room> rooms = roomManager.listRooms();
    allRooms = rooms;
  }

  @FXML
  public void goToMainPage() {
    setInitialState();
    App.setView("App");
  }

  @FXML
  public void roomTypeChanged() {
    RoomType roomType = roomTypeComboBox.getSelectionModel().getSelectedItem();
    if (roomType == null || fromDatePicker.getValue() == null || toDatePicker.getValue() == null) {
      ObservableList<Room> items = FXCollections.observableArrayList();
      roomListView.setItems(items);
      return;
    }

    List<Room> rooms = allRooms
        .stream()
        .filter(r -> r.getTypeId().equals(roomType.getId()))
        .filter(r -> r.isAvailable(fromDatePicker.getValue(), toDatePicker.getValue()))
        .toList();

    if (rooms.size() == 0) {
      String roomTypeName = roomTypeComboBox.getSelectionModel().getSelectedItem().getName();
      roomLabel.setText("No " + roomTypeName + " available");
      forwardBtn.setDisable(true);
    } else {
      roomLabel.setText("Available rooms");
      forwardBtn.setDisable(false);
    }

    ObservableList<Room> observable = FXCollections.observableArrayList(rooms);
    roomListView.setItems(observable);
    roomListView.getSelectionModel().selectFirst();

  }
}
