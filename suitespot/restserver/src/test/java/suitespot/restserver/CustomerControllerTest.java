package suitespot.restserver;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import core.apiConnector.ApiClient;
import core.model.Customer;

public class CustomerControllerTest {
  @Test
  void customerControllerEndpointTest() {
    var client = new ApiClient<Customer>("/customer", Customer.class);
    var listItems = client.get();
    assertTrue(listItems.size() > 0);

    var item = listItems.get(0);
    var itemName = item.getFullName();
    assertTrue(itemName.length() > 0);

    var itemFetched = client.get(item.getId());
    assertTrue(itemFetched.getFullName().equals(itemName));
  }
}
