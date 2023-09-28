package core.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Room {
    private String id;
    private int roomNumber;
    private String typeId;
    private String availability;
    private int price;
    private Set<Date> dates = new HashSet<Date>();

    public Room(String id, int roomNumber, String typeId, String availability, int price) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.typeId = typeId;
        this.availability = availability;
        this.price = price;

    }

    public Room() {
    } // need an empty constructor to work with json

}