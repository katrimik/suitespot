package core.manager;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.fileUtil.IJsonFileParser;
import core.fileUtil.mock.JsonFileParserMock;
import core.model.Booking;
import core.model.Room;

public class BookingManagerTest {
    private BookingManager bookingManager;
    private RoomManager roomManager;
    private Booking newBooking;
    private LocalDate fromDate;
    private LocalDate toDate;

    @BeforeEach
    public void setup() {
        IJsonFileParser<Booking> fileParser = new JsonFileParserMock<Booking>();
        IJsonFileParser<Room> roomFileParser = new JsonFileParserMock<Room>();
        bookingManager = new BookingManager(fileParser, roomFileParser);
        roomManager = new RoomManager(roomFileParser);

        fromDate = LocalDate.now();
        toDate = LocalDate.now().plusDays(1);
        newBooking = new Booking("roomId", "customerId", fromDate, toDate);
    }

    @Test
    public void testDeleteBooking() {
        String newId = bookingManager.saveBooking(newBooking);
        bookingManager.deleteBooking(newId);

        List<Booking> bookings = bookingManager.listBookings();
        List<Booking> deletedBookingsThatExist = bookings.stream().filter(c -> c.getId().equals(newId)).toList();

        assertTrue(bookings.size() == 0);
        assertTrue(deletedBookingsThatExist.size() == 0);
    }

    @Test
    public void testListBookings() {
        List<Booking> contains0Bookings = bookingManager.listBookings();
        bookingManager.saveBooking(newBooking);
        List<Booking> contains1Bookings = bookingManager.listBookings();
        newBooking.setId(null);
        bookingManager.saveBooking(newBooking);
        List<Booking> contains2Bookings = bookingManager.listBookings();

        assertTrue(contains0Bookings.size() == 0);
        assertTrue(contains1Bookings.size() == 1);
        assertTrue(contains2Bookings.size() == 2);
    }

    @Test
    public void testSaveBooking() {
        String newBookingId = bookingManager.saveBooking(newBooking);
        List<Booking> containsBookings = bookingManager.listBookings();
        Booking foundBooking = containsBookings.stream().filter(c -> c.getId().equals(newBookingId)).findFirst()
                .orElse(null);
        assertNotNull(foundBooking);
    }

    @Test
    public void testReadBooking() {
        String bookingId = bookingManager.saveBooking(newBooking);
        Booking booking = bookingManager.getBooking(bookingId);

        // testing name and not id since id is set dynamically
        assertTrue(newBooking.getRoomId().equals(booking.getRoomId()));
    }

    @Test
    public void testUpdateBooking() {
        String testRoomId = "roomId";

        String customerId = bookingManager.saveBooking(newBooking);
        List<Booking> beforeUpdateBookings = bookingManager.listBookings();
        newBooking.setRoomId(testRoomId);
        bookingManager.saveBooking(newBooking);
        List<Booking> afterUpdateBookings = bookingManager.listBookings();

        Booking customerWithNewName = bookingManager.getBooking(customerId);

        assertTrue(beforeUpdateBookings.size() == 1);
        assertTrue(afterUpdateBookings.size() == 1);
        assertTrue(customerWithNewName.getRoomId().equals(testRoomId));
    }

    @Test
    public void testNotValidBookingId() {
        newBooking.setId("not-valid-id");

        assertThrows(IllegalArgumentException.class, () -> {
            bookingManager.saveBooking(newBooking);
        });
    }
}
