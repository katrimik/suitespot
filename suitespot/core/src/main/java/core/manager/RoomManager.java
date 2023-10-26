package core.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import core.fileUtil.IJsonFileParser;
import core.model.Room;

public class RoomManager implements IRoomManager {

  private IJsonFileParser<Room> roomManager;

  public RoomManager(IJsonFileParser<Room> roomManager) {
    this.roomManager = roomManager;
  }

  /**
   * Creates an unique id.
   * 
   * @return room manager id (uuid)
   * 
   */
  private String createNewId() { 
    UUID id = UUID.randomUUID();
    return id.toString();
  }

  /**
   * Creates and updates a room.
   * 
   * @param room room
   * 
   * @return room id (uuid)
   * 
   */
  public String saveRoom(Room room) {
    boolean isRoomNumberAvailable = isRoomNumberAvailable(room.getRoomNumber(), room.getId());
    if (!isRoomNumberAvailable) {
      throw new IllegalArgumentException("Room number already taken");
    }

    if (room.getId() == null) { // creates a new room type
      room.setId(createNewId());
      roomManager.appendFile(room);
    } else { // saves the changes made to an already existing room type
      ArrayList<Room> listRoom = roomManager.readFile();

      Room roomsToUpdate = listRoom.stream()
          .filter(r -> r.getId().equals(room.getId()))
          .findFirst()
          .orElse(null);

      if (roomsToUpdate == null) {
        throw new IllegalArgumentException("Not an id.");
      }

      int roomIndex = listRoom.indexOf(roomsToUpdate);
      listRoom.set(roomIndex, room);
      roomManager.writeFile(listRoom);
    }

    return room.getId();

  }

  @Override
  public List<Room> listRooms() {
    return roomManager.readFile();
  }

  /**
   * Deletes one room with the given id.
   * 
   * @param roomId room id
   * 
   */
  @Override
  public void deleteRoom(String roomId) {
    ArrayList<Room> listRoom = roomManager.readFile();
    ArrayList<Room> tmpRoom = new ArrayList<Room>();

    for (Room r : listRoom) {
      if (!(r.getId().equals(roomId))) {
        tmpRoom.add(r);
      }
    }

    roomManager.writeFile(tmpRoom);
  }

  /**
   * Deletes all rooms of given room type id.
   * 
   * @param roomTypeId room type id
   * 
   */
  public void deleteAllInRoomType(String roomTypeId) {
    ArrayList<Room> listRoom = roomManager.readFile();
    ArrayList<Room> tmpRooms = new ArrayList<Room>();

    for (Room r : listRoom) {
      if (!(r.getTypeId().equals(roomTypeId))) {
        tmpRooms.add(r);
      }
    }

    roomManager.writeFile(tmpRooms);
  }

  /**
   * Checks if room is available (aka, not any duplicate numbers).
   * 
   * @param roomNumber room number
   * 
   * @param roomId room id
   * 
   * @return true if room is available
   * 
   */
  private boolean isRoomNumberAvailable(int roomNumber, String roomId) {
    List<Room> rooms = listRooms();
    return !rooms.stream().anyMatch(r -> r.getRoomNumber() == roomNumber && !r.getId().equals(roomId));
  }
}
