package core.manager.interfaces;

import java.util.List;

import core.model.RoomType;

public interface IRoomTypeManager {

  /**
   * Creates and updates room type.
   * 
   * @param roomType roomtype
   * 
   * @return room type id (uuid)
   * 
   */
  String saveRoomType(RoomType roomType);

  /**
   * Get room type.
   * 
   * @param roomTypeId room type id
   * 
   * @return room type
   * 
   */
  RoomType getRoomType(String roomTypeId);

  /**
   * List of all room types.
   * 
   * @return list of all room types
   * 
   */
  List<RoomType> listRoomTypes();

  /**
   * Deletes a room type, including all rooms in type.
   * 
   * @param roomTypeId room type id
   * 
   */
  void deleteRoomType(String roomTypeId);

}