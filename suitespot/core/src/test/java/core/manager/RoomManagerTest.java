package core.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.fileUtil.IJsonFileParser;
import core.fileUtil.mock.JsonFileParserMock;
import core.model.Room;

public class RoomManagerTest {

    private RoomManager roomManager;

    @BeforeEach
    void setup() {
        IJsonFileParser<Room> fileParser = new JsonFileParserMock<Room>();
        roomManager = new RoomManager(fileParser);
    }

    @Test
    void testDeleteAllInRoomType() {
        String typeId = "type1";

        for (int i = 1; i <= 5; i++) {
            Room room = new Room(Integer.parseInt("10" + i));
            room.setTypeId(typeId);
            roomManager.saveRoom(room);
        }

        assertEquals(5, roomManager.listRooms().size());
        roomManager.deleteAllInRoomType(typeId);
        assertEquals(0, roomManager.listRooms().size());
    }

    @Test
    void testDeleteRoom() {
        Room room = new Room(101);
        String roomId = roomManager.saveRoom(room);
    roomManager.listRooms();

    }

    @Test
    void testListRooms() {

    }

    @Test
    void testSaveRoom() {

    }
}
