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

import core.manager.Manager;
import core.manager.interfaces.IRoomTypeManager;
import core.model.RoomType;

@RestController
public class RoomTypeController {

  private final IRoomTypeManager rtm;

  @Autowired
  public RoomTypeController() {
    rtm = Manager.getRoomTypeManager();
  }

  @GetMapping("/roomtype")
  public ResponseEntity<List<RoomType>> listRoomTypes() {
    return new ResponseEntity<>(rtm.listRoomTypes(), HttpStatus.OK);
  }

  @GetMapping("/roomtype/{id}")
  public ResponseEntity<RoomType> getRoomType(@PathVariable("id") String id) {
    RoomType roomType = rtm.getRoomType(id);
    return new ResponseEntity<>(roomType, roomType == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
  }

  @PostMapping("/roomtype")
  public ResponseEntity<String> createOrUpdateRoomType(@RequestBody RoomType roomType) {
    try {
      return new ResponseEntity<String>(rtm.saveRoomType(roomType), HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<String>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/roomtype/{id}")
  public void deleteRoomType(@PathVariable("id") String id) {
    rtm.deleteRoomType(id);
  }
}
