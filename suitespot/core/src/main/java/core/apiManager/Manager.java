package core.apiManager;

import java.net.URI;
import java.net.URISyntaxException;

import core.manager.interfaces.IBookingManager;
import core.manager.interfaces.ICustomerManager;
import core.manager.interfaces.IRoomManager;
import core.manager.interfaces.IRoomTypeManager;

public class Manager {

  private static String apiUrl = "http://localhost:8080";

  /**
   * Creates a new fully equipped customer manager.
   * 
   * @return a customer manager object
   * 
   */
  public static ICustomerManager getCustomerManager() {
    return new CustomerManager(apiUrl);
  }

  /**
   * Creates a new fully equipped room manager.
   * 
   * @return a room manager object
   * 
   */
  public static IRoomManager getRoomManager() {
    return new RoomManager(apiUrl);
  }

  /**
   * Creates a new fully equipped room type manager.
   * 
   * @return a room type manager object
   * 
   */
  public static IRoomTypeManager getRoomTypeManager() {
    return new RoomTypeManager(apiUrl);
  }

  /**
   * Creates a new fully equipped booking manager.
   * 
   * @return a booking manager object
   * 
   */
  public static IBookingManager getBookingManager() {
    return new BookingManager(apiUrl);
  }

  public static String getApiUrl() {
    return apiUrl;
  }

  public static void setApiUrl(String url) throws URISyntaxException {
    new URI(url);
    apiUrl = url;
  }
}
