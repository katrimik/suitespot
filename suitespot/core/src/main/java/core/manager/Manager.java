package core.manager;

import core.fileUtil.FileTypeEnum;
import core.fileUtil.IJsonFileParser;
import core.fileUtil.JsonFileParser;
import core.model.Customer;
import core.model.Room;
import core.model.RoomType;

public class Manager {
    public static CustomerManager GetCustomerManager() {
        IJsonFileParser<Customer> f = new JsonFileParser<Customer>(Customer.class, FileTypeEnum.CUSTOMER);
        return new CustomerManager(f);
    }

    public static IRoomManager GetRoomManager() {
        IJsonFileParser<Room> f = new JsonFileParser<Room>(Room.class, FileTypeEnum.ROOM);
        return new RoomManager(f);
    }

    public static RoomTypeManager GetRoomTypeManager() {
        IJsonFileParser<RoomType> f = new JsonFileParser<RoomType>(RoomType.class, FileTypeEnum.ROOM_TYPE);
        IRoomManager roomManager = Manager.GetRoomManager();
        return new RoomTypeManager(f, roomManager);
    }
}
