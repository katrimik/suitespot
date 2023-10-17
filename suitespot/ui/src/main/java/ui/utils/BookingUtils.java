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
            List<Room> foundRooms = rooms
            .stream().filter(r -> r.getId().equals(a.getRoomId()) || r.getId().equals(b.getRoomId()))
            .toList();

            if (foundRooms.size() != 2) {
                throw new IllegalArgumentException("room list does not contain all rooms in bookings");
            }

           Room roomA = foundRooms
            .stream().filter(r -> r.getId().equals(a.getRoomId())).findFirst().orElse(null);

           Room roomB = foundRooms
            .stream().filter(r -> r.getId().equals(b.getRoomId())).findFirst().orElse(null);

            return roomA.getRoomNumber() - roomB.getRoomNumber();
        }).toList();
    }

    public static List<Booking> sortOnCustomer(List<Booking> bookings, List<Customer> customers) {
        return bookings.stream().sorted((a, b) -> {
            List<Customer> foundCustomers = customers
            .stream().filter(c -> c.getId().equals(a.getCustomerId()) || c.getId().equals(b.getCustomerId()))
            .toList();

            if (foundCustomers.size() != 2) {
                throw new IllegalArgumentException("customer list does not contain all customers in bookings");
            }

           Customer customerA  = foundCustomers
            .stream().filter(c -> c.getId().equals(a.getCustomerId())).findFirst().orElse(null);

           Customer customerB = foundCustomers
            .stream().filter(c -> c.getId().equals(b.getCustomerId())).findFirst().orElse(null);

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
