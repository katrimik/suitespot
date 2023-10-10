package core.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

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
    roomManager.deleteRoom(roomId);
    List<Room> rooms = roomManager.listRooms();
    assertEquals(0, rooms.size());
  }

  @Test
  void testDuplicateRoomNumber() {
    Room room1 = new Room(101);
    Room room2 = new Room(101);

    roomManager.saveRoom(room1);

    assertThrows(IllegalArgumentException.class, () -> {
      roomManager.saveRoom(room2);
    });
  }

  @Test
  void testUpdateRoom() {
    Room room = new Room(101);
    String roomId = roomManager.saveRoom(room);
    room.setRoomNumber(102);
    roomManager.saveRoom(room);
    Room updateRoom = roomManager.listRooms().stream().filter(r -> r.getId().equals(roomId)).findFirst().orElse(null);
    if (updateRoom == null) {
      throw new IllegalStateException("It should find the room");
    }

    assertEquals(102, updateRoom.getRoomNumber());
  }
}
