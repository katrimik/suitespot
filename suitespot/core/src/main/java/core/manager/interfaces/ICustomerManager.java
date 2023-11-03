package core.manager.interfaces;

import java.util.List;

import core.model.Customer;

public interface ICustomerManager {

    /**
     * Deletes a customer.
     * 
     * @param id customer id (uuid)
     * 
     */
    void deleteCustomer(String id);

    /**
     * Creates and updates customer object.
     * 
     * @param customer customer object
     * 
     * @return customer id (uuid)
     * 
     */
    String saveCustomer(Customer customer);

    /**
     * Find the customer with given id.
     * 
     * @param id customer id (uuid)
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