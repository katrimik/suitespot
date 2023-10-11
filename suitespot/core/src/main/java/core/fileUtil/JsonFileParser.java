package core.fileUtil;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonFileParser<T> implements IJsonFileParser<T> {
  private final Class<T> targetType;
  private final Path path;
  private final Gson gson;

  public JsonFileParser(Class<T> targetType, FileTypeEnum fileType) {
    this.targetType = targetType;
    this.path = createPath(fileType);

    // Configure Gson with custom serializer/deserializer here
    this.gson = new GsonBuilder()
        .registerTypeHierarchyAdapter(LocalDate.class, new LocalDateAdapter().nullSafe())
        .create();
  }

  private static Path createPath(FileTypeEnum fileType) {
    Path existing = Paths.get("..", "storage", fileType.getFileName() + ".json");
    return existing.toAbsolutePath();
  }

  @Override
  public ArrayList<T> readFile() {
    ArrayList<T> items = new ArrayList<>();

    try (FileReader reader = new FileReader(path.toString(), StandardCharsets.UTF_8)) {
      // Use TypeToken to specify the type you want to deserialize into
      Type genericListType = TypeToken.getParameterized(List.class, targetType).getType();
      items = gson.fromJson(reader, genericListType);

      // If file empty, return empty list
      if (items == null) {
        items = new ArrayList<T>();
      }
    } catch (Exception e) {
      // This should never happen, and is likely an error with the environment
      e.printStackTrace();
    }

    return items;
  }

  /**
   * Write a file. Overriding all existing items
   * 
   * @param items List of all Customers, all Rooms, or all Bookings
   */
  @Override
  public void writeFile(List<T> items) {
    try (FileWriter writer = new FileWriter(path.toString(), StandardCharsets.UTF_8)) {
      writer.write(gson.toJson(items));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void appendFile(List<T> items) {
    List<T> readItems = readFile();
    readItems.addAll(items);
    writeFile(readItems);
  }

  @Override
  public final void appendFile(T item) {
    appendFile(Arrays.asList(item));
  }

}
