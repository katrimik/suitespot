package core.fileUtil;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import core.model.Customer;


public class JsonFileParser<T> implements IJsonFileParser<T> {
    private final Class<T> targetType;
    private final Path path;

    public JsonFileParser(Class<T> targetType, FileTypeEnum fileType) {
        this.targetType = targetType;
        this.path = createPath(fileType);
    }

    private static Path createPath(FileTypeEnum fileType) {
        Path existing = Paths.get("..", "storage", fileType.getFileName() + ".json");
        return existing.toAbsolutePath();
    }

    @Override
    public ArrayList<T> readFile() {
        Gson gson = new Gson();
        ArrayList<T> items = new ArrayList<>();

        try (FileReader reader = new FileReader(path.toString())) {
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
     * @param items List of all Customers, all Rooms, or all Bookings
     */
    @Override
    public void writeFile(List<T> items) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(path.toString())) {
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
    @SafeVarargs 
    public final void appendFile(T... items){
        appendFile(Arrays.asList(items));
    }

    public static void main(String[] args) {
        IJsonFileParser<Customer> customerFileManager = new JsonFileParser<Customer>(Customer.class, FileTypeEnum.CUSTOMER);
        Customer c = new Customer("Banan", "Eplekake", "tull234567@outlook.com", "12345678");
        customerFileManager.appendFile(c);

    }
}
