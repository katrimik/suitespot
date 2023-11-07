package core.apiManager;

import java.util.List;

import core.apiConnector.ApiClient;
import core.manager.interfaces.IBookingManager;
import core.model.Booking;

public class BookingManager implements IBookingManager {

  private final ApiClient<Booking> client = new ApiClient<Booking>("/booking", Booking.class);

  public void deleteBooking(String bookingId) {
    client.delete(bookingId);
  }

  public String saveBooking(Booking booking) {
    return client.post(booking);
  }

  public Booking getBooking(String bookingId) {
    return client.get(bookingId);
  }

  public List<Booking> listBookings() {
    return client.get();
  }
}
