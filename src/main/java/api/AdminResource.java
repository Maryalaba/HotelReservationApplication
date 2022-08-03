package api;

import model.Customer;
import model.IRoom;

import java.util.Collection;
import java.util.List;

public class AdminResource {

    private static AdminResource SINGLE_INSTANCE = new AdminResource();

    private AdminResource(){
    }

    public static AdminResource getSingleInstance() {
        return SINGLE_INSTANCE;
    }

    public Customer getCustomer(String email){

    }

    public void addRoom(List<IRoom> rooms){

    }

    public Collection<IRoom> getAllRooms(){

    }

    public Collection<Customer> getAllCustomers(){

    }

    public void displayAllReservations(){

    }

}
