package ui;

import java.net.URL;
import java.util.ResourceBundle;

import core.Suitespot;
//import core.manager.CustomerManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class CustomerController implements Initializable {    
    @FXML
    Label label1;
    Text customerFirstName;
    Text customerLastName;
    Text customerPhone;
    Text customerEmail;
    Button customerAdd;
    Button customerDelete;
    Button customerSave;
    ListView <String> customerList;

    private String currentCustomerId;

    //private CustomerManager customerCore;

    public CustomerController() {
        System.out.println("hei");
        //var customerCore = new CustomerManager();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("init");
        var ss = new Suitespot();
        var cm = ss.getCustomerManager();
        System.out.println("init done");
    }

    @FXML
    public void customerAdd() {
        // currentCustomerId = null;
        // resetFields();
    }

    private void resetFields() {
        customerFirstName.setText("");
        customerLastName.setText("");
        customerPhone.setText("");
        customerEmail.setText("");
    }

    @FXML
    public void customerDelete() {

    }

    @FXML
    public void customerSave() {

    }



    // @FXML
    // public void initialize() {
    //     System.out.println("hei");
    //     customerCore = new CustomerManager();
    // }
}
