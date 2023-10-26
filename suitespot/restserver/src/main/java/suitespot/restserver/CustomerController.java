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

import core.manager.CustomerManager;
import core.manager.Manager;
import core.model.Customer;

@RestController
public class CustomerController {

  private final CustomerManager cm;

  @Autowired
  public CustomerController() {
    cm = Manager.getCustomerManager();
  }

  @GetMapping("/customer")
  public ResponseEntity<List<Customer>> listCustomers() {
    return new ResponseEntity<>(cm.listCustomers(), HttpStatus.OK);
  }

  @GetMapping("/customer/{id}")
  public ResponseEntity<Customer> getCustomer(@PathVariable("id") String id) {
    Customer customer = cm.readCustomer(id);
    return new ResponseEntity<>(customer, customer == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
  }

  @PostMapping("/customer")
  public ResponseEntity<String> createOrUpdateCustomer(@RequestBody Customer customer) {
    try {
      return new ResponseEntity<String>(cm.saveCustomer(customer), HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<String>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/customer/{id}")
  public void deleteCustomer(@PathVariable("id") String id) {
    cm.deleteCustomer(id);
  }
}
