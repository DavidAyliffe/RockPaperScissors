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
        String winner = "";
        String[] options = {"Rock", "Paper", "Scissors"};

        System.out.println("Welcome to Rock, Paper, Scissors!");

        System.out.printf("%s", options[0]);

        System.out.print("Enter your move (Rock, Paper, Scissors): ");
        String playerMove = scanner.nextLine();
        String computerMove = options[random.nextInt(3)];

        System.out.println("Computer chose: " + computerMove);

        if (playerMove.equalsIgnoreCase(computerMove)) {
            System.out.println("It's a tie!");
            winner = "No winner";
        } else if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equals("Scissors")) ||
                (playerMove.equalsIgnoreCase("Paper") && computerMove.equals("Rock")) ||
                (playerMove.equalsIgnoreCase("Scissors") && computerMove.equals("Paper"))) {
            System.out.println("You win!");
            winner = "Player";
        } else {
            System.out.println("You lose!");
            winner = "Computer";
        }

        // Database URL, Username, and Password

        //create database if not exists RockPaperScissors;
        //use RockPaperScissors;
        //create table if not exists rps
        //(
        //	  winner varchar(10),
        //    computer_choice varchar(10),
        //    player_choice varchar(10),
        //    match_time	TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        //);
        String url = "jdbc:mysql://localhost:3306/RockPaperScissors"; // Change database name
        String user = "root"; // Change username if necessary
        String password = "rootroot"; // Change your MySQL password

        // Establish the connection
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connection successful!");

            try {
                PreparedStatement insert_statement;
                insert_statement = conn.prepareStatement("insert into RockPaperScissors.rps(winner, computer_choice, player_choice) values (?, ?, ?) ");

                insert_statement.setString(1, winner );
                insert_statement.setString(2, computerMove );
                insert_statement.setString(3, playerMove );

                insert_statement.executeUpdate();
            }
            catch ( SQLException e)
            {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}