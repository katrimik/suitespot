package core.model;

public class RoomType {

    private String roomTypeId;
    private String name;

    public RoomType() {
        this.roomTypeId = "";
        this.name = "";
    }

    public RoomType(String name, String roomTypeId) {
        this.name = name;
        this.roomTypeId = roomTypeId;
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

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

}
