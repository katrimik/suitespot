package core.manager;

import core.fileUtil.FileTypeEnum;
import core.fileUtil.IJsonFileParser;
import core.fileUtil.JsonFileParser;
import core.model.Customer;
import core.model.Room;
import core.model.RoomType;

public class Manager {
  public static CustomerManager getCustomerManager() {
    IJsonFileParser<Customer> f = new JsonFileParser<Customer>(Customer.class, FileTypeEnum.CUSTOMER);
    return new CustomerManager(f);
  }

  public static IRoomManager getRoomManager() {
    IJsonFileParser<Room> f = new JsonFileParser<Room>(Room.class, FileTypeEnum.ROOM);
    return new RoomManager(f);
  }

  public static RoomTypeManager getRoomTypeManager() {
    IJsonFileParser<RoomType> f = new JsonFileParser<RoomType>(RoomType.class, FileTypeEnum.ROOM_TYPE);
    IRoomManager roomManager = Manager.getRoomManager();
    return new RoomTypeManager(f, roomManager);
  }
}
