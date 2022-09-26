package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
public class AdminResource {
    private CustomerService customerService = CustomerService.getSingleInstance();
    private ReservationService reservationService = ReservationService.getSingleInstance();

    private static final AdminResource SINGLE_INSTANCE = new AdminResource();

    private AdminResource(){
    }

    public static AdminResource getSingleInstance() {
        return SINGLE_INSTANCE;
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void addRoom(IRoom room){
        reservationService.addRoom(room);
    }

    public Collection<IRoom> getAllRooms(){
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    public void displayAllReservations(){
        reservationService.printAllReservation();
    }

}
