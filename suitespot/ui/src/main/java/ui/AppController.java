package ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import core.manager.BookingManager;
import core.manager.CustomerManager;
import core.manager.IRoomManager;
import core.manager.Manager;
import core.manager.RoomTypeManager;
import core.model.Booking;
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

public class AppController implements Initializable {
  @FXML
  Button createBtn, deleteBtn;

  @FXML
  Label bookingDataLbl, dateDataLbl, roomDataLbl, roomTypeDataLbl, customerDataLbl;

  @FXML
  TextField searchField;

  @FXML
  ListView<Booking> bookingListView;

  @FXML
  RadioButton customerRadioBtn, roomRadioBtn, dateRadioBtn;

  private List<Booking> bookings;
  private final IRoomManager roomManager;
  private final RoomTypeManager roomTypeManager;
  private final BookingManager bookingManager;
  private final CustomerManager customerManager;

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
  }

  private void loadBookings() {
    bookings = bookingManager.listBookings();
    displayBookings();
  }

  private void displayBookings() {
    ObservableList<Booking> bookingObservable = FXCollections.observableArrayList(bookings);
    bookingListView.setItems(bookingObservable);

    // ObservableList<Booking> bookingObservable =
    // FXCollections.observableArrayList();
    // // TODO: JEG TRENGER HJEEEELP
    // for (Booking booking : bookings) {
    // bookingObservable.(customBookingStringFormatter(booking));
    // }

    // bookingListView.setItems(bookingObservable);

    bookingListView.setOnMouseClicked(event -> {
      Booking selectedBooking = bookingListView.getSelectionModel().getSelectedItem();
      // TODO: tror ikke det er riktig å plassere den her egentlig, men initalize
      // fungerer jo ikke pga. layout ...
      if (selectedBooking != null) {
        displaySelectedBooking(selectedBooking);
      }
    });
  }

  @FXML
  public void bookingDelete() {
    resetFields();
  }

  private void displaySelectedBooking(Booking b) {
    Room room = roomManager.listRooms()
        .stream()
        .filter(r -> r.getId().equals(b.getRoomId()))
        .findFirst()
        .orElse(null);

    bookingDataLbl.setText(b.getId());

    dateDataLbl.setText("From " + b.getFromDate() + " to " + b.getToDate());

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
        .orElse(null).getFirstName());
  }

  public void sortBookingListView(ActionEvent event) {
    if (dateRadioBtn.isSelected()) {
      bookings = bookingManager.listBookings();

      bookings.forEach(s -> System.out.println("" + s.getFromDate() + "-" + s.getToDate()));

      BookingUtils.sortOnFromDateNewestFirst(bookings);
      displayBookings();

    } else if (roomRadioBtn.isSelected()) {
      bookings = bookingManager.listBookings();

      bookings.forEach(s -> System.out.println("" +
          roomManager
              .listRooms()
              .stream()
              .filter(a -> a.getId()
                  .equals(s.getRoomId()))
              .findFirst()
              .orElse(null)));

      BookingUtils.sortOnRoom(bookings, roomManager.listRooms());
      displayBookings();

    } else if (customerRadioBtn.isSelected()) {
      bookings = bookingManager.listBookings();

      bookings.forEach(s -> System.out.println("" +
          customerManager
              .listCustomers()
              .stream()
              .filter(a -> a.getId()
                  .equals(s.getCustomerId()))
              .findFirst()
              .orElse(null)));

      BookingUtils.sortOnCustomer(bookings, customerManager.listCustomers());
      displayBookings();
    }
  }

  private String customBookingStringFormatter(Booking booking) {
    if (customerRadioBtn.isSelected()) {
      return customerManager
          .listCustomers()
          .stream()
          .filter(c -> c.getId().equals(booking.getCustomerId()))
          .findFirst()
          .map(customer -> customer.getFirstName() + " " + customer.getLastName())
          .orElse("No customer found");
    } else if (roomRadioBtn.isSelected()) {
      Room room = roomManager
          .listRooms()
          .stream()
          .filter(r -> r.getId().equals(booking.getRoomId()))
          .findFirst()
          .orElse(null);
      return (room != null) ? String.valueOf(room.getRoomNumber()) : "No room found";
    } else if (dateRadioBtn.isSelected()) {
      return "From " + booking.getFromDate() + " to " + booking.getToDate();
    }
    return "Select a sorting option"; // Default or an invalid case
  }

  private void resetFields() {
    bookingDataLbl.setText("");
    dateDataLbl.setText("");
    roomDataLbl.setText("");
    roomTypeDataLbl.setText("");
    customerDataLbl.setText("");
    bookingListView.getSelectionModel().select(null);
  }

}