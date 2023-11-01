package ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import core.model.Customer;

/**
 * TestFX App test
 */
public class CustomerControllerTest extends ApplicationTest {

  private Parent root;
  private Scene scene;
  private FxRobot robot = new FxRobot();

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Customer.fxml"));
    root = fxmlLoader.load();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public Parent getRootNode() {
    return root;
  }

  @Test
  public void clickListView() {

    String name = "Sarah Johnson";
    robot.clickOn("#customerList").clickOn(name);
    FxAssert.verifyThat("#customerList", NodeMatchers.isFocused());

    Customer selectedCustomer = (Customer) robot.lookup("#customerList").queryAs(ListView.class).getSelectionModel()
        .getSelectedItem();

    String selectedCustomerFirstName = robot.lookup("#customerFirstName").queryAs(TextField.class).getText();
    String selectedCustomerLastName = robot.lookup("#customerLastName").queryAs(TextField.class).getText();
    String selectedCustomerPhone = robot.lookup("#customerPhone").queryAs(TextField.class).getText();
    String selectedCustomerEmail = robot.lookup("#customerEmail").queryAs(TextField.class).getText();

    assertEquals(selectedCustomerFirstName, selectedCustomer.getFirstName());
    assertEquals(selectedCustomerLastName, selectedCustomer.getLastName());
    assertEquals(selectedCustomerEmail, selectedCustomer.getEmail());
    assertEquals(selectedCustomerPhone, selectedCustomer.getPhone());

  }

  @Test
  public void customerAddTest() {
    robot.clickOn("#customerAdd");
    String selectedCustomerFirstName = robot.lookup("#customerFirstName").queryAs(TextField.class).getText();
    String selectedCustomerLastName = robot.lookup("#customerLastName").queryAs(TextField.class).getText();
    String selectedCustomerPhone = robot.lookup("#customerPhone").queryAs(TextField.class).getText();
    String selectedCustomerEmail = robot.lookup("#customerEmail").queryAs(TextField.class).getText();
    String selectedTitle = robot.lookup("#StatusCrudLbl").queryAs(Text.class).getText();

    // check if the fields are empty and no element in the listview is selected
    assertEquals(selectedCustomerFirstName, "");
    assertEquals(selectedCustomerLastName, "");
    assertEquals(selectedCustomerEmail, "");
    assertEquals(selectedCustomerPhone, "");
    assertEquals(selectedTitle, "Create new customer");
  }

  @Test
  public void customerAddAndDeleteTest() {
    // deleting when no customer selected
    robot.clickOn("#customerAdd");
    @SuppressWarnings("unchecked")
    ObservableList<Customer> listviewCustomers = robot.lookup("#customerList").queryAs(ListView.class).getItems();
    robot.clickOn("#customerDelete");
    @SuppressWarnings("unchecked")
    ObservableList<Customer> listviewCustomersNew = robot.lookup("#customerList").queryAs(ListView.class).getItems();

    assertEquals(listviewCustomers, listviewCustomersNew);

    // add New customer
    String fullname = createStandardCustomer("Elias");

    @SuppressWarnings("unchecked")
    ObservableList<Customer> listviewCustomersNewAfterAdd = robot.lookup("#customerList").queryAs(ListView.class).getItems();
    assertNotEquals(listviewCustomersNewAfterAdd, listviewCustomersNew);

    deleteCustomer(fullname);

  }

  @Test
  public void notValidUserTest() {
    // create user with invalid name
    createStandardCustomer("Elias.-?");
    String errorString = robot.lookup("#StatusErrorLbl").queryAs(Label.class).getText();
    assertFalse(errorString.equals(""));

  }

  @Test
  public void updateUserTest() {
    String newPhone = "12345678";

    // create customer
    String fullName = createStandardCustomer("Elias");

    // update customer

    robot.clickOn("#customerList").clickOn(fullName);
    robot.clickOn("#customerPhone");
    // clear textField
    robot.lookup("#customerPhone").queryAs(TextField.class).setText("");
    ;
    robot.write(newPhone);
    robot.clickOn("#customerSave");
    robot.clickOn("#customerList").clickOn(fullName);

    String selectedCustomerPhone = robot.lookup("#customerPhone").queryAs(TextField.class).getText();
    assertTrue(selectedCustomerPhone.equals(newPhone));

    // delete customer
    deleteCustomer(fullName);

  }

  private String createStandardCustomer(String firstName) {
    String lastName = "Hetland";
    String phone = "98765432";
    String mail = "test@example.com";

    robot.clickOn("#customerFirstName");
    robot.write(firstName);
    robot.clickOn("#customerLastName");
    robot.write(lastName);
    robot.clickOn("#customerEmail");
    robot.write(mail);
    robot.clickOn("#customerPhone");
    robot.write(phone);
    robot.clickOn("#customerSave");
    return firstName + " " + lastName;
  }

  private void deleteCustomer(String fullName) {
    robot.clickOn("#customerList").clickOn(fullName);
    robot.clickOn("#customerDelete");
  }
}
