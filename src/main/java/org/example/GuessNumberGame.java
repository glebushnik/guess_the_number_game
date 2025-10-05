package org.example;

import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class GuessNumberGame {
    private final Path recordFile = Paths.get("record.txt");

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int best = loadBest();
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
                    if (best == 0 || attempts < best) {
                        best = attempts;
                        saveBest(best);
                        System.out.println("Новый рекорд: " + best);
                    } else {
                        System.out.println("Текущий рекорд: " + best);
                    }
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

    private int loadBest() {
        try {
            if (Files.exists(recordFile)) {
                String s = Files.readString(recordFile).trim();
                if (!s.isEmpty()) {
                    return Integer.parseInt(s);
                }
            }
        } catch (Exception ignored) {
        }
        return 0;
    }

    private void saveBest(int best) {
        try {
            Files.writeString(recordFile, Integer.toString(best),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ignored) {
        }
    }
}
