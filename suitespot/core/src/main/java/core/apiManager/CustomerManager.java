package core.apiManager;

import java.util.List;

import core.apiConnector.ApiClient;
import core.manager.interfaces.ICustomerManager;
import core.model.Customer;

public class CustomerManager implements ICustomerManager {

  private final ApiClient<Customer> client = new ApiClient<Customer>("/customer", Customer.class);

  public CustomerManager(String url) {
    client.setApiUrl(url);
  }

  public void deleteCustomer(String id) {
    client.delete(id);
  }

  public String saveCustomer(Customer customer) {
    return client.post(customer);
  }

  public Customer readCustomer(String id) {
    return client.get(id);
  }

  public List<Customer> listCustomers() {
    return client.get();
  }
}
