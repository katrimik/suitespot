package ui.utils;

import java.util.List;

import core.model.Booking;
import core.model.Customer;
import core.model.Room;

public class BookingUtils {

    public static List<Booking> filterOnRoomId(List<Booking> bookings, String roomId) {
        return bookings.stream().filter(b -> b.getRoomId().equals(roomId)).toList();
    }

    public static List<Booking> filterOnCustomerId(List<Booking> bookings, String customerId) {
        return bookings.stream().filter(b -> b.getCustomerId().equals(customerId)).toList();
    }

    public static List<Booking> sortOnRoom(List<Booking> bookings, List<Room> rooms) {
        return bookings.stream().sorted((a, b) -> {
            Room roomA = null;
            Room roomB = null;

            for (Room room : rooms) {

                if (room.getId().equals(a.getRoomId())) {
                    roomA = room;
                }
                
                if (room.getId().equals(b.getRoomId())) {
                    roomB = room;
                }

                if (roomA != null && roomB != null) {
                    break;
                }
            }

            if (roomA == null | roomB == null) {
                throw new IllegalArgumentException("room list does not contain all rooms in bookings");
            }

            return roomA.getRoomNumber() - roomB.getRoomNumber();
        }).toList();
    }

    public static List<Booking> sortOnCustomer(List<Booking> bookings, List<Customer> customers) {
        return bookings.stream().sorted((a, b) -> {
            Customer customerA = null;
            Customer customerB = null;

            for (Customer customer : customers) {
                if (customer.getId().equals(a.getCustomerId())) {
                    customerA = customer;
                }
                
                if (customer.getId().equals(b.getCustomerId())) {
                    customerB = customer;
                }

                if (customerA != null && customerB != null) {
                    break;
                }
            }

            if (customerA == null || customerB == null) {
                throw new IllegalArgumentException("customer list does not contain all customers in bookings");
            }

            return customerA.getFullName().compareTo(customerB.getFullName());
        }).toList();
    }

    public static List<Booking> sortOnFromDateNewestFirst(List<Booking> bookings) {
        return bookings
            .stream()
            .sorted((a, b) -> b.getFromDate().compareTo(a.getFromDate()))
            .toList();
    }
}
