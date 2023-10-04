package core.model;

public class RoomType {

    private String roomTypeId;
    private String name;
    private int price;

    public RoomType() {
        this.roomTypeId = "";
        this.name = "";
    }

    public RoomType(String name, String roomTypeId, int price) {
        this.name = name;
        this.roomTypeId = roomTypeId;
        this.price = price;
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

    public String getRoomTypeId() {
        return this.roomTypeId;
    }

    public void setId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

}
