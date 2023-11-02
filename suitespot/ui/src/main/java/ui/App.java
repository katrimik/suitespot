package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JavaFX App
 */
public class App extends Application {
  // Layout engine inspired by: https://youtu.be/C-ReKeKSQrw?si=GAEHbs6U3f9g9GlB

  private static final HashMap<String, AnchorPane> views = new HashMap<>();
  private static AnchorPane rootAnchor;
  private static String currentViewName;
  private static Map<String, Runnable> callbacks = new HashMap<>();

  private static void writeCurrentViewName(String viewName) {
    currentViewName = viewName;
  }

  private static void writeRootAncher(AnchorPane anchor) {
    rootAnchor = anchor;
  }

  @Override
  public void start(Stage stage) throws IOException {
    List<String> viewNames = Arrays.asList("App", "Room", "Customer", "CreateBooking");
    viewNames.forEach(viewName -> loadView(viewName));

    writeRootAncher(FXMLLoader.load(this.getClass().getResource("Layout.fxml")));

    stage.setScene(new Scene(rootAnchor));
    stage.show();

    setView("App");
  }

  private void loadView(String viewName) {
    try {
      AnchorPane view = FXMLLoader.load(getClass().getResource(viewName + ".fxml"));
      views.put(viewName, view);
    } catch (IOException e) {
      System.out.println("Failed to load Layout.fxml");
      e.printStackTrace();
    }
  }

  public static void setView(String viewName) {
    if (!views.containsKey(viewName)) {
      throw new IllegalArgumentException("viewName does not correspond to a stored view");
    }

    rootAnchor.getChildren().remove(views.get(currentViewName));
    rootAnchor.getChildren().add(views.get(viewName));
    writeCurrentViewName(viewName);
  }

  public static void main(String[] args) {
    launch();
  }

  public static void registerFunction(String name, Runnable callback) {
    callbacks.put(name, callback);
  }

  public static void callFunction(String name) {
    Runnable callback = callbacks.get(name);
    if (callback != null) {
      callback.run();
    }
  }
}
