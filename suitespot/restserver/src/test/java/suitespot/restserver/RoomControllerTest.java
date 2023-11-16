package suitespot.restserver;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import core.apiConnector.ApiClient;
import core.model.Room;

public class RoomControllerTest {
  @Test
  void roomControllerEndpointTest() {
    var client = new ApiClient<Room>("/room", Room.class);
    var listItems = client.get();
    assertTrue(listItems.size() > 0);

    var item = listItems.get(0);
    var itemFetched = client.get(item.getId());
    assertTrue(itemFetched.getId().equals(item.getId()));
    assertTrue(itemFetched.getRoomNumber() > 100);
  }
}
