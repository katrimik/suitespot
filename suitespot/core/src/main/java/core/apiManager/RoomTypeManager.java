package core.apiManager;

import java.util.List;

import core.apiConnector.ApiClient;
import core.manager.interfaces.IRoomTypeManager;
import core.model.RoomType;

public class RoomTypeManager implements IRoomTypeManager {

    private final ApiClient<RoomType> client = new ApiClient<RoomType>("/roomtype", RoomType.class);

    public String saveRoomType(RoomType roomType) {
        return client.post(roomType);
    }

    public RoomType getRoomType(String roomTypeId) {
        return client.get(roomTypeId);
    }

    public List<RoomType> listRoomTypes() {
        return client.get();
    }

    public void deleteRoomType(String roomTypeId) {
        client.delete(roomTypeId);
    }
}
