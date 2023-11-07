package ui.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ServerUtils {
  public static boolean isServerActive(String basePath) {
    String path = basePath + "/" + "customer";

    HttpResponse<String> response = null;
    try {
      HttpRequest request = HttpRequest.newBuilder(new URI(path))
          .header("Accept", "application/json")
          .GET()
          .build();

      response = HttpClient
          .newBuilder()
          .connectTimeout(Duration.ofMillis(1500))
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException | URISyntaxException | InterruptedException | IllegalArgumentException e) {
      return false;
    }

    if (response == null) {
      return false;
    }

    return response.statusCode() >= 200 && response.statusCode() < 300;
  }
}
