package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;
import java.util.Collection;
import java.util.Date;

public class HotelResource {
    CustomerService customerService = CustomerService.getSingleInstance();
    ReservationService reservationService = ReservationService.getSingleInstance();
    private static HotelResource SINGLE_INSTANCE = new HotelResource();

    private HotelResource() {
    }

    public static HotelResource getSingleInstance() {
        return SINGLE_INSTANCE;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(firstName, lastName, email);
    }

    public IRoom getRoom(String roomNumber) {
       return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.getCustomersReservation(customer);
    }

    public Collection<IRoom> findAvailableRooms(Date checkIn, Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }

    public Collection<IRoom> findAlternativeRooms(Date checkIn, Date checkOut){
        return reservationService.findAlternativeRooms(checkIn, checkOut);
    }

    public void printAvailableRooms(Date checkIn, Date checkOut){
        reservationService.printAvailableRooms(checkIn, checkOut);
    }

}



