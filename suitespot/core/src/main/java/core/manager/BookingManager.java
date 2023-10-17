package core.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import core.fileUtil.IJsonFileParser;
import core.model.Booking;

public class BookingManager {

    private IJsonFileParser<Booking> bookingFileManager;

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
    public BookingManager(IJsonFileParser<Booking> bookingFileManager) {
        this.bookingFileManager = bookingFileManager;
    }

    /**
     * Deletes a booking from the file storage used.
     * 
     * @param bookingId the id of the booking to delete
     */
    public void deleteBooking(String bookingId) {
        List<Booking> bookings = bookingFileManager.readFile();
        bookings = bookings.stream().filter(b -> !b.getId().equals(bookingId)).toList();
        bookingFileManager.writeFile(bookings);
    }

    /**
     * Saves a booking, creates a new one if the id==null, else it updates.
     * 
     * @param booking the booking to create or update
     * 
     */
    public String saveBooking(Booking booking) {
        if (booking.getId() == null) {
            booking.setId(createNewId());
            bookingFileManager.appendFile(booking);
        } else {
            // Trying a little different update approach here compared to the other managers
            List<Booking> bookings = bookingFileManager.readFile();
            List<Booking> updateBookings = bookings.stream().filter(b -> !b.getId().equals(booking.getId())).toList();
            if (bookings.size() == updateBookings.size()) {
                throw new IllegalArgumentException("The booking had an id that didn't exist in the database");
            }

            bookingFileManager.writeFile(updateBookings);
            bookingFileManager.appendFile(booking);
        }

        return booking.getId();
    }

    /**
     * Gets a single booking.
     * 
     * @param bookingId Id of booking to get
     */
    public Booking getBooking(String bookingId) {
        List<Booking> bookings = bookingFileManager.readFile();
        Booking booking = bookings.stream()
                .filter(c -> c.getId().equals(bookingId))
                .findFirst()
                .orElse(null);

        return booking;
    }

    /**
     * Gets all bookings stored in the system (the storage engine provided in the constructor).
     */
    public List<Booking> listBookings() {
        return new ArrayList<Booking>(bookingFileManager.readFile());
    }
}
