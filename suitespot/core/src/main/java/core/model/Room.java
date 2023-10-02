package core.model;

import java.util.HashSet;
import java.util.Set;
import java.time.LocalDate;


public class Room {
    private String id;
    private int roomNumber;
    private String typeId;
    private String availability;
    private int price;
    private final static int MIN_ROOMNUMBER = 101;
    private final static int MAX_ROOMNUMBER = 600;
    private Set<LocalDate> bookedDates = new HashSet<LocalDate>();

    public Room(int roomNumber, String typeId, String availability, int price) {
        this.roomNumber = roomNumber;
        this.typeId = typeId;
        this.availability = availability;
        this.price = price;

    }

    public Room() {
    } // need an empty constructor to work with json

    public String getRoomId() {
        return id;
    }

    public void setRoomId(String id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        if(roomNumber < MIN_ROOMNUMBER || roomNumber > MAX_ROOMNUMBER){
            throw new IllegalArgumentException("Not a valid roomnumber");
        }
        this.roomNumber = roomNumber;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getAvailability() { //get from roommanager
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
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
    
        public Room(int roomNumber) {
            this.roomNumber = roomNumber;
            this.bookedDates = new HashSet<>();
        }
    
        public Set<LocalDate> getBookedDates() {
            return bookedDates;
        }
    
        /**
         * Local date uses the ISO-8601 calendar system. (yy-mm-dd)
         * 
         * @param startDate could be 2023-9-10, and
         * @param endDate   could be 2023-9-20
         */
        public void bookRoom(LocalDate startDate, LocalDate endDate) {
            LocalDate date = startDate;
    
            if (!(isAvailble(date))) {
                throw new IllegalArgumentException("Date is aldready booked. Try another date.");
            }
            while (!date.isAfter(endDate)) {
                bookedDates.add(date);
                date = date.plusDays(1);
            }
        }       
    
        public boolean isAvailble(LocalDate date) {
            return !bookedDates.contains(date);
        }
    
        public boolean isAvailble(LocalDate startDate, LocalDate endDate) {
            while (!startDate.isAfter(endDate)) {
                if (bookedDates.contains(startDate)) {
                    throw new IllegalArgumentException("Room is booked.");
                }
                startDate = startDate.plusDays(1);
            }
            return true;
    
        }    
    
        @Override
        public String toString() {
            return "Room nr " + getRoomNumber() + " is booked: " + getBookedDates() + " ";
        }
        
    
        public static void main(String[] args) {
            Room single1 = new Room(1);
            System.out.println(single1);
    
            LocalDate startDate = LocalDate.of(2023, 9, 25);
            LocalDate endDate = LocalDate.of(2023, 9, 30);
    
            single1.bookRoom(startDate, endDate);
    
            System.out.println(single1);
            System.out.println("Room booked for the following dates:");
    
            for (LocalDate date : single1.bookedDates) {
                System.out.println(date);
            }
    
            single1.bookRoom(LocalDate.of(2023, 9, 27), LocalDate.of(2023, 9, 28));
        }
    //hvordan sette inn variablene?
    }
