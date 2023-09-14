package ui;

import core.Suitespot;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AppController {
    public AppController(){
        
    }
    


    @FXML
    Label label1;

    @FXML
    public void runTest(){
        label1.setText(Suitespot.test());
        System.out.println("here");
    }

   
}
