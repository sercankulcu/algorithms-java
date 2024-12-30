import java.util.Random;

public class SimulatedAnnealing {

    // Constants for the simulated annealing algorithm
    static final double INITIAL_TEMPERATURE = 100; // Starting temperature
    static final double COOLING_RATE = 0.003; // Rate at which the temperature decreases
    static final int MAX_ITERATIONS = 100; // Maximum number of iterations

    /**
     * Objective function: Computes the value of the function f(x) = x^2.
     * The goal is to find the minimum value of this function.
     * @param x The input value.
     * @return The value of f(x) = x^2.
     */
    static double f(double x) {
        return x * x;
    }

    /**
     * Computes the probability of accepting a worse solution based on the current energy,
     * the new energy, and the current temperature.
     * @param energy The energy of the current solution.
     * @param newEnergy The energy of the new solution.
     * @param temperature The current temperature.
     * @return The probability of accepting the new solution.
     */
    static double acceptanceProbability(double energy, double newEnergy, double temperature) {
        if (newEnergy < energy) { // If the new solution is better, always accept it
            return 1.0;
        }
        // If the new solution is worse, accept it with a probability based on the energy difference
        return Math.exp((energy - newEnergy) / temperature);
    }

    /**
     * Performs the simulated annealing algorithm to find an approximate minimum of the function f(x).
     * @return The solution that approximates the minimum of the function.
     */
    static double simulatedAnnealing() {
        Random random = new Random();

        // Initialize a random starting solution in the range [-5, 5]
        double currentSolution = random.nextDouble() * 10 - 5;
        double currentEnergy = f(currentSolution); // Compute the energy of the current solution
        double temperature = INITIAL_TEMPERATURE; // Initialize temperature

        // Main loop for simulated annealing
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            // Generate a new random solution in the range [-5, 5]
            double newSolution = random.nextDouble() * 10 - 5;
            double newEnergy = f(newSolution); // Compute the energy of the new solution

            // Decide whether to accept the new solution based on acceptance probability
            if (acceptanceProbability(currentEnergy, newEnergy, temperature) > random.nextDouble()) {
                currentSolution = newSolution; // Accept the new solution
                currentEnergy = newEnergy; // Update the current energy
            }

            // Cool down the temperature using the cooling rate
            temperature *= 1 - COOLING_RATE;
        }

        // Return the final solution as the approximate minimum
        return currentSolution;
    }

    public static void main(String[] args) {
        // Execute the simulated annealing algorithm to find the minimum
        double solution = simulatedAnnealing();
        System.out.println("Approximate minimum of the function: " + solution);
        System.out.println("Function value at the approximate minimum: " + f(solution));
    }
}
