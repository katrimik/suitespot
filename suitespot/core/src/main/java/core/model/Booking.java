package core.model;

import java.time.LocalDate;

public class Booking {
    private String id;
    private String roomId;
    private String customerId;

    private LocalDate fromDate;
    private LocalDate toDate;

    public Booking(String roomId, String customerId, LocalDate fromDate, LocalDate toDate) {
        this.setRoomId(roomId);
        this.setCustomerId(customerId);
        this.setDates(fromDate, toDate);
    }

    public Booking(String roomId, String customerId, LocalDate fromDate, LocalDate toDate, String id) {
        this(roomId, customerId, fromDate, toDate);
        this.id = id;
    }

    public Booking() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setDates(LocalDate fromDate, LocalDate toDate) {

        // check that toDate is in the future compared to fromDate
        int dateComparison = fromDate.compareTo(toDate);
        if (dateComparison >= 0) {
            throw new IllegalArgumentException("toDate has to be greater then fromDate (aka. in the future)");
        }

        this.fromDate = fromDate;
        this.toDate = toDate;
    }
}
