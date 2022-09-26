package cli;

import java.util.Scanner;

public class MainMenuDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainMenu.displayMainMenu(scanner);
        if (scanner != null)
            scanner.close();
    }
}
