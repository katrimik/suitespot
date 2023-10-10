package core.manager;

import java.util.List;

import core.model.Room;

public interface IRoomManager {

  String saveRoom(Room room);

  List<Room> listRooms();

  /**
   * Deletes one room with the given id
   * 
   * @param roomId
   */
  void deleteRoom(String roomId);

  /**
   * Deletes all rooms of given room type-id
   * 
   * @param roomTypeId
   */
  void deleteAllInRoomType(String roomTypeId);

  /**
   * checks if the room number is available (aka, not taken)
   * 
   * @param roomNumber
   * @param roomId
   * @return
   */
  boolean isRoomNumberAvailable(int roomNumber, String roomId);

}