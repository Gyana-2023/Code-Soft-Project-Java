import java.util.Random;
import java.util.Scanner;

public class NumberGame {
  public static void main(String[] args) {
    int score = 0;
    int rounds = 0;
    try (Scanner scanner = new Scanner(System.in)) {
      while (true) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(100) + 1;
        int userGuess = 0;

        System.out.println("Welcome to the guessing game!");
        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("You have 5 attempts to guess the number.");

        int attempts = 0;

        while (attempts < 5) {
          System.out.print("Enter your guess: ");
          userGuess = scanner.nextInt();

          attempts++;

          if (userGuess < randomNumber) {
            System.out.println("Too low! Try again.");
          } else if (userGuess > randomNumber) {
            System.out.println("Too high! Try again.");
          } else if (userGuess == randomNumber) {
            System.out.println(" Congratulations! You guessed it!");
            System.out.println("The number was indeed " + randomNumber);

            score++;
            break;
          }
        }

        if (attempts == 5 && userGuess != randomNumber) {
          System.out.println("Sorry, you didn't guess the number. The correct answer was " + randomNumber);
        }

        System.out.print("Do you want to play again? (yes/no): ");
        String response = scanner.next();

        if (response.equalsIgnoreCase("no")) {
          break;
        }

        rounds++;
      }
    }

    System.out.println("Your final score is " + score + " out of " + rounds);
  }
}