package test;

import model.*;
import java.util.Calendar;
import java.util.Date;

public class Tester {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2022);
        cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 25);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date checkIn = cal.getTime();

        cal.set(Calendar.DAY_OF_MONTH, 29);
        Date checkOut = cal.getTime();

        Room room = new Room("1", 20000.0, RoomType.SINGLE);
        FreeRoom freeRoom = new FreeRoom("2", RoomType.SINGLE);
        Customer customer = new Customer("Mary", "Alaba", "mary@gmail.com");
        Reservation reservation = new Reservation(customer, room, checkIn, checkOut);

        System.out.println(room);
        System.out.println(freeRoom);
        System.out.println(customer);
        System.out.println(reservation);
    }

}