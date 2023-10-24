package suitespot.springboot.restserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import core.model.Customer;
import core.model.Room;

@RestController
public class SuitespotModelController {
  @GetMapping(path = "/hello")
  public String hello() {
    return "hello";
  }

  @GetMapping(path = "/test")
  public String test() {
    return "test fungerer";
  }

  @GetMapping(path = "/c")
  public Customer c() {
    Customer customer = new Customer("elias", "hetland", "test@gmail.com", "91827364");
    return customer;
  }

  @GetMapping(path = "/r")
  public Room r() {
    Room room = new Room(110);
    return room;
  }

}
