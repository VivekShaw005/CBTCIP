package com.company;
import java.util.*;

public class Online_Examination {
    private static Map<String, String> users = new HashMap<>();
    private static String currentUser = null;
    private static Scanner scanner = new Scanner(System.in);
    private static String[] questions = {
            "Q1: Water vapour is?\n1. A gas\n2. A cloud droplet\n3. A rain droplet\n4. A snowflake",
            "Q2: which city is known as 'Electronic City of India'?\n1. Mumbai\n2. Hyderabad\n3. Guragon\n4. Bangalore",
            "Q3: First train started in India?\n1. 1851\n2. 1852\n3. 1853\n4. 1854",
    };
    private static int[] correctAnswers = {1, 4, 3};
    private static int[] userAnswers = new int[questions.length];
    private static Timer timer = new Timer();

    public static void main(String[] args) {
        users.put("admin", "admin123");
        while (true) {
            System.out.println("1. Login\n2. Register\n3. Exit");
            switch (scanner.nextInt()) {
                case 1 -> login();
                case 2 -> register();
                case 3 -> System.exit(0);
            }
        }
    }
    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        if (users.getOrDefault(username, "").equals(scanner.next())) {
            currentUser = username;
            System.out.println("Login successful!");
            userMenu();
        } else {
            System.out.println("Invalid credentials.");
        }
    }
    private static void register() {
        System.out.print("Enter new username: ");
        String username = scanner.next();
        if (users.containsKey(username)) {
            System.out.println("Username already exists.");
        } else {
            System.out.print("Enter new password: ");
            users.put(username, scanner.next());
            System.out.println("Registration successful!");
        }
    }
    private static void userMenu() {
        while (currentUser != null) {
            System.out.println("1. Update Profile\n2. Update Password\n3. Start Exam\n4. Logout");
            switch (scanner.nextInt()) {
                case 1 -> updateProfile();
                case 2 -> updatePassword();
                case 3 -> startExam();
                case 4 -> currentUser = null;
            }
        }
    }
    private static void updateProfile() {
        System.out.print("Enter new username: ");
        String newUsername = scanner.next();
        if (users.containsKey(newUsername)) {
            System.out.println("Username already exists.");
        } else {
            users.put(newUsername, users.remove(currentUser));
            currentUser = newUsername;
            System.out.println("Profile updated successfully!");
        }
    }
    private static void updatePassword() {
        System.out.print("Enter new password: ");
        users.put(currentUser, scanner.next());
        System.out.println("Password updated successfully!");
    }
    private static void startExam() {
        timer.schedule(new TimerTask() {
            public void run() { autoSubmit(); }
        }, 60000);

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            userAnswers[i] = scanner.nextInt();
        }
        timer.cancel();
        submitExam();
    }
    private static void autoSubmit() {
        System.out.println("\nTime's up! Auto-submitting your answers...");
        submitExam();
    }
    private static void submitExam() {
        int score = 0;
        for (int i = 0; i < questions.length; i++) {
            if (userAnswers[i] == correctAnswers[i]) score++;
        }
        System.out.println("Exam submitted. Your score: " + score + "/" + questions.length);
    }
}