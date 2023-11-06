package core.manager.interfaces;

import java.util.List;

import core.model.Customer;

public interface ICustomerManager {

    /**
     * Deletes a customer.
     * 
     * @param id customer ID (UUID)
     * 
     */
    void deleteCustomer(String id);

    /**
     * Creates and updates customer object.
     * 
     * @param customer customer object
     * 
     * @return customer ID (UUID)
     * 
     */
    String saveCustomer(Customer customer);

    /**
     * Find the customer with given ID.
     * 
     * @param id customer ID (UUID)
     * 
     * @return given customer object
     * 
     */
    Customer readCustomer(String id);

    /**
     * List of all customers.
     * 
     * @return list of customer objects
     * 
     */
    List<Customer> listCustomers();

}