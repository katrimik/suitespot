package core.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import core.fileUtil.IJsonFileParser;
import core.manager.interfaces.ICustomerManager;
import core.model.Customer;

public class CustomerManager implements ICustomerManager {
  IJsonFileParser<Customer> customerFileManager;

  /**
   * Initialize a new CustomerManager.
   * 
   * @param customerFileManager the storage mechanism to use
   * 
   */
  public CustomerManager(IJsonFileParser<Customer> customerFileManager) {
    this.customerFileManager = customerFileManager;
  }

  /**
   * Deletes a customer.
   * 
   * @param id customer id (uuid)
   * 
   */
  @Override
  public void deleteCustomer(String id) {
    ArrayList<Customer> listCustomers = customerFileManager.readFile();
    ArrayList<Customer> tmpCustomers = new ArrayList<Customer>();

    for (Customer c : listCustomers) {
      if (!(c.getId().equals(id))) {
        tmpCustomers.add(c);
      }
    }

    customerFileManager.writeFile(tmpCustomers);
  }

  private String createNewId() {
    UUID id = UUID.randomUUID();
    return id.toString();
  }

  /**
   * Creates and updates customer object.
   * 
   * @param customer customer object
   * 
   * @return customer id (uuid)
   * 
   */
  @Override
  public String saveCustomer(Customer customer) {
    if (customer.getId() == null) { // creates a new customer
      customer.setId(createNewId());
      customerFileManager.appendFile(customer);
    } else { // saves the changes made to an already existing customer
      ArrayList<Customer> listCustomers = customerFileManager.readFile();

      Customer customerToUpdate = listCustomers.stream()
          .filter(c -> c.getId().equals(customer.getId()))
          .findFirst()
          .orElse(null);

      if (customerToUpdate == null) {
        throw new IllegalArgumentException("Not a valid id.");
      }

      int customerIndex = listCustomers.indexOf(customerToUpdate);
      listCustomers.set(customerIndex, customer);
      customerFileManager.writeFile(listCustomers);
    }

    return customer.getId();
  }

  /**
   * Find the customer with given id.
   * 
   * @param id customer id (uuid)
   * 
   * @return given customer object
   * 
   */
  @Override
  public Customer readCustomer(String id) {
    List<Customer> listCustomers = customerFileManager.readFile();
    Customer customer = listCustomers.stream()
        .filter(c -> c.getId().equals(id))
        .findFirst()
        .orElse(null);

    return customer;
  }

  /**
   * List of all customers.
   * 
   * @return list of customer objects
   * 
   */
  @Override
  public List<Customer> listCustomers() {
    return customerFileManager.readFile();
  }
}
