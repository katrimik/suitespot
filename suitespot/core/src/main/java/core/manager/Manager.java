package core.manager;

import core.fileUtil.FileTypeEnum;
import core.fileUtil.IJsonFileParser;
import core.fileUtil.JsonFileParser;
import core.model.Booking;
import core.model.Customer;
import core.model.Room;
import core.model.RoomType;

public class Manager {
  
  /**
   * Creates a new fully equipped customer manager.
   * 
   * @return a customer manager object
   * 
   */
  public static CustomerManager getCustomerManager() {
    IJsonFileParser<Customer> f = new JsonFileParser<Customer>(Customer.class, FileTypeEnum.CUSTOMER);
    return new CustomerManager(f);
  }

  /**
   * Creates a new fully equipped room manager.
   * 
   * @return a room manager object
   * 
   */
  public static IRoomManager getRoomManager() {
    IJsonFileParser<Room> f = new JsonFileParser<Room>(Room.class, FileTypeEnum.ROOM);
    return new RoomManager(f);
  }
  
  /**
   * Creates a new fully equipped room type manager.
   * 
   * @return a room type manager object
   * 
   */
  public static RoomTypeManager getRoomTypeManager() {
    IJsonFileParser<RoomType> f = new JsonFileParser<RoomType>(RoomType.class, FileTypeEnum.ROOM_TYPE);
    IRoomManager roomManager = Manager.getRoomManager();
    return new RoomTypeManager(f, roomManager);
  }

   /**
   * Creates a new fully equipped booking manager.
   * 
   * @return a booking manager object
   * 
   */
  public static BookingManager getBookingManager() {
    IJsonFileParser<Booking> f = new JsonFileParser<Booking>(Booking.class, FileTypeEnum.BOOKING);
    return new BookingManager(f);
  }
}
