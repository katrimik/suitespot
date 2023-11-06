package core.manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import core.fileUtil.IJsonFileParser;
import core.manager.interfaces.IBookingManager;
import core.model.Booking;
import core.model.Room;

public class BookingManager implements IBookingManager {

  private IJsonFileParser<Booking> bookingFileManager;
  private IJsonFileParser<Room> roomFileManager;

  private String createNewId() {
    UUID id = UUID.randomUUID();
    return id.toString();
  }

  /**
   * Initializes a new BookingManager.
   * 
   * @param bookingFileManager the storage mechanism to use
   * 
   */
  public BookingManager(IJsonFileParser<Booking> bookingFileManager, IJsonFileParser<Room> roomFileManager) {
    this.bookingFileManager = bookingFileManager;
    this.roomFileManager = roomFileManager;
  }

  /**
   * Deletes a booking from the file storage used.
   * 
   * @param bookingId the ID of the booking to delete
   * 
   */
  @Override
  public void deleteBooking(String bookingId) {
    List<Booking> bookings = bookingFileManager.readFile();
    bookings = bookings.stream().filter(b -> {
      boolean isBookingToDelete = b.getId().equals(bookingId);
      if (isBookingToDelete) {
        removeDatesFromRoom(b);
      }

      return !isBookingToDelete;
    }).toList();
    bookingFileManager.writeFile(bookings);
  }

  /**
   * Saves a booking, creates a new one if the ID==null, a booking can't be
   * updated
   * 
   * @param booking the booking to create
   * 
   */
  @Override
  public String saveBooking(Booking booking) {
    if (booking.getId() != null) {
      throw new IllegalArgumentException("Booking must be null");
    }

    booking.setId(createNewId());
    bookingFileManager.appendFile(booking);
    saveDatesToRoom(booking);

    return booking.getId();
  }

  private void saveDatesToRoom(Booking booking) {
    String roomId = booking.getRoomId();
    LocalDate startDate = booking.getFromDate();
    LocalDate endDate = booking.getToDate();

    List<Room> rooms = roomFileManager.readFile();
    Room roomToUpdate = rooms.stream()
        .filter(r -> r.getId().equals(roomId))
        .findFirst()
        .orElse(null);

    if (roomToUpdate == null) {
      throw new IllegalArgumentException("Room not found");
    }

    roomToUpdate.bookRoom(startDate, endDate);
    int roomIndex = rooms.indexOf(roomToUpdate);
    rooms.set(roomIndex, roomToUpdate);
    roomFileManager.writeFile(rooms);
  }

  private void removeDatesFromRoom(Booking booking) {
    String roomId = booking.getRoomId();
    LocalDate startDate = booking.getFromDate();
    LocalDate endDate = booking.getToDate();

    List<Room> rooms = roomFileManager.readFile();
    Room roomToUpdate = rooms.stream()
        .filter(r -> r.getId().equals(roomId))
        .findFirst()
        .orElse(null);

    if (roomToUpdate == null) {
      throw new IllegalArgumentException("Room not found");
    }

    while (!startDate.isAfter(endDate)) {
      roomToUpdate.deBookRoom(startDate);
      startDate = startDate.plusDays(1);
    }

    int roomIndex = rooms.indexOf(roomToUpdate);
    rooms.set(roomIndex, roomToUpdate);
    roomFileManager.writeFile(rooms);
  }

  /**
   * Gets a single booking.
   * 
   * @param bookingId ID of booking to get
   * 
   */
  @Override
  public Booking getBooking(String bookingId) {
    List<Booking> bookings = bookingFileManager.readFile();
    Booking booking = bookings.stream()
        .filter(c -> c.getId().equals(bookingId))
        .findFirst()
        .orElse(null);

    return booking;
  }

  /**
   * Gets all bookings stored in the system (the storage engine provided in the
   * constructor).
   * 
   */
  @Override
  public List<Booking> listBookings() {
    return new ArrayList<Booking>(bookingFileManager.readFile());
  }
}
