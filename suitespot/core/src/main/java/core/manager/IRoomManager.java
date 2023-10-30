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
   * Get room by id
   * 
   * @param roomId id of room (uuid)
   * 
   * @return Room (or null if room does not exist)
   * 
   */
  Room getRoom(String roomId);

}