package core.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import core.fileUtil.IJsonFileParser;
import core.model.Room;

public class RoomManager {
    /*
     * save
     * get
     * list
     * delete
     * 
     * 
     * listbytype
     * getavailabilty
     */
    private IJsonFileParser<Room> roomManager;

    public RoomManager(IJsonFileParser<Room> roomManager) {
        this.roomManager = roomManager;
    }
    private String createNewId() { //dupliserer?
        UUID id = UUID.randomUUID();
        return id.toString();
    }

    
    public String saveRoom(Room room) {
        if (room.getRoomId() == null) { // creates a new room type
            room.setRoomId(createNewId());
            roomManager.appendFile(room);
        } 
        
        else { // saves the changes made to an already existing room type
            ArrayList<Room> listRoom = roomManager.readFile();

            Room roomsToUpdate = listRoom.stream()
                    .filter(r -> r.getRoomId().equals(room.getRoomId()))
                    .findFirst()
                    .orElse(null);

            if (roomsToUpdate == null) {
                throw new IllegalArgumentException("Not an id.");
            }

            int roomIndex = listRoom.indexOf(roomsToUpdate);
            listRoom.set(roomIndex, room);
            roomManager.writeFile(listRoom);
        }

        return room.getRoomId();

    }

    public List<Room> listRooms() {
        return roomManager.readFile();
    }

    /**
     * Deletes one room with the given id
     * @param roomId
     */
    public void deleteRoom(String roomId) {
        ArrayList<Room> listRoom = roomManager.readFile();
        ArrayList<Room> tmpRoom = new ArrayList<Room>();

        for (Room r : listRoom) {
            if (!(r.getRoomId().equals(roomId))) {
                tmpRoom.add(r);
            }
        }

        roomManager.writeFile(tmpRoom);
    }

    /**
     * Deletes all rooms of given room type-id
     * @param roomTypeId
     */
    public void deleteAllInRoomType(String roomTypeId){
        ArrayList<Room> listRoom = roomManager.readFile();
        ArrayList<Room> tmpRooms = new ArrayList<Room>();

        for (Room r : listRoom) {
            if(!(r.getTypeId().equals(roomTypeId))){
                tmpRooms.add(r);
            }
        }
        roomManager.writeFile(tmpRooms);

    }
}
