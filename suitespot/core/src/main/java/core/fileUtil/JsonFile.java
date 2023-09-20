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

public class JsonFile<T> {
    private final Class<T> targetType;
    private final Path path;

    public JsonFile(Class<T> targetType, FileTypeEnum fileType) {
        this.targetType = targetType;
        this.path = createPath(fileType);
    }

    private static Path createPath(FileTypeEnum fileType) {
        return Paths.get("storage", fileType.getFileName() + ".json");
    }

    public List<T> readFile() {
        Gson gson = new Gson();
        List<T> items = new ArrayList<>();

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
     * @param fileType CUSTOMER, ROOM, BOOKING 
     * @param items List of all Customers, all Rooms, or all Bookings
     */
    public void writeFile(List<T> items) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(path.toString())) {
            writer.write(gson.toJson(items));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void appendFile(List<T> items) {
        List<T> readItems = readFile();
        readItems.addAll(items);
        writeFile(readItems);
    }

    @SafeVarargs 
    public final void appendFile(T... items){
        appendFile(Arrays.asList(items));
    }

 

    public static void main(String[] args) {
        JsonFile<Customer> jsonFile = new JsonFile<Customer>(Customer.class, FileTypeEnum.CUSTOMER);
        Customer c1 = new Customer("Benji", "12");
        Customer c2 = new Customer("elias", "19");

        jsonFile.appendFile(c1, c2, c1);
        List<Customer> test = jsonFile.readFile();
        // System.out.println(test);
        test.stream().forEach(x -> System.out.println(x.getName()));
        // System.out.println(test);

        
    }

}
