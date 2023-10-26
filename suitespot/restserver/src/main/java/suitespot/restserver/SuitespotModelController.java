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


  @GetMapping(path = "/customer")
  public List<Customer> defaultCustomer() {
    CustomerManager cm = Manager.getCustomerManager(); 
    return cm.listCustomers();
  }

  @GetMapping(path = "/customer/firstname/{firstName}")
  public List<Customer> customer(@PathVariable("firstName") String firstName) {
    CustomerManager cm = Manager.getCustomerManager(); 
    return cm.listCustomers().stream().filter(c->c.getFirstName().equals(firstName)).toList();
  }

  @PutMapping(path = "/customer/{firstName}/rename/{newName}")
  public void renameCustomer(@PathVariable("firstName") String firstName, @PathVariable("newName")String newName) {
    CustomerManager cm = Manager.getCustomerManager(); 
    Customer customer = cm.listCustomers().stream().filter(c->c.getFirstName().equals(firstName)).findFirst().orElse(null);
    if (customer == null) {
      return;
    }
    customer.setFirstName(newName);
    cm.saveCustomer(customer);
  }





  @GetMapping(path = "/r")
  public List<Room> r() {
    IRoomManager rm = Manager.getRoomManager();
    return rm.listRooms();
  }

}
