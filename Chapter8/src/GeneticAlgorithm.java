import java.util.Arrays; // Importing Arrays class for utility methods (not directly used in this code)
import java.util.Random; // Importing Random class for random number generation

public class GeneticAlgorithm {

    // Define constants for the genetic algorithm
    static final int POPULATION_SIZE = 100; // Number of individuals in the population
    static final int MAX_GENERATIONS = 100; // Maximum number of generations to evolve
    static final double MUTATION_RATE = 0.05; // Probability of mutation for each bit

    /**
     * Objective function to maximize: -x^2 + 5x + 10
     * 
     * @param x the input variable
     * @return the computed value of the function
     */
    static double f(double x) {
        return -x * x + 5 * x + 10;
    }

    /**
     * Initializes the population with random values between 0 and 10.
     * 
     * @return an array representing the initial population
     */
    static double[] initializePopulation() {
        Random random = new Random();
        double[] population = new double[POPULATION_SIZE];
        for (int i = 0; i < POPULATION_SIZE; i++) {
            population[i] = random.nextDouble() * 10; // Random values between 0 and 10
        }
        return population;
    }

    /**
     * Fitness function, which is equivalent to the objective function in this example.
     * 
     * @param x the input variable
     * @return the fitness value
     */
    static double fitness(double x) {
        return f(x);
    }

    /**
     * Performs single-point crossover between two parent solutions.
     * 
     * @param parent1 the first parent
     * @param parent2 the second parent
     * @return a new offspring generated by crossover
     */
    static double crossover(double parent1, double parent2) {
        Random random = new Random();
        int crossoverPoint = random.nextInt(32); // Single-point crossover on a 32-bit representation
        long mask = (1L << crossoverPoint) - 1; // Create a mask for crossover
        long child = ((Double.doubleToLongBits(parent1) & mask) | // Combine bits of parent1
                (Double.doubleToLongBits(parent2) & ~mask)); // Combine bits of parent2
        return Double.longBitsToDouble(child); // Convert back to double
    }

    /**
     * Performs mutation on an individual by flipping bits with a certain mutation rate.
     * 
     * @param x the input solution
     * @return the mutated solution
     */
    static double mutate(double x) {
        Random random = new Random();
        long bits = Double.doubleToLongBits(x); // Get the binary representation of the double
        for (int i = 0; i < 64; i++) { // Iterate over 64 bits (double representation)
            if (random.nextDouble() < MUTATION_RATE) { // Mutate with a probability
                bits ^= (1L << i); // Flip the i-th bit
            }
        }
        return Double.longBitsToDouble(bits); // Convert back to double
    }

    /**
     * Implements the genetic algorithm to approximate the maximum of the objective function.
     * 
     * @return the best solution found by the genetic algorithm
     */
    static double geneticAlgorithm() {
        double[] population = initializePopulation(); // Initialize the population
        for (int generation = 0; generation < MAX_GENERATIONS; generation++) {
            // Find the individual with the highest fitness in the current generation
            double maxFitness = Double.NEGATIVE_INFINITY;
            for (double individual : population) {
                double fitness = fitness(individual);
                if (fitness > maxFitness) {
                    maxFitness = fitness;
                }
            }
            // Select two parents using tournament selection
            Random random = new Random();
            double parent1 = population[random.nextInt(POPULATION_SIZE)];
            double parent2 = population[random.nextInt(POPULATION_SIZE)];
            // Generate offspring using crossover and mutation
            double offspring = crossover(parent1, parent2);
            offspring = mutate(offspring);
            // Replace the worst individual with the new offspring
            int worstIndex = 0;
            double minFitness = Double.POSITIVE_INFINITY;
            for (int i = 0; i < POPULATION_SIZE; i++) {
                double fitness = fitness(population[i]);
                if (fitness < minFitness) {
                    minFitness = fitness;
                    worstIndex = i;
                }
            }
            population[worstIndex] = offspring; // Replace worst individual
        }
        // Find the best individual in the final population
        double maxFitness = Double.NEGATIVE_INFINITY;
        double bestIndividual = 0;
        for (double individual : population) {
            double fitness = fitness(individual);
            if (fitness > maxFitness) {
                maxFitness = fitness;
                bestIndividual = individual;
            }
        }
        return bestIndividual; // Return the best solution
    }

    /**
     * Main method to execute the genetic algorithm and print the results.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        double solution = geneticAlgorithm(); // Run the genetic algorithm
        System.out.println("Approximate maximum of the function: " + solution);
        System.out.println("Function value at the approximate maximum: " + f(solution));
    }
}
