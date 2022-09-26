package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private Map<String, IRoom> hotelRooms = new HashMap<>();
    private Collection<Reservation> listOfHotelReservations = new ArrayList<>();

    private Collection<Reservation> customerReservations = new ArrayList<>();

    private static final ReservationService SINGLE_INSTANCE = new ReservationService();

    private ReservationService() {

    }

    public static ReservationService getSingleInstance() {
        return SINGLE_INSTANCE;
    }

    public void addRoom(IRoom room) {
        if (!hotelRooms.containsKey(room.getRoomNumber())) {
            hotelRooms.put(room.getRoomNumber(), room);
        } else {
            System.out.println("This room has already been added.");
        }
    }

    public IRoom getARoom(String roomId) {
        return hotelRooms.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Map<String, Customer> customerList = CustomerService.getSingleInstance().customerList;
        Reservation reservation = null;
        try {
            if (customerList.containsKey(customer.getEmail())) {
                if (hotelRooms.containsKey(room.getRoomNumber())) {
                    if (inputRoomHasNotBeenBooked(room, checkInDate, checkOutDate)) {
                        reservation = new Reservation(customer, room, checkInDate, checkOutDate);
                        customerReservations.add(reservation);
                        listOfHotelReservations.add(reservation);
                        System.out.println("Room Booked Successfully");
                    } else {

                        System.out.println("Room is no longer available for booking. Kindly check below for the next availability.");
                        System.out.println(findAlternativeRooms(checkInDate, checkOutDate));
                    }
                } else {
                    System.out.println("room does not exist");
                }
            } else {
                System.out.println("Customer does not exist, please register!!");
            }
        } catch (NullPointerException exception) {
            exception.getMessage();
        }
        return reservation;
    }

    public boolean inputRoomHasNotBeenBooked(IRoom room, Date checkInDate, Date checkOutDate) {
        Collection<Reservation> alreadyBookedRoomList = getReservationListWithUniqueRoomNumber(room.getRoomNumber());
        boolean hasNotBeenBooked = true;
        if (hasBeenBookedReservation(alreadyBookedRoomList, checkInDate, checkOutDate)) {
            hasNotBeenBooked = false;
        }
        return hasNotBeenBooked;
    }

    private Collection<Reservation> getReservationListWithUniqueRoomNumber(String roomNumber) {
        Collection<Reservation> reservationWithSameRoomList = new ArrayList<>();
        for (Reservation reservation : listOfHotelReservations) {
            if (reservation.getRoom().getRoomNumber().equalsIgnoreCase(roomNumber)) {
                reservationWithSameRoomList.add(reservation);
            }
        }
        return reservationWithSameRoomList;
    }

    private boolean hasBeenBookedReservation(Collection<Reservation> alreadyBookedRoomList, Date checkInDate, Date checkOutDate) {
        boolean hasBeenBooked = false;
        for (Reservation reservation : alreadyBookedRoomList) {
            if (reservation.getCheckInDate().before(checkOutDate) && reservation.getCheckOutDate().after(checkInDate)) {
                hasBeenBooked = true;
            }
        }
        return hasBeenBooked;
    }

    public Collection<IRoom> getAllRooms() {
        return hotelRooms.values();
    }


    public Collection<IRoom> findRooms(Date checkIn, Date checkOut) {
        Collection<IRoom> availableRooms = new ArrayList<>();
        for (Reservation reservation : getAllHotelReservations()) {
            if (checkIn.after(reservation.getCheckOutDate()) && checkOut.before(reservation.getCheckOutDate())) {
                availableRooms.add(reservation.getRoom());
            }
        }
        return availableRooms;
    }

    public Collection<IRoom> findAlternativeRooms(Date checkIn, Date checkOut) {
        return findRooms(getDatePlus7Days(checkIn), getDatePlus7Days(checkOut));
    }

    private static Date getDatePlus7Days(Date date) {
        long dateTimeStampPlus7Days = date.getTime() + 7 * 24 * 60 * 60 * 1000;
        return new Date(dateTimeStampPlus7Days);
    }

    private Collection<Reservation> getAllHotelReservations() {
        Collection<Reservation> allReservations = new ArrayList<>();
        for (Reservation reservation : listOfHotelReservations) {
            allReservations.add(reservation);
        }
        return allReservations;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        Collection<Reservation> customersReserves = new ArrayList<>();
        for (Reservation reservation : listOfHotelReservations) {
            if (reservation.getCustomer().getEmail().equals(customer.getEmail())) {
                customersReserves.add(reservation);
            }
        }
        return customersReserves;
    }

    public void printAllReservation() {
        Collection<Reservation> reservations = getAllHotelReservations();
        if (reservations.isEmpty()) {
            System.out.println("No reservation yet");
        }
        for (Reservation reservation : reservations) {
            System.out.println("Room " + reservation.getRoom().getRoomNumber() + " booked by " +
                    reservation.getCustomer().getFirstName() + " " +
                    reservation.getCustomer().getLastName() + "." + " Checked In " +
                    reservation.getCheckInDate() + " and Checked Out: "
                    + reservation.getCheckOutDate());
        }
    }

    public void printAvailableRooms(Date checkIn, Date checkOut) {
        Collection<IRoom> availableRooms = findRooms(checkIn, checkOut);
        for (IRoom room : availableRooms) {
            System.out.println(room);
        }
    }
}


