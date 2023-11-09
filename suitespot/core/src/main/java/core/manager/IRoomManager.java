package core.manager;

import java.util.List;

import core.model.Room;

public interface IRoomManager {

  String saveRoom(Room room);

  List<Room> listRooms();

  /**
   * Deletes one room with the given room id.
   * 
   * @param roomId room id
   * 
   */
  void deleteRoom(String roomId);

  /**
   * Deletes all rooms of given room type id.
   * 
   * @param roomTypeId room type id
   * 
   */
  void deleteAllInRoomType(String roomTypeId);

  /**
   * Checks if the room number is available (aka, not taken).
   * 
   * @param roomNumber room number
   * 
   * @param roomId room id
   * 
   * @return true if available
   * 
   */
  boolean isRoomNumberAvailable(int roomNumber, String roomId);

}