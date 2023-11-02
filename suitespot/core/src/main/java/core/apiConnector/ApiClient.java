package core.apiConnector;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import core.fileUtil.LocalDateAdapter;

public class ApiClient<T> {

  private final Class<T> targetType;
  private final String fullBasePath;
  private final Gson gson;

  /**
   * Init a new http client.
   * 
   * @param path       path to api endpoint with leading slash (e.g /customer)
   * 
   * @param targetType type of generic class
   * 
   */
  public ApiClient(String path, Class<T> targetType) {
    this.fullBasePath = "http://localhost:8080" + path;
    this.targetType = targetType;

    this.gson = new GsonBuilder()
        .serializeNulls()
        .registerTypeHierarchyAdapter(LocalDate.class, new LocalDateAdapter().nullSafe())
        .create();
  }

  private HttpResponse<String> getHttp(String path) {
    HttpResponse<String> response = null;
    try {
      HttpRequest request = HttpRequest.newBuilder(new URI(path))
          .header("Accept", "application/json")
          .GET()
          .build();

      response = HttpClient
          .newBuilder()
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException | URISyntaxException | InterruptedException e) {
      throw new RuntimeException(e);
    }

    return response;
  }

  /**
   * 
   * Get all Items.
   * 
   * @return all items
   * 
   */
  public List<T> get() {
    HttpResponse<String> response = getHttp(fullBasePath);

    String responseString = response.body();
    Type genericListType = TypeToken.getParameterized(List.class, targetType).getType();
    List<T> items = gson.fromJson(responseString, genericListType);

    return items;
  }

  /**
   * 
   * Get one item.
   * 
   * @param id of resource to get
   * 
   * @return object of T or null if not found
   * 
   */
  public T get(String id) {
    String path = fullBasePath + "/" + id;
    HttpResponse<String> response = getHttp(path);

    if (response.statusCode() == 404) {
      return null;
    }

    String responseString = response.body();
    T item = gson.fromJson(responseString, targetType);
    return item;
  }

  /**
   * Post request to server.
   * 
   * @param data data to post (body of request)
   * 
   * @return id of created or updated resource
   */
  public String post(T data) {
    String json = gson.toJson(data);
    HttpResponse<String> response = null;

    try {
      HttpRequest request = HttpRequest.newBuilder(new URI(fullBasePath))
          .header("Content-Type", "application/json")
          .POST(BodyPublishers.ofString(json))
          .build();

      response = HttpClient
          .newBuilder()
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException | URISyntaxException | InterruptedException e) {
      throw new RuntimeException(e);
    }

    String responseString = response.body();

    if (response.statusCode() == 400) {
      throw new IllegalArgumentException(responseString);
    }

    return responseString;
  }

  /**
   * 
   * Send a delete request.
   * 
   * @param id of resource to delete
   */
  public void delete(String id) {
    try {
      String path = fullBasePath + "/" + id;
      HttpRequest request = HttpRequest.newBuilder(new URI(path))
          .DELETE()
          .build();

      HttpClient
          .newBuilder()
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());

    } catch (IOException | URISyntaxException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
