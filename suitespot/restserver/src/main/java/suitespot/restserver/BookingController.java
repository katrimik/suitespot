package suitespot.restserver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import core.manager.BookingManager;
import core.manager.Manager;
import core.model.Booking;

@RestController
public class BookingController {

  private final BookingManager bm;

  @Autowired
  public BookingController() {
    bm = Manager.getBookingManager();
  }

  @GetMapping("/booking")
  public ResponseEntity<List<Booking>> listBookings() {
    return new ResponseEntity<>(bm.listBookings(), HttpStatus.OK);
  }

  @GetMapping("/booking/{id}")
  public ResponseEntity<Booking> getBooking(@PathVariable("id") String id) {
    Booking booking = bm.getBooking(id);
    return new ResponseEntity<>(booking, booking == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
  }

  @PostMapping("/booking")
  public ResponseEntity<String> createOrUpdateBooking(@RequestBody Booking booking) {
    try {
      return new ResponseEntity<String>(bm.saveBooking(booking), HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<String>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/booking/{id}")
  public void deleteBooking(@PathVariable("id") String id) {
    bm.deleteBooking(id);
  }
}
