package core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoomTest {

    private Room room;

    @BeforeEach
    void setup() {
        room = new Room();
    }

    @Test
    void testConstructors() {
        room = new Room(4);
        assertEquals(4, room.getRoomNumber());

        room = new Room(5, "123");
        assertEquals(5, room.getRoomNumber());
        assertEquals("123", room.getTypeId());
    }

    @Test
    void testSetGetRoomId() {
        room.setRoomId("123");
        assertEquals("123", room.getRoomId());
    }

    @Test
    void testSetGetRoomNumber() {
        room.setRoomNumber(200);
        assertEquals(200, room.getRoomNumber());
    }

    @Test
    void testInvalidRoomNumber() {
        ArrayList<Integer> invalidValues = new ArrayList<>(Arrays.asList(-2, 0, 2, 100, 601, 1000));

        invalidValues.forEach(invalidValue -> {
            assertThrows(IllegalArgumentException.class, () -> {
                room.setRoomNumber(invalidValue);
            });
        });
    }

    @Test
    void testSetGetTypeId() {
        room.setTypeId("123");
        assertTrue(room.getTypeId().equals("123"));
    }

    @Test
    void testBookedDates() {
        LocalDate fromDate = LocalDate.of(2023, 1, 1);
        LocalDate toDate = LocalDate.of(2023, 1, 10);
        room.bookRoom(fromDate, toDate);

        assertThrows(IllegalArgumentException.class, () -> {
            room.bookRoom(fromDate, toDate);
        });

        Set<LocalDate> bookedDates = room.getBookedDates();
        assertTrue(bookedDates.contains(fromDate));
        assertTrue(bookedDates.contains(toDate));


        assertFalse(room.isAvailable(LocalDate.of(2023, 1, 9)));
        assertTrue(room.isAvailable(LocalDate.of(2023, 2, 4)));

        assertFalse(room.isAvailable(LocalDate.of(2023, 1, 5), LocalDate.of(2023, 1, 17)));
        assertTrue(room.isAvailable(LocalDate.of(2023, 1, 11), LocalDate.of(2023, 1, 17)));
    }
}
