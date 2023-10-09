package core.model;

import java.util.HashSet;
import java.util.Set;
import java.time.LocalDate;

public class Room {
    private String id;
    private int roomNumber;
    private String typeId;
    private final static int MIN_ROOMNUMBER = 101;
    private final static int MAX_ROOMNUMBER = 599;
    private Set<LocalDate> bookedDates = new HashSet<LocalDate>();

    public Room(int roomNumber, String typeId) {
        this.setRoomNumber(roomNumber);
        this.setTypeId(typeId);
    }

    public Room() {
    } // need an empty constructor to work with json

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        if (roomNumber < MIN_ROOMNUMBER || roomNumber > MAX_ROOMNUMBER) {
            throw new IllegalArgumentException("Room number must be in interval [101, 599]");
        }
        this.roomNumber = roomNumber;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.bookedDates = new HashSet<>();
    }

    public Set<LocalDate> getBookedDates() {
        Set<LocalDate> tempBookedDates = new HashSet<LocalDate>(bookedDates);
        return tempBookedDates;
    }

    /**
     * Local date uses the ISO-8601 calendar system. (yy-mm-dd)
     * 
     * @param startDate could be 2023-9-10, and
     * @param endDate   could be 2023-9-20
     */
    public void bookRoom(LocalDate startDate, LocalDate endDate) {
        LocalDate date = startDate;

        if (!(isAvailable(date))) {
            throw new IllegalArgumentException("Date is aldready booked. Try another date.");
        }
        while (!date.isAfter(endDate)) {
            bookedDates.add(date);
            date = date.plusDays(1);
        }
    }

    public boolean isAvailable(LocalDate date) {
        return !bookedDates.contains(date);
    }

    public boolean isAvailable(LocalDate startDate, LocalDate endDate) {
        while (!startDate.isAfter(endDate)) {
            if (bookedDates.contains(startDate)) {
                return false;
            }
            startDate = startDate.plusDays(1);
        }
        return true;

    }

    @Override
    public String toString() {
        return "Room nr " + getRoomNumber();
    }
}
