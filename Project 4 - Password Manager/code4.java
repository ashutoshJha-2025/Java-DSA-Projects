import java.security.SecureRandom;
import java.util.*;

public class code4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, passManage> map = new HashMap<>();

        while (true) {
            System.out.println("\n==============================");
            System.out.println("Welcome to Password Manager");
            System.out.println("==============================");
            System.out.println("1. Add a new account");
            System.out.println("2. Search password by Site name");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addAccount(map, sc);
                    break;
                case 2:
                    searchbySiteName(map, sc);
                    break;
                case 3:
                    System.out.println("Exiting... Thank you for using Password Manager!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }

    private static void addAccount(HashMap<String, passManage> map, Scanner sc) {
        while (true) {
            System.out.print("\nEnter site name (or 0 to exit): ");
            String siteName = sc.nextLine();
            if (siteName.equals("0")) {
                return;
            }
            System.out.print("Enter G-mail: ");
            String gmail = sc.nextLine();

            System.out.print("Do you want to auto-generate a strong password? (yes/no): ");
            String choice = sc.nextLine();
            String pass;
            if (choice.equalsIgnoreCase("yes")) {
                pass = generateStrongPassword();
                System.out.println("Generated Password: " + pass);
            } else {
                System.out.print(
                        "Enter a strong password (at least 8 characters, including uppercase, lowercase, digit, and special character): ");
                pass = sc.nextLine();
            }

            map.put(siteName, new passManage(gmail, pass));
            System.out.println("Account added successfully!");
        }
    }

    private static String generateStrongPassword() {
        final String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        final String digits = "0123456789";
        final String specialChars = "!@#$%^&*()-_+=<>?";
        final String allChars = upperCase + lowerCase + digits + specialChars;

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Ensure at least one character from each category
        password.append(upperCase.charAt(random.nextInt(upperCase.length())));
        password.append(lowerCase.charAt(random.nextInt(lowerCase.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(specialChars.charAt(random.nextInt(specialChars.length())));

        // Fill the rest of the password length with random characters
        for (int i = 4; i < 8; i++) { // Minimum length of 16
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        // Shuffle the characters for randomness
        List<Character> passwordChars = new ArrayList<>();
        for (char c : password.toString().toCharArray()) {
            passwordChars.add(c);
        }
        Collections.shuffle(passwordChars);

        StringBuilder finalPassword = new StringBuilder();
        for (char c : passwordChars) {
            finalPassword.append(c);
        }

        return finalPassword.toString();
    }

    private static void searchbySiteName(HashMap<String, passManage> map, Scanner sc) {
        System.out.print("\nEnter site name (or 0 to exit): ");
        String siteName = sc.nextLine();
        if (siteName.equals("0")) {
            return;
        }
        if (map.containsKey(siteName)) {
            passManage temp = map.get(siteName);
            System.out.println("G-mail: " + temp.getEmail() + " | Password: " + temp.getPassword());
        } else {
            System.out.println("Password not found!");
        }
    }
}

class passManage {
    private String email;
    private String password;

    public passManage(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}