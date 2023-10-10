package core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoomTypeTest {

    private RoomType roomType;

    @BeforeEach
    void setup() {
        roomType = new RoomType();
    }

    @Test
    void testInitalDataConstructor() {
        roomType = new RoomType("name", "123", 200);

        assertTrue(roomType.getName().equals("name"));
        assertTrue(roomType.getId().equals("123"));
        assertEquals(200, roomType.getPrice());
    }

    @Test 
    void testConstructor() {
        assertTrue(roomType.getId().equals(""));
        assertTrue(roomType.getName().equals(""));
    }

    @Test
    void testSetGetName() {
        String name = "testname";
        roomType.setName(name);
        assertTrue(roomType.getName().equals(name));
    }

    @Test
    void testSetGetRoomTypeId() {
        String name = "testid";
        roomType.setId(name);
        assertTrue(roomType.getId().equals(name));       
    }

    @Test
    void testSetGetPrice() {
        roomType.setPrice(300);
        assertEquals(300, roomType.getPrice());
    }

    @Test
    void testSetNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            roomType.setPrice(-1);
        });
    }

    @Test
    void testSetInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> {
            roomType.setName("123456");
        });
    }
}
