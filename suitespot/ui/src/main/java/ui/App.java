package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {
    // Layout engine inspired by: https://youtu.be/C-ReKeKSQrw?si=GAEHbs6U3f9g9GlB

    private static final HashMap<String, Object> views = new HashMap<>();

    @Override
    public void start(Stage stage) throws IOException {
        List<String> viewNames = Arrays.asList("App", "Room", "Customer");
        viewNames.forEach(viewName -> LoadView(viewName));

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("App.fxml"));

        Parent parent = fxmlLoader.load();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    private void LoadView(String viewName) {
        try {
            Object view = FXMLLoader.load(getClass().getResource(viewName + ".fxml"));
            views.put(viewName, view);
        } catch (IOException e) {
            System.out.println("Failed to load Layout.fxml");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}