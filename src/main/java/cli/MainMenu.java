package cli;

import api.HotelResource;
import model.IRoom;
import model.Reservation;
import org.apache.commons.lang3.time.DateUtils;
import service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;


public class MainMenu {
    public static void displayMainMenu(Scanner scanner) {
        showMainMenu();
        int selection = -1;
        while (selection != 5) {
            try {
                selection = Integer.parseInt(scanner.nextLine());
                switch (selection) {
                    case 1:
                        findAndReserveRoom();
                        break;
                    case 2:
                        seeMyReservation();
                        showMainMenu();
                        break;
                    case 3:
                        createAccount();
                        showMainMenu();
                        break;
                    case 4:
                        AdminMenu.displayAdminMenu(scanner);
                        showMainMenu();
                    case 5:
                        System.out.println("Exit from menu now");
                        break;
                    default:
                        System.out.println("Please select 1 to 5");
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Please select from 1 to 5 only. ");
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("Welcome to the Hotel Reservation Application");
        System.out.println("========================================================");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("========================================================");
        System.out.println("Please select a number for the menu option");
    }

    private static void findAndReserveRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to book a room? Please enter Yes or No");
        try {
            String bookARoom = yesOrNo(scanner);
            if (bookARoom.equalsIgnoreCase("Yes")) {
                System.out.println("Do you have an account with us? Please enter Yes or No");
                String haveAnAccount = yesOrNo(scanner);

                if (haveAnAccount.equalsIgnoreCase("Yes")) {
                    System.out.println("Kindly enter your email in this format: name@domain.com");
                    String customerEmail = scanner.nextLine();

                    if (HotelResource.getSingleInstance().getCustomer(customerEmail) == null) {
                        System.out.println("Account does not exist. Kindly create an account");
                        createAccount();
                        showMainMenu();
                    } else {
                        System.out.println("Kindly check the list of rooms in the hotel");
                        System.out.println(ReservationService.getSingleInstance().getAllRooms());

                        System.out.println("What room number would you like to book?");
                        String roomNumber = scanner.nextLine();

                        IRoom room = HotelResource.getSingleInstance().getRoom(roomNumber);

                        System.out.println("Enter Check-In Date in this format: yyyy-MM-dd");
                        Date checkIn = getInputDate();

                        System.out.println("Enter Check-Out Date in this format: yyyy-MM-dd");
                        Date checkOut = getInputDate();

                        Reservation reservation = HotelResource.getSingleInstance().bookARoom(customerEmail, room, checkIn, checkOut);
                        System.out.println(reservation);
                        showMainMenu();
                    }
                } else {
                    System.out.println("Please kindly create an account. ");
                    createAccount();
                    findAndReserveRoom();
                }
            } else {
                showMainMenu();
            }
        } catch (IllegalArgumentException exception) {
            exception.getLocalizedMessage();
        }
    }

    private static Date getInputDate() {
        Date parsedDate = null;
        String input = "";
        Scanner scanner = new Scanner(System.in);
        try {
            input = scanner.nextLine();
            parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(input);
            boolean check = true;

            while (check) {
                if (DateUtils.isSameDay(parsedDate, new Date()) || parsedDate.compareTo(new Date()) > 0) {
                    check = false;
                } else {
                    System.out.println("Sorry, you cannot book a past date. Kindly enter another date");
                    input = scanner.nextLine();
                    parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(input);
                }
            }
        } catch (DateTimeParseException | ParseException exception) {
            System.out.println("Error: Invalid date.");
            System.out.println("Enter Date in this format: yyyy-MM-dd");
        }
        return parsedDate;
    }

    private static String yesOrNo(Scanner scanner) {
        String input = scanner.nextLine();
        boolean check = true;
        while (check) {
            if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("no")) {
                check = false;
            } else {
                System.out.println("Invalid entry please enter yes or no");
                input = scanner.nextLine();
            }
        }
        return input;
    }

    private static void seeMyReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Kindly Enter your Email in this format: name@domain.com");
        String customerEmail = scanner.nextLine();

        Collection<Reservation> myReservations = HotelResource.getSingleInstance().getCustomersReservations(customerEmail);
        if (myReservations.isEmpty()) {
            System.out.println("No reservation found. ");
        } else {
            for (Reservation reservation : myReservations) {
                System.out.println(reservation);
            }
        }
    }

    private static void createAccount() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter your Email in this format: name@domain.com");
            String email = scanner.nextLine();

            System.out.println("First Name:");
            String firstName = scanner.nextLine();

            System.out.println("Last Name:");
            String lastName = scanner.nextLine();

            if (firstName != null && lastName != null && email != null) {
                HotelResource.getSingleInstance().createACustomer(email, firstName, lastName);
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
}
