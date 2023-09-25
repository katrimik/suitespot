package core.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import core.fileUtil.FileTypeEnum;
import core.fileUtil.IJsonFileParser;
import core.fileUtil.JsonFileParser;
import core.model.Customer;

public class CustomerManager {
    IJsonFileParser<Customer> customerFileManager = new JsonFileParser<Customer>(Customer.class, FileTypeEnum.CUSTOMER);

    public CustomerManager() {

    }

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

    public void saveCustomer(Customer customer) {
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
    }

    public Customer readCustomer(String id) {
        List<Customer> listCustomers = customerFileManager.readFile();
        Customer customer = listCustomers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);

        return customer;
    }

    public List<Customer> listCustomers() {
        return customerFileManager.readFile();
    }


    public static void main(String[] args) {
        var cm = new CustomerManager();

        Customer c1 = new Customer("Banan", "Eplekake", "tull234567@outlook.com", "12345678");
        Customer c2 = new Customer("Benji", "Leverpostei", "hie@jalla.com", "87654321");
        c1.setId("0a49135f-b5ef-4ff1-82a6-bb215a572ea9");
        cm.saveCustomer(c1);
        cm.saveCustomer(c2);

        cm.deleteCustomer("0a49135f-b5ef-4ff1-82a6-bb215a572ea9");

        var customers = cm.listCustomers();
        

        for (Customer c : customers) {
            System.out.println(c.getFullName());
        }



    }

}
