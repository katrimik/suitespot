package core.model;

public class RoomType {

    private String id;
    private String name;
    private int price;

    public RoomType() {
        this.id = "";
        this.name = "";
    }

    public RoomType(String name, String id, int price) {
        this.setName(name);
        this.setId(id);
        this.setPrice(price);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if(price < 0){
            throw new IllegalArgumentException("Can't be a negative price");
        }
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    private boolean validateName(String name) {
        return name.matches("^[a-zA-ZÅÄÖåäö_]+([- ]?[a-zA-ZÅÄÖåäö_]+)*$");
    }

    public void setName(String name) {
        if (!validateName(name)) {
            throw new IllegalArgumentException("Not a room type.");
        }

        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
