package core.apiManager;

import java.util.List;

import core.apiConnector.ApiClient;
import core.manager.interfaces.IRoomManager;
import core.model.Room;

public class RoomManager implements IRoomManager {

  private final ApiClient<Room> client = new ApiClient<Room>("/room", Room.class);

  public String saveRoom(Room room) {
    return client.post(room);
  }

  public List<Room> listRooms() {
    return client.get();
  }

  public void deleteRoom(String roomId) {
    client.delete(roomId);
  }

  public void deleteAllInRoomType(String roomTypeId) {
    client.delete("roomtype/" + roomTypeId);
  }

  public Room getRoom(String roomId) {
    return client.get(roomId);
  }
}
