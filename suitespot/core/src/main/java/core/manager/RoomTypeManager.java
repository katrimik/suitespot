package core.manager;

import core.fileUtil.IJsonFileParser;
import core.model.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomTypeManager {

  private IJsonFileParser<RoomType> roomTypeManager;
  private IRoomManager roomManager;

  public RoomTypeManager(IJsonFileParser<RoomType> roomTypeFileManager, IRoomManager roomManager) {
    this.roomTypeManager = roomTypeFileManager;
    this.roomManager = roomManager;
  }

  private String createNewId() {
    UUID id = UUID.randomUUID();
    return id.toString();
  }

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

  public RoomType getRoomType(String roomTypeId) {
    List<RoomType> listRoomTypes = roomTypeManager.readFile();
    RoomType roomType = listRoomTypes.stream()
        .filter(c -> c.getId().equals(roomTypeId))
        .findFirst()
        .orElse(null);

    return roomType;
  }

  public List<RoomType> listRoomTypes() {
    return roomTypeManager.readFile();
  }

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

  public boolean isRoomTypeAvailable(String name, String id) {
    List<RoomType> roomTypes = listRoomTypes();
    return !roomTypes
      .stream()
      .anyMatch(r -> r.getName().trim().toLowerCase().equals(name.trim().toLowerCase())
        && !r.getId().equals(id == null ? "" : id));
  }
}
