package core.manager.interfaces;

import java.util.List;

import core.model.Booking;

public interface IBookingManager {

  /**
   * Deletes a booking from the file storage used.
   * 
   * @param bookingId the id of the booking to delete
   */
  void deleteBooking(String bookingId);

  /**
   * Saves a booking, creates a new one if the id==null, a booking can't be
   * updated
   * 
   * @param booking the booking to create
   * 
   */
  String saveBooking(Booking booking);

  /**
   * Gets a single booking.
   * 
   * @param bookingId Id of booking to get
   */
  Booking getBooking(String bookingId);

  /**
   * Gets all bookings stored in the system (the storage engine provided in the
   * constructor).
   */
  List<Booking> listBookings();

}