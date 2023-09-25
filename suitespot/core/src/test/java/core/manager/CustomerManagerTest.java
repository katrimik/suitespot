package core.manager;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.fileUtil.IJsonFileParser;
import core.fileUtil.mock.JsonFileParserMock;
import core.model.Customer;

public class CustomerManagerTest {

    private CustomerManager customerManager;
    private Customer newCustomer;

    @BeforeEach
    public void setup() {
        IJsonFileParser<Customer> fileParser = new JsonFileParserMock<Customer>();
        customerManager = new CustomerManager(fileParser);
        newCustomer = new Customer("John", "Nordmann", "john@nordmann.no", "12345678");
    }

    @Test
    public void testDeleteCustomer() {
        String newId = customerManager.saveCustomer(newCustomer);
        customerManager.deleteCustomer(newId);

        List<Customer> customers = customerManager.listCustomers();
        List<Customer> deletedCustomersThatExist = customers.stream().filter(c -> c.getId().equals(newId)).toList();

        assertTrue(customers.size() == 0);
        assertTrue(deletedCustomersThatExist.size() == 0);
    }

    @Test
    public void testListCustomers() {
        List<Customer> contains0Customers = customerManager.listCustomers();
        customerManager.saveCustomer(newCustomer);
        List<Customer> contains1Customers = customerManager.listCustomers();
        newCustomer.setId(null);
        customerManager.saveCustomer(newCustomer);
        List<Customer> contains2Customers = customerManager.listCustomers();

        assertTrue(contains0Customers.size() == 0);
        assertTrue(contains1Customers.size() == 1);
        assertTrue(contains2Customers.size() == 2);
    }

    @Test
    public void testSaveCustomer() {
        String newCustomerId = customerManager.saveCustomer(newCustomer);
        List<Customer> containsCustomers = customerManager.listCustomers();
        Customer foundCustomer = containsCustomers.stream().filter(c -> c.getId().equals(newCustomerId)).findFirst().orElse(null);
        assertNotNull(foundCustomer);
    }

    @Test
    public void testReadCustomer() {
        String customerId = customerManager.saveCustomer(newCustomer);
        Customer customer = customerManager.readCustomer(customerId);
        
        // testing name and not id since id is set dynamically
        assertTrue(newCustomer.getFullName().equals(customer.getFullName()));
    }

    @Test
    public void testUpdateCustomer() {
        String testName = "test update";

        String customerId = customerManager.saveCustomer(newCustomer);
        List<Customer> beforeUpdateCustomers = customerManager.listCustomers();
        newCustomer.setFirstName(testName);
        customerManager.saveCustomer(newCustomer);
        List<Customer> afterUpdateCustomers = customerManager.listCustomers();

        Customer customerWithNewName = customerManager.readCustomer(customerId);

        assertTrue(beforeUpdateCustomers.size() == 1);
        assertTrue(afterUpdateCustomers.size() == 1);
        assertTrue(customerWithNewName.getFirstName().equals(testName));
    }

    @Test
    public void testNotValidCustomerId() {
        newCustomer.setId("not-valid-id");

        assertThrows(IllegalArgumentException.class, () -> {
            customerManager.saveCustomer(newCustomer);
        });
    }
}
