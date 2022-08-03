package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ReservationService {

//todo: to store and retrieve a reservation
    List<Reservation> reservationList = new ArrayList<>();
    private static ReservationService SINGLE_INSTANCE = new ReservationService();

    private ReservationService(){
    }

    public static ReservationService getSingleInstance() {
        return SINGLE_INSTANCE;
    }

    public void addRoom(IRoom room){

    }

    public IRoom getARoom(String roomId){

    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){

    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){

    }

    public Collection<Reservation> getCustomersReservation(Customer customer){

    }

    public void printAllReservation(){

    }


}


