package org.example;

import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean again = true;
        while (again) {
            int target = random.nextInt(100) + 1;
            int attempts = 0;
            System.out.println("Загадано число от 1 до 100.");
            while (true) {
                System.out.print("Введите число: ");
                int guess;
                if (scanner.hasNextInt()) {
                    guess = scanner.nextInt();
                } else {
                    String skip = scanner.next();
                    System.out.println("Введите целое число.");
                    continue;
                }
                attempts++;
                if (guess == target) {
                    System.out.println("Угадано за " + attempts + " попыток.");
                    break;
                } else if (guess < target) {
                    System.out.println("Больше");
                } else {
                    System.out.println("Меньше");
                }
            }
            System.out.print("Сыграть ещё раз? (y/n): ");
            String resp = scanner.next().trim().toLowerCase();
            again = resp.startsWith("y") || resp.equals("д") || resp.startsWith("да");
        }
    }
}