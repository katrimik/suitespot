package ui.utils;

public class ListViewItem<T> {

  private String displayName;
  private T value;

  public ListViewItem(String displayName, T value) {
    this.displayName = displayName;
    this.value = value;
  }

  public ListViewItem(T value) {
    this.value = value;
  }

  public ListViewItem() {

  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }

  public String getDisplayName() {
    return displayName;
  }

  @Override
  public String toString() {
    return getDisplayName();
  }
}
