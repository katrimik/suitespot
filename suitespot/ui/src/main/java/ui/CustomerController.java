package ui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import core.manager.interfaces.ICustomerManager;
import core.apiManager.Manager;
import core.model.Customer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CustomerController implements Initializable {
  @FXML
  Label label1, StatusErrorLbl;
  @FXML
  TextField customerFirstName, customerLastName, customerPhone, customerEmail;
  @FXML
  Button customerAdd, customerDelete, customerSave;
  @FXML
  ListView<Customer> customerList;
  @FXML
  Text StatusCrudLbl;

  private final ICustomerManager customerManager;

  public CustomerController() {
    customerManager = Manager.getCustomerManager();
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    refreshCustomers();

    customerList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {
      @Override
      public void changed(ObservableValue<? extends Customer> observable, Customer oldValue, Customer newValue) {
        selectedCustomerChanged(newValue);
      }
    });
  }

  @FXML
  public void goToMainPage() {
    App.setView("App");
  }

  @FXML
  public void selectedCustomerChanged(Customer c) {
    if (c == null) {
      StatusCrudLbl.setText("Create new customer");
      return;
    }
    ;

    StatusCrudLbl.setText("Edit customer");

    customerFirstName.setText(c.getFirstName());
    customerLastName.setText(c.getLastName());
    customerPhone.setText(c.getPhone());
    customerEmail.setText(c.getEmail());

  }

  private void refreshCustomers() {
    List<Customer> customers = customerManager.listCustomers();
    ObservableList<Customer> customersOberservable = FXCollections.observableArrayList(customers);
    customerList.setItems(customersOberservable);
  }

  @FXML
  public void customerAdd() {
    resetFields();
  }

  private void resetFields() {
    customerFirstName.setText("");
    customerLastName.setText("");
    customerPhone.setText("");
    customerEmail.setText("");
    StatusErrorLbl.setText("");
    customerList.getSelectionModel().select(null);
  }

  @FXML
  public void customerDelete() {
    Customer listViewCustomer = customerList.getSelectionModel().getSelectedItem();
    if (listViewCustomer == null)
      return;

    customerManager.deleteCustomer(listViewCustomer.getId());

    refreshCustomers();
    resetFields();
  }

  @FXML
  public void customerSave() {
    Customer customer = null;
    try {
      customer = new Customer(customerFirstName.getText(), customerLastName.getText(), customerEmail.getText(),
          customerPhone.getText());
    } catch (Exception e) {
      StatusErrorLbl.setText(e.getLocalizedMessage());
    }

    if (customer == null)
      return;

    Customer listViewCustomer = customerList.getSelectionModel().getSelectedItem();
    if (listViewCustomer != null) {
      customer.setId(listViewCustomer.getId());
    }

    customerManager.saveCustomer(customer);
    refreshCustomers();
    resetFields();
  }

}
