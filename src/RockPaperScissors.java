import java.util.Scanner;
import java.util.Random;

// database import statements
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] options = {"Rock", "Paper", "Scissors"};

        System.out.println("Welcome to Rock, Paper, Scissors!");

        System.out.printf("%s", options[0]);

        System.out.print("Enter your move (Rock, Paper, Scissors): ");
        String playerMove = scanner.nextLine();
        String computerMove = options[random.nextInt(3)];

        System.out.println("Computer chose: " + computerMove);

        if (playerMove.equalsIgnoreCase(computerMove)) {
            System.out.println("It's a tie!");
        } else if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equals("Scissors")) ||
                (playerMove.equalsIgnoreCase("Paper") && computerMove.equals("Rock")) ||
                (playerMove.equalsIgnoreCase("Scissors") && computerMove.equals("Paper"))) {
            System.out.println("You win!");
        } else {
            System.out.println("You lose!");
        }

        // Database URL, Username, and Password
        String url = "jdbc:mysql://localhost:3306/RockPaperScissors"; // Change database name
        String user = "root"; // Change username if necessary
        String password = "rootroot"; // Change your MySQL password

        // Establish the connection
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}