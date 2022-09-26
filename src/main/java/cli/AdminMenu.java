package cli;

import api.AdminResource;
import model.*;
import java.util.Collection;
import java.util.Scanner;
public class AdminMenu {
    private static AdminResource adminResource = AdminResource.getSingleInstance();
    private static Scanner scanner = new Scanner(System.in);

    public static void displayAdminMenu(Scanner scanner) {
        showAdminMenu();
        int selection = -1;
        while (selection != 5) {
            try {
                selection = Integer.parseInt(scanner.nextLine());
                switch (selection) {
                    case 1:
                        viewAllCustomers();
                        showAdminMenu();
                        break;
                    case 2:
                        viewAllRooms();
                        showAdminMenu();
                        break;
                    case 3:
                        viewAllReservations();
                        showAdminMenu();
                        break;
                    case 4:
                        addRoom();
                        break;
                    case 5:
                        System.out.println("Going Back to the main Menu");
                        break;
                    default:
                        System.out.println("Please select 1 to 5");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Select from 1 to 5");
            }
        }
    }

    public static void showAdminMenu() {
        System.out.println("Admin Menu");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.println("========================================================");
        System.out.println("Please select a number for the menu option");
    }

    public static void addRoom() {
        System.out.println("Enter room number: ");
        String roomNumber = scanner.nextLine();

        System.out.println("Enter price per night: ");
        double roomPrice = 0.0;
        boolean priceAccepted = false;
        while (!priceAccepted) {
            try {
                roomPrice = Double.parseDouble(scanner.nextLine());
                priceAccepted = true;
            } catch (NullPointerException np) {
                System.out.println("Your input for room price is null, please try again");
                System.out.println("Enter price per night");
            } catch (NumberFormatException nfe) {
                System.out.println("Your input format for room price is not acceptable, please try again");
                System.out.println("Enter price per night");
            }
        }

        System.out.println("Enter room type: Single or Double");
        String roomToBeCreatedType = null;
        try {
            String roomType = singleOrDouble(scanner);
            if(roomType.equalsIgnoreCase(RoomType.SINGLE.name())){
                roomToBeCreatedType = roomType.toUpperCase();
            }else if (roomType.equalsIgnoreCase(RoomType.DOUBLE.name())) {
                roomToBeCreatedType = roomType.toUpperCase();
            } else {
                addRoom();
            }
            if(roomToBeCreatedType != null){
                adminResource.addRoom(new Room(roomNumber, roomPrice, RoomType.valueOf(roomToBeCreatedType)));
            }

            System.out.println("Would like to add another room? Enter Yes or No");
            String addAnotherRoom = yesOrNo(scanner);
            if (addAnotherRoom.equalsIgnoreCase("Yes")) {
                addRoom();
            }else {
                showAdminMenu();
            }
        } catch (IllegalArgumentException exception) {
            exception.getLocalizedMessage();
        }
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

    private static String singleOrDouble(Scanner scanner) {
        String input = scanner.nextLine();
        boolean check = true;
        while (check) {
            if (input.equalsIgnoreCase("Single") || input.equalsIgnoreCase("Double")) {
                check = false;
            } else {
                System.out.println("Invalid entry please enter Single or Double");
                input = scanner.nextLine();
            }
        }
        return input;
    }

    private static void viewAllRooms() {
        Collection<IRoom> hotelRooms = adminResource.getAllRooms();
        if (hotelRooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            for (IRoom room : hotelRooms) {
                System.out.println(room);
            }
        }
    }

    private static void viewAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customer found.");
        } else {
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }

    private static void viewAllReservations() {
        adminResource.displayAllReservations();
    }

}

