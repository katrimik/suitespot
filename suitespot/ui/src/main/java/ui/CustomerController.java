package ui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


import core.manager.CustomerManager;
import core.model.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CustomerController implements Initializable {    
    @FXML
    Label label1;
    @FXML
    TextField customerFirstName;
    @FXML
    TextField customerLastName;
    @FXML
    TextField customerPhone;
    @FXML
    TextField customerEmail;
    @FXML
    Button customerAdd;
    @FXML
    Button customerDelete;
    @FXML
    Button customerSave;
    @FXML
    ListView <String> customerList;

    private String currentCustomerId;
    private final CustomerManager customerManager;



    public CustomerController() {
        customerManager = new CustomerManager();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("init");

    }

    @FXML
    public void customerAdd() {
        // currentCustomerId = null;
        resetFields();
    }

    private void resetFields() {
        customerFirstName.setText("");
        customerLastName.setText("");
        customerPhone.setText("");
        customerEmail.setText("");
    }

    @FXML
    public void customerDelete() {
        System.out.println(customerManager.listCustomers());
    }

    @FXML
    public void customerSave() {

        Customer customer = new Customer(customerFirstName.getText(), customerLastName.getText(), customerEmail.getText(), customerPhone.getText());
        System.out.println(customer);
        customerManager.saveCustomer(customer);

    }

    public static void main(String[] args) {
        CustomerController c = new CustomerController();
        Customer customer = new Customer("sdasfaf", "easddsr", "elisssa@gmail.com", "99999999");
        c.customerManager.saveCustomer(customer);
        List<Customer> customers = c.customerManager.listCustomers();
        customers.forEach(x-> System.out.println(x));
    }

}
