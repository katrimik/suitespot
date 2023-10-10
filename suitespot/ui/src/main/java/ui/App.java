package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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

  private static final HashMap<String, AnchorPane> views = new HashMap<>();
  private static AnchorPane rootAnchor;
  private static String currentViewName;

  @Override
  public void start(Stage stage) throws IOException {
    List<String> viewNames = Arrays.asList("App", "Room", "Customer");
    viewNames.forEach(viewName -> LoadView(viewName));

    rootAnchor = FXMLLoader.load(this.getClass().getResource("Layout.fxml"));

    rootAnchor.getChildren().add(views.get("App"));
    currentViewName = "App";
    stage.setScene(new Scene(rootAnchor));
    stage.show();
  }

  private void LoadView(String viewName) {
    try {
      AnchorPane view = FXMLLoader.load(getClass().getResource(viewName + ".fxml"));
      views.put(viewName, view);
    } catch (IOException e) {
      System.out.println("Failed to load Layout.fxml");
      e.printStackTrace();
    }
  }

  public static void SetView(String viewName) {
    if (!views.containsKey(viewName)) {
      throw new IllegalArgumentException("viewName does not correspond to a stored view");
    }

    rootAnchor.getChildren().remove(views.get(currentViewName));
    rootAnchor.getChildren().add(views.get(viewName));
    currentViewName = viewName;
  }

  public static void main(String[] args) {
    launch();
  }
}