package ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
  // Layout engine inspired by: https://youtu.be/C-ReKeKSQrw?si=GAEHbs6U3f9g9GlB

  private static AnchorPane rootAnchor;
  private static Map<String, Runnable> callbacks = new HashMap<>();

  private static void writeRootAncher(AnchorPane anchor) {
    rootAnchor = anchor;
  }

  @Override
  public void start(Stage stage) throws IOException {
    writeRootAncher(FXMLLoader.load(this.getClass().getResource("Layout.fxml")));

    stage.setScene(new Scene(rootAnchor));
    stage.show();

    setView("App");
  }

  private static AnchorPane loadView(String viewName) {
    try {
      AnchorPane view = FXMLLoader.load(App.class.getResource(viewName + ".fxml"));
      return view;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static void setView(String viewName) {
    AnchorPane view = loadView(viewName);
    rootAnchor.getChildren().add(view);
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
