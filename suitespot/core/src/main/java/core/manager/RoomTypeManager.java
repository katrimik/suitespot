package core.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import core.fileUtil.IJsonFileParser;
import core.model.RoomType;

public class RoomTypeManager {

    private IJsonFileParser<RoomType> roomTypeFileManager;

    public RoomTypeManager(IJsonFileParser<RoomType> roomTypeFileManager) {
        this.roomTypeFileManager = roomTypeFileManager;
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
            roomTypeFileManager.appendFile(roomType);
        } else { // saves the changes made to an already existing room type
            ArrayList<RoomType> listRoomTypes = roomTypeFileManager.readFile();

            RoomType roomTypeToUpdate = listRoomTypes.stream()
                    .filter(rt -> rt.getId().equals(roomType.getId()))
                    .findFirst()
                    .orElse(null);

            if (roomTypeToUpdate == null) {
                throw new IllegalArgumentException("Not a valid room type id.");
            }

            int roomTypeIndex = listRoomTypes.indexOf(roomTypeToUpdate);
            listRoomTypes.set(roomTypeIndex, roomType);
            roomTypeFileManager.writeFile(listRoomTypes);
        }

        return roomType.getId();

    }

    public RoomType getRoomType(String roomTypeId) {
        List<RoomType> listRoomTypes = roomTypeFileManager.readFile();
        RoomType roomType = listRoomTypes.stream()
                .filter(c -> c.getId().equals(roomTypeId))
                .findFirst()
                .orElse(null);

        return roomType;
    }

    public List<RoomType> listRoomTypes() {
        return roomTypeFileManager.readFile();
    }

    public void deleteRoomType(String roomTypeId) {
        ArrayList<RoomType> listRoomTypes = roomTypeFileManager.readFile();
        ArrayList<RoomType> tmpRoomTypes = new ArrayList<RoomType>();

        for (RoomType rt : listRoomTypes) {
            if (!(rt.getId().equals(roomTypeId))) {
                tmpRoomTypes.add(rt);
            }
        }

        roomTypeFileManager.writeFile(tmpRoomTypes);

        RoomManager roomManager = Manager.GetRoomManager();
        roomManager.deleteAllInRoomType(roomTypeId);
    }


    public boolean isRoomTypeAvailable(String name, String id) {
        List<RoomType> roomTypes = listRoomTypes();
        return !roomTypes.stream().anyMatch(r -> r.getName().trim().toLowerCase().equals(name.trim().toLowerCase()) && !r.getId().equals(id));
    }
}
