package ui.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.model.Booking;
import core.model.Customer;
import core.model.Room;

public class BookingUtilsTest {

  private List<Booking> bookings = new ArrayList<Booking>();
  private List<Customer> customers = new ArrayList<Customer>();
  private List<Room> rooms = new ArrayList<Room>();

  @BeforeEach
  void setup() {
    bookings.add(new Booking("room1", "customer1", LocalDate.now(), LocalDate.now().plusDays(1), "booking1"));
    bookings
        .add(new Booking("room2", "customer2", LocalDate.now().plusDays(1), LocalDate.now().plusDays(2), "booking2"));
    bookings
        .add(new Booking("room3", "customer2", LocalDate.now().plusDays(2), LocalDate.now().plusDays(3), "booking3"));
    bookings
        .add(new Booking("room1", "customer3", LocalDate.now().plusDays(3), LocalDate.now().plusDays(4), "booking4"));

    Room room1 = new Room(101, "type");
    room1.setId("room1");
    rooms.add(room1);

    Room room2 = new Room(102, "type");
    room2.setId("room2");
    rooms.add(room2);

    Room room3 = new Room(103, "type");
    room3.setId("room3");
    rooms.add(room3);

    Customer customer1 = new Customer();
    customer1.setFirstName("customer-one");
    customer1.setId("customer1");
    customers.add(customer1);

    Customer customer2 = new Customer();
    customer2.setFirstName("customer-two");
    customer2.setId("customer2");
    customers.add(customer2);

    Customer customer3 = new Customer();
    customer3.setFirstName("customer-three");
    customer3.setId("customer3");
    customers.add(customer3);
  }

  @Test
  void testFilterOnCustomerId() {
    List<Booking> result = BookingUtils.filterOnCustomerId(bookings, "customer2");
    List<Booking> filtered = result.stream().filter(r -> r.getId().equals("booking2") || r.getId().equals("booking3"))
        .toList();
    assertEquals(filtered.size(), result.size());
  }

  @Test
  void testFilterOnRoomId() {
    List<Booking> result = BookingUtils.filterOnRoomId(bookings, "room2");
    List<Booking> filtered = result.stream().filter(r -> r.getId().equals("booking2")).toList();
    assertEquals(filtered.size(), result.size());
  }

  @Test
  void testSortOnCustomer() {
    List<Booking> result = BookingUtils.sortOnCustomer(bookings, customers);
    assertEquals(customers.get(0).getId(), result.get(0).getCustomerId());
    assertEquals(customers.get(1).getId(), result.get(2).getCustomerId());
  }

  @Test
  void testSortOnFromDateNewestFirst() {
    List<Booking> result = BookingUtils.sortOnFromDateNewestFirst(bookings);
    assertTrue(result.get(0).getFromDate().compareTo(result.get(1).getFromDate()) > 0);
    assertEquals(customers.get(1).getId(), result.get(2).getCustomerId());
  }

  @Test
  void testSortOnRoom() {
    List<Booking> result = BookingUtils.sortOnRoom(bookings, rooms);
    assertTrue(result.get(0).getRoomId().equals(rooms.get(0).getId()));
    assertTrue(result.get(3).getRoomId().equals(rooms.get(2).getId()));
  }
}
