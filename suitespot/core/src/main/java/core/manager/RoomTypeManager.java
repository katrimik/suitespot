package core.manager;

import core.fileUtil.IJsonFileParser;
import core.manager.interfaces.IRoomManager;
import core.manager.interfaces.IRoomTypeManager;
import core.model.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomTypeManager implements IRoomTypeManager {

  private IJsonFileParser<RoomType> roomTypeManager;
  private IRoomManager roomManager;

  /**
   * Initialize room type manager.
   * 
   * @param roomTypeFileManager room type file manager object
   * 
   * @param roomManager room manager object
   * 
   */
  public RoomTypeManager(IJsonFileParser<RoomType> roomTypeFileManager, IRoomManager roomManager) {
    this.roomTypeManager = roomTypeFileManager;
    this.roomManager = roomManager;
  }

  /**
   * Creates an unique room type id.
   * 
   * @return room type id (uuid)
   * 
   */
  private String createNewId() {
    UUID id = UUID.randomUUID();
    return id.toString();
  }

  /**
   * Creates and updates room type.
   * 
   * @param roomType roomtype
   * 
   * @return room type id (uuid)
   * 
   */
  @Override
  public String saveRoomType(RoomType roomType) {
    boolean isRoomTypeAvailable = isRoomTypeAvailable(roomType.getName(), roomType.getId());
    if (!isRoomTypeAvailable) {
      throw new IllegalArgumentException("Room type name is already taken");
    }

    if (roomType.getId() == null) { // creates a new room type
      roomType.setId(createNewId());
      roomTypeManager.appendFile(roomType);
    } else { // saves the changes made to an already existing room type
      ArrayList<RoomType> listRoomTypes = roomTypeManager.readFile();

      RoomType roomTypeToUpdate = listRoomTypes.stream()
          .filter(rt -> rt.getId().equals(roomType.getId()))
          .findFirst()
          .orElse(null);

      if (roomTypeToUpdate == null) {
        throw new IllegalArgumentException("Not a valid room type id.");
      }

      int roomTypeIndex = listRoomTypes.indexOf(roomTypeToUpdate);
      listRoomTypes.set(roomTypeIndex, roomType);
      roomTypeManager.writeFile(listRoomTypes);
    }

    return roomType.getId();

  }
  /**
   * Get room type. 
   * 
   * @param roomTypeId room type id
   * 
   * @return room type
   * 
   */
  @Override
  public RoomType getRoomType(String roomTypeId) {
    List<RoomType> listRoomTypes = roomTypeManager.readFile();
    RoomType roomType = listRoomTypes.stream()
        .filter(c -> c.getId().equals(roomTypeId))
        .findFirst()
        .orElse(null);

    return roomType;
  }

  /**
   * List of all room types.
   * 
   * @return list of all room types
   * 
   */
  @Override
  public List<RoomType> listRoomTypes() {
    return roomTypeManager.readFile();
  }

  /**
   * Deletes a room type, including all rooms in type.
   * 
   * @param roomTypeId room type id
   * 
   */
  @Override
  public void deleteRoomType(String roomTypeId) {
    ArrayList<RoomType> listRoomTypes = roomTypeManager.readFile();
    ArrayList<RoomType> tmpRoomTypes = new ArrayList<RoomType>();

    for (RoomType rt : listRoomTypes) {
      if (!(rt.getId().equals(roomTypeId))) {
        tmpRoomTypes.add(rt);
      }
    }

    roomTypeManager.writeFile(tmpRoomTypes);
    roomManager.deleteAllInRoomType(roomTypeId);
  }

  /**
   * Checks if room type is available (aka, doesn't exists any duplicates).
   * 
   * @param name room type name
   * 
   * @param id room type id
   * 
   * @return true if room type is available
   * 
   */
  private boolean isRoomTypeAvailable(String name, String id) {
    List<RoomType> roomTypes = listRoomTypes();
    return !roomTypes
      .stream()
      .anyMatch(r -> r.getName().trim().toLowerCase().equals(name.trim().toLowerCase())
        && !r.getId().equals(id == null ? "" : id));
  }
}
