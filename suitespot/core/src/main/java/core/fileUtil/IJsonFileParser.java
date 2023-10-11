package core.fileUtil;

import java.util.ArrayList;
import java.util.List;

public interface IJsonFileParser<T> {

  ArrayList<T> readFile();

  /**
   * Write a file. Overriding all existing items
   * 
   * @param items List of all Customers, all Rooms, or all Bookings
   */
  void writeFile(List<T> items);

  void appendFile(List<T> items);

  void appendFile(T item);

}