package banking;

import java.util.concurrent.ThreadLocalRandom;

import static banking.Bank.IIN;

public class Account {
    private String cardNumber;
    private int pin;
    private int balance;

    public Account() {

    }

    public static Account createNewAccount(){
        Account account = new Account();
        account.cardNumber = generateCardNumber();
        account.pin = generatePin();
        account.balance = 0;

        return account;
    }

    private static String generateCardNumber() {
        return IIN + generateAccountIdentifier();
    }

    private static Long generateAccountIdentifier() {
        return ThreadLocalRandom.current().nextLong(1000000000L, 9999999999L);
    }

    private static int generatePin() {
        return ThreadLocalRandom.current().nextInt(1000, 9999);
    }

    public void printBalance() {
        System.out.println("Balance: " + balance);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getPin() {
        return pin;
    }

    public int getBalance() {
        return balance;
    }
}
