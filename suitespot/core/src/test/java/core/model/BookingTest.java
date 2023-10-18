package core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookingTest {
    private Booking booking;
    private LocalDate fromDate;
    private LocalDate toDate;

    @BeforeEach
    void setup() {
        fromDate = LocalDate.now();
        toDate = LocalDate.now().plusDays(1);
        booking = new Booking("roomId", "customerId", fromDate, toDate);
    }

    @Test
    void testGetCustomerId() {
        assertEquals("customerId", booking.getCustomerId());
    }

    @Test
    void testGetFromDate() {
        assertEquals(fromDate, booking.getFromDate());
    }

    @Test
    void testGetId() {
        assertEquals(null, booking.getId());
        booking.setId("test");
        assertEquals("test", booking.getId());
    }

    @Test
    void testGetRoomId() {
        assertEquals("roomId", booking.getRoomId());
    }

    @Test
    void testGetToDate() {
        assertEquals(toDate, booking.getToDate());
    }

    @Test
    void testSetDates() {
        LocalDate newToDate = toDate.plusDays(1);
        booking.setDates(fromDate, newToDate);

        assertEquals(fromDate, booking.getFromDate());
        assertEquals(newToDate, booking.getToDate());

        assertThrows(IllegalArgumentException.class, () -> {
            booking.setDates(newToDate, fromDate);
        });
    }
}
