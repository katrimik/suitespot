package core.apiManager;

import core.manager.interfaces.*;

public class Manager {

  /**
   * Creates a new fully equipped customer manager.
   * 
   * @return a customer manager object
   * 
   */
  public static ICustomerManager getCustomerManager() {
    return new CustomerManager();
  }

  /**
   * Creates a new fully equipped room manager.
   * 
   * @return a room manager object
   * 
   */
  public static IRoomManager getRoomManager() {
    return new RoomManager();
  }

  /**
   * Creates a new fully equipped room type manager.
   * 
   * @return a room type manager object
   * 
   */
  public static IRoomTypeManager getRoomTypeManager() {
    return new RoomTypeManager();
  }

  /**
   * Creates a new fully equipped booking manager.
   * 
   * @return a booking manager object
   * 
   */
  public static IBookingManager getBookingManager() {
    return new BookingManager();
  }    
}
