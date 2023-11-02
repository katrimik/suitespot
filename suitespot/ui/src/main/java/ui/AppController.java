package ui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import core.manager.interfaces.IBookingManager;
import core.manager.interfaces.ICustomerManager;
import core.manager.interfaces.IRoomManager;
import core.manager.Manager;
import core.manager.interfaces.IRoomTypeManager;
import core.model.Booking;
import core.model.Customer;
import core.model.Room;
import core.model.RoomType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import ui.utils.BookingUtils;
import ui.utils.ListViewItem;

public class AppController implements Initializable {
  @FXML
  Button createBtn, deleteBtn;

  @FXML
  Label dateFromLbl, dateToLbl, roomDataLbl, roomTypeDataLbl, customerDataLbl;

  @FXML
  TextField searchField;

  @FXML
  ListView<ListViewItem<Booking>> bookingListView;

  @FXML
  RadioButton customerRadioBtn, roomRadioBtn, dateRadioBtn;

  private List<Booking> bookings;
  private List<ListViewItem<Booking>> allListViewBookings;
  private final IRoomManager roomManager;
  private final IRoomTypeManager roomTypeManager;
  private final IBookingManager bookingManager;
  private final ICustomerManager customerManager;

  @FXML
  public void openCustomerView() {
    App.setView("Customer");
  }

  @FXML
  public void openRoomView() {
    App.setView("Room");
  }

  public AppController() {
    bookingManager = Manager.getBookingManager();
    roomManager = Manager.getRoomManager();
    roomTypeManager = Manager.getRoomTypeManager();
    customerManager = Manager.getCustomerManager();

  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    loadBookings();
    resetFields();

    bookingListView.setOnMouseClicked(event -> {
      ListViewItem<Booking> bookingItem = bookingListView.getSelectionModel().getSelectedItem();
      if (bookingItem == null) {
        return;
      }

      Booking selectedBooking = bookingItem.getValue();
      if (selectedBooking != null) {
        displaySelectedBooking(selectedBooking);
      }
    });

    App.registerFunction("updatebooking", () -> {
      displayBookings();
    });
  }

  private void loadBookings() {
    bookings = bookingManager.listBookings();
    displayBookings();
  }

  private void displayBookings() {
    List<ListViewItem<Booking>> viewBookings = bookings.stream()
        .map(b -> {

          String displayName = "";

          if (dateRadioBtn.isSelected()) {
            displayName = b.getFromDate().toString();
          } else if (roomRadioBtn.isSelected()) {
            Room room = roomManager.getRoom(b.getRoomId());
            RoomType roomType = roomTypeManager.getRoomType(room.getTypeId());
            displayName = room.getRoomNumber() + " (" + roomType.getName() + ")";
          } else if (customerRadioBtn.isSelected()) {
            Customer customer = customerManager.readCustomer(b.getCustomerId());
            displayName = customer.getFullName();
          }

          return new ListViewItem<Booking>(displayName, b);
        })
        .toList();

    ObservableList<ListViewItem<Booking>> bookingObservable = FXCollections.observableArrayList(viewBookings);
    allListViewBookings = viewBookings;
    bookingListView.setItems(bookingObservable);
  }

  @FXML
  public void bookingDelete() {
    ListViewItem<Booking> listViewBooking = bookingListView.getSelectionModel().getSelectedItem();
    if (listViewBooking == null)
      return;

    bookingManager.deleteBooking(listViewBooking.getValue().getId());

    loadBookings();
    resetFields();
  }

  private void displaySelectedBooking(Booking b) {
    Room room = roomManager.getRoom(b.getRoomId());

    dateFromLbl.setText(b.getFromDate().toString());
    dateToLbl.setText(b.getToDate().toString());

    roomDataLbl.setText(String.valueOf(room.getRoomNumber()));

    roomTypeDataLbl.setText(roomTypeManager
        .listRoomTypes()
        .stream()
        .filter(rt -> rt.getId()
            .equals(room.getTypeId()))
        .findFirst().orElse(null).getName());

    customerDataLbl.setText(customerManager
        .listCustomers()
        .stream()
        .filter(c -> c.getId()
            .equals(b.getCustomerId()))
        .findFirst()
        .orElse(null).getFullName());
  }

  public void sortBookingListView(ActionEvent event) {
    bookings = bookingManager.listBookings();
    searchField.setText("");

    if (dateRadioBtn.isSelected()) {
      bookings = BookingUtils.sortOnFromDateNewestFirst(bookings);
    } else if (roomRadioBtn.isSelected()) {
      bookings = BookingUtils.sortOnRoom(bookings, roomManager.listRooms());
    } else if (customerRadioBtn.isSelected()) {
      bookings = BookingUtils.sortOnCustomer(bookings, customerManager.listCustomers());
    }

    displayBookings();
  }

  private void resetFields() {
    dateFromLbl.setText("");
    dateToLbl.setText("");
    roomDataLbl.setText("");
    roomTypeDataLbl.setText("");
    customerDataLbl.setText("");
    bookingListView.getSelectionModel().select(null);
  }

  @FXML
  public void search() {
    String searchText = searchField.getText();
    if (searchText.trim().equals("")) {
      displayBookings();
      return;
    }

    List<ListViewItem<Booking>> filteredBookings = allListViewBookings.stream()
        .filter((b -> b.getDisplayName().toLowerCase().contains(searchText.toLowerCase()))).toList();
    ObservableList<ListViewItem<Booking>> bookingObservable = FXCollections.observableArrayList(filteredBookings);
    bookingListView.setItems(bookingObservable);
  }

  @FXML
  public void onCreateBtnPressed() {
    App.setView("CreateBooking");
  }
}