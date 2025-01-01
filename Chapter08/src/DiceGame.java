import java.util.Random; // Importing the Random class to generate random numbers

public class DiceGame {

    /**
     * The main method, which serves as the entry point for the program.
     * Simulates a dice-rolling game and determines if the player wins or loses.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a random number generator to simulate dice rolls
        Random random = new Random();

        // Simulate rolling two dice
        int dice1 = random.nextInt(6) + 1; // Generates a random number between 1 and 6 for the first die
        int dice2 = random.nextInt(6) + 1; // Generates a random number between 1 and 6 for the second die

        // Calculate the total score by adding the values of the two dice
        int totalScore = dice1 + dice2;

        // Print the results of the dice rolls
        System.out.println("You rolled a " + dice1 + " and a " + dice2);
        System.out.println("Total score: " + totalScore);

        // Check if the total score is a winning number (7 or 11)
        if (totalScore == 7 || totalScore == 11) {
            // Print a congratulatory message for a winning roll
            System.out.println("Congratulations! You win!");
        } else {
            // Print a message encouraging the player to try again
            System.out.println("Sorry, you didn't win this time. Try again!");
        }
    }
}
