package core.fileUtil.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import core.fileUtil.IJsonFileParser;

public class JsonFileParserMock<T> implements IJsonFileParser<T> {
  private ArrayList<T> allItems = new ArrayList<>();

  public ArrayList<T> readFile() {
    return new ArrayList<T>(allItems);
  }

  public void writeFile(List<T> items) {
    allItems = new ArrayList<T>(items);
  }

  public void appendFile(List<T> items) {
    allItems.addAll(new ArrayList<T>(items));
  }

  @SafeVarargs
  public final void appendFile(T... items) {
    appendFile(Arrays.asList(items));
  }
}
