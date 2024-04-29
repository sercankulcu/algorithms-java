import java.util.Random;

public class DiceGame {

	public static void main(String[] args) {
		// Create a random number generator
		Random random = new Random();

		// Simulate rolling two dice
		int dice1 = random.nextInt(6) + 1; // Generates a random number between 1 and 6
		int dice2 = random.nextInt(6) + 1; // Generates a random number between 1 and 6

		// Calculate the total score
		int totalScore = dice1 + dice2;

		// Print the result
		System.out.println("You rolled a " + dice1 + " and a " + dice2);
		System.out.println("Total score: " + totalScore);

		// Check if it's a winning roll (total score is 7 or 11)
		if (totalScore == 7 || totalScore == 11) {
			System.out.println("Congratulations! You win!");
		} else {
			System.out.println("Sorry, you didn't win this time. Try again!");
		}
	}
}
