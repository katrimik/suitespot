package suitespot.restserver;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import core.manager.Manager;
import core.manager.interfaces.IRoomManager;
import core.model.Room;

@RestController
public class RoomController {

  private final IRoomManager rm;

  public RoomController() {
    rm = Manager.getRoomManager();
  }

  @GetMapping("/room")
  public ResponseEntity<List<Room>> listRooms() {
    return new ResponseEntity<>(rm.listRooms(), HttpStatus.OK);
  }

  @GetMapping("/room/{id}")
  public ResponseEntity<Room> getRoom(@PathVariable("id") String id) {
    Room room = rm.getRoom(id);
    return new ResponseEntity<>(room, room == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
  }

  @PostMapping("/room")
  public ResponseEntity<String> createOrUpdateRoom(@RequestBody Room room) {
    try {
      return new ResponseEntity<String>(rm.saveRoom(room), HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<String>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/room/roomtype/{id}")
  public void deleteAllInRoomType(@PathVariable("id") String id) {
    rm.deleteAllInRoomType(id);
  }

  @DeleteMapping("/room/{id}")
  public void deleteRoom(@PathVariable("id") String id) {
    rm.deleteRoom(id);
  }
}
