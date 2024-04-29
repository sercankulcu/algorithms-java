import java.util.Random;

public class SimulatedAnnealing {

    static final double INITIAL_TEMPERATURE = 100;
    static final double COOLING_RATE = 0.003;
    static final int MAX_ITERATIONS = 100;

    static double f(double x) {
        return x * x;
    }

    static double acceptanceProbability(double energy, double newEnergy, double temperature) {
        if (newEnergy < energy) {
            return 1.0;
        }
        return Math.exp((energy - newEnergy) / temperature);
    }

    static double simulatedAnnealing() {
        Random random = new Random();
        double currentSolution = random.nextDouble() * 10 - 5; // Initial random solution between -5 and 5
        double currentEnergy = f(currentSolution);
        double temperature = INITIAL_TEMPERATURE;

        for (int i = 0; i < MAX_ITERATIONS; i++) {
            double newSolution = random.nextDouble() * 10 - 5; // Generate a new random solution
            double newEnergy = f(newSolution);

            if (acceptanceProbability(currentEnergy, newEnergy, temperature) > random.nextDouble()) {
                currentSolution = newSolution;
                currentEnergy = newEnergy;
            }

            temperature *= 1 - COOLING_RATE; // Cooling down the temperature
        }

        return currentSolution;
    }

    public static void main(String[] args) {
        double solution = simulatedAnnealing();
        System.out.println("Approximate minimum of the function: " + solution);
        System.out.println("Function value at the approximate minimum: " + f(solution));
    }
}
