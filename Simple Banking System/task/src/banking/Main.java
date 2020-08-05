package banking;

import java.util.Scanner;



public class Main {
    static Bank bank = new Bank();
    private static boolean isLoggedIn;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (!isLoggedIn) {
                bank.initMenu();
            } else {
                bank.loginMenu();
            }
            int action = Integer.parseInt(scanner.nextLine());
            chooseAction(action);
        }
    }

    private static void chooseAction(int action) {
        switch (action) {
            case 1:
                if (!isLoggedIn) {
                    bank.createAccount();
                } else {
                    bank.printBalance();
                }
                break;
            case 2:
                if (!isLoggedIn) {
                    isLoggedIn = bank.loginAccount();
                } else {
                    isLoggedIn = bank.logoutAccount();
                }
                break;
            case 0:
                bank.exit();
                break;
            default:
                System.out.println("Wrong option");
        }
    }
}
