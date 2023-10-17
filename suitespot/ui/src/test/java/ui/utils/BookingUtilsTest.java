package ui.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.model.Booking;
import core.model.Customer;

public class BookingUtilsTest {

    private List<Booking> bookings = new ArrayList<Booking>();
    private List<Customer> customers = new ArrayList<Customer>();

    @BeforeEach
    void setup() {
        bookings.add(new Booking("room1", "customer1", LocalDate.now(), LocalDate.now().plusDays(1), "booking1"));
        bookings.add(new Booking("room2", "customer2", LocalDate.now(), LocalDate.now().plusDays(1), "booking2"));
        bookings.add(new Booking("room3", "customer2", LocalDate.now(), LocalDate.now().plusDays(1), "booking3"));
        bookings.add(new Booking("room1", "customer3", LocalDate.now(), LocalDate.now().plusDays(1), "booking4"));
    }


    @Test
    void testFilterOnCustomerId() {
        List<Booking> result = BookingUtils.filterOnCustomerId(bookings, "customer2");
        List<Booking> filtered = result.stream().filter(r -> r.getId().equals("booking2") || r.getId().equals("booking3")).toList();
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
    }

    @Test
    void testSortOnFromDateNewestFirst() {

    }

    @Test
    void testSortOnRoom() {

    }
}
