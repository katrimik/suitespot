package ui;

import core.Suitespot;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class CustomerController {    


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

    @FXML
    public void customerAdd(){
        
    }


    @FXML
    public void runTest(){
        label1.setText(Suitespot.test());
        System.out.println("here");
    }

   
}
