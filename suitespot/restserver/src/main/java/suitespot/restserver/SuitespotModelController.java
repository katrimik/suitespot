package suitespot.restserver;

import java.util.List;

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

import core.manager.CustomerManager;
import core.manager.IRoomManager;
import core.manager.Manager;
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
  public List<Customer> c() {
    CustomerManager cm = Manager.getCustomerManager(); 
    return cm.listCustomers();
  }

  @GetMapping(path = "/r")
  public List<Room> r() {
    IRoomManager rm = Manager.getRoomManager();
    return rm.listRooms();
  }

}
