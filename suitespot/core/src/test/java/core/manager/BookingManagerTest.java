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
import core.manager.interfaces.IBookingManager;
import core.manager.interfaces.IRoomTypeManager;
import core.model.Booking;
import core.model.Room;
import core.model.RoomType;

public class BookingManagerTest {
    private IBookingManager bookingManager;
    private RoomManager roomManager;
    private IRoomTypeManager roomTypeManager;
    private Booking newBooking;
    private LocalDate fromDate;
    private LocalDate toDate;

    @BeforeEach
    public void setup() {
        IJsonFileParser<Booking> fileParser = new JsonFileParserMock<Booking>();
        IJsonFileParser<Room> roomFileParser = new JsonFileParserMock<Room>();
        IJsonFileParser<RoomType> roomTypeFileParser = new JsonFileParserMock<RoomType>();

        bookingManager = new BookingManager(fileParser, roomFileParser);
        roomManager = new RoomManager(roomFileParser);
        roomTypeManager = new RoomTypeManager(roomTypeFileParser, roomManager);

        String typeId = roomTypeManager.saveRoomType(new RoomType("suite", null, 1000));
        String roomId = roomManager.saveRoom(new Room(101, typeId));

        fromDate = LocalDate.now();
        toDate = LocalDate.now().plusDays(1);
        newBooking = new Booking(roomId, "customerId", fromDate, toDate);
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
        newBooking.setDates(LocalDate.now().plusDays(10), LocalDate.now().plusDays(12));
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
    public void testNotValidBookingId() {
        newBooking.setId("not-valid-id");

        assertThrows(IllegalArgumentException.class, () -> {
            bookingManager.saveBooking(newBooking);
        });
    }
}
