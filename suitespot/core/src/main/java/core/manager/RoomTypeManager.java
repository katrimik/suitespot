package core.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import core.fileUtil.IJsonFileParser;
import core.model.RoomType;

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
        if (roomType.getRoomTypeId() == null) { // creates a new room type
            roomType.setRoomTypeId(createNewId());
            roomTypeManager.appendFile(roomType);
        } else { // saves the changes made to an already existing room type
            ArrayList<RoomType> listRoomTypes = roomTypeManager.readFile();

            RoomType roomTypeToUpdate = listRoomTypes.stream()
                    .filter(rt -> rt.getRoomTypeId().equals(roomType.getRoomTypeId()))
                    .findFirst()
                    .orElse(null);

            if (roomTypeToUpdate == null) {
                throw new IllegalArgumentException("Not a valid id.");
            }

            int roomTypeIndex = listRoomTypes.indexOf(roomTypeToUpdate);
            listRoomTypes.set(roomTypeIndex, roomType);
            roomTypeManager.writeFile(listRoomTypes);
        }

        return roomType.getRoomTypeId();

    }

    public RoomType getRoomType(String roomTypeId) {
        List<RoomType> listRoomTypes = roomTypeManager.readFile();
        RoomType roomType = listRoomTypes.stream()
                .filter(c -> c.getRoomTypeId() == roomTypeId)
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
            if (!(rt.getRoomTypeId().equals(roomTypeId))) {
                tmpRoomTypes.add(rt);
            }
        }

        roomTypeManager.writeFile(tmpRoomTypes);
        roomManager.deleteAllInRoomType(roomTypeId);
    }
}
