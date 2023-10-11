package core.manager;

import java.util.List;

import core.model.Room;

public interface IRoomManager {

  String saveRoom(Room room);

  List<Room> listRooms();

  /**
   * Deletes one room with the given id
   * 
   * @param roomId room id
   * 
   */
  void deleteRoom(String roomId);

  /**
   * Deletes all rooms of given room type-id
   * 
   * @param roomTypeId room type id
   * 
   */
  void deleteAllInRoomType(String roomTypeId);

  /**
   * checks if the room number is available (aka, not taken)
   * 
   * @param roomNumber
   * @param roomId
   * @return bool if it is available
   * 
   */
  boolean isRoomNumberAvailable(int roomNumber, String roomId);

}