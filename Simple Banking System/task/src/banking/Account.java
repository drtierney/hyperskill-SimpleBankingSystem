package banking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

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
        String cardNumber1 = IIN + generateAccountIdentifier();
        return cardNumber1 + generateCheckSum(cardNumber1);
    }

    private static Long generateAccountIdentifier() {
        return ThreadLocalRandom.current().nextLong(100000000L, 999999999L);
    }

    private static int generatePin() {
        return ThreadLocalRandom.current().nextInt(1000, 9999);
    }

    private static int generateCheckSum(String cardNumber1) {
        int sum = getLuhnAlgorithmSum(cardNumber1);
        return (100 - sum) % 10;
    }

    public static int getLuhnAlgorithmSum(String cardNumber1) {
        //Split cardNumber into List of digits
        String[] split = cardNumber1.split("\\B");
        List<Integer> digits = new ArrayList<>();
        for (String s : split) {
            Integer integer = Integer.valueOf(s);
            digits.add(integer);
        }

        // Multiply odd digits by 2
        for (int i = 0; i < digits.size(); i++) {
            if ((i + 1) % 2 != 0) {
                digits.set(i, digits.get(i) * 2);
            }
        }

        //Subtract 9 from numbers > 9
        IntStream.range(0, digits.size()).filter(i -> digits.get(i) > 9).forEach(i -> digits.set(i, digits.get(i) - 9));

        //Sum up control number
        int sum = digits.stream().mapToInt(i -> i).sum();
        return sum;
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
