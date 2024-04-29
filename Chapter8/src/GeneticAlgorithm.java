import java.util.Arrays;
import java.util.Random;

public class GeneticAlgorithm {

	static final int POPULATION_SIZE = 100;
	static final int MAX_GENERATIONS = 100;
	static final double MUTATION_RATE = 0.05;

	static double f(double x) {
		return -x * x + 5 * x + 10; // Function to maximize: -x^2 + 5x + 10
	}

	static double[] initializePopulation() {
		Random random = new Random();
		double[] population = new double[POPULATION_SIZE];
		for (int i = 0; i < POPULATION_SIZE; i++) {
			population[i] = random.nextDouble() * 10; // Random initial solutions between 0 and 10
		}
		return population;
	}

	static double fitness(double x) {
		return f(x); // Fitness function is the same as the objective function in this example
	}

	static double crossover(double parent1, double parent2) {
		// Single-point crossover
		Random random = new Random();
		int crossoverPoint = random.nextInt(32); // Assume a 32-bit floating-point representation
		long mask = (1L << crossoverPoint) - 1;
		long child = ((Double.doubleToLongBits(parent1) & mask) |
				(Double.doubleToLongBits(parent2) & ~mask));
		return Double.longBitsToDouble(child);
	}

	static double mutate(double x) {
		// Flip a bit with a certain mutation rate
		Random random = new Random();
		long bits = Double.doubleToLongBits(x);
		for (int i = 0; i < 64; i++) {
			if (random.nextDouble() < MUTATION_RATE) {
				bits ^= (1L << i);
			}
		}
		return Double.longBitsToDouble(bits);
	}

	static double geneticAlgorithm() {
		double[] population = initializePopulation();
		for (int generation = 0; generation < MAX_GENERATIONS; generation++) {
			// Evaluate fitness of each individual in the population
			double maxFitness = Double.NEGATIVE_INFINITY;
			double bestIndividual = 0;
			for (double individual : population) {
				double fitness = fitness(individual);
				if (fitness > maxFitness) {
					maxFitness = fitness;
					bestIndividual = individual;
				}
			}
			// Select parents for reproduction (tournament selection)
			Random random = new Random();
			double parent1 = population[random.nextInt(POPULATION_SIZE)];
			double parent2 = population[random.nextInt(POPULATION_SIZE)];
			// Crossover and mutate to create new offspring
			double offspring = crossover(parent1, parent2);
			offspring = mutate(offspring);
			// Replace worst individual in the population with the new offspring
			int worstIndex = 0;
			double minFitness = Double.POSITIVE_INFINITY;
			for (int i = 0; i < POPULATION_SIZE; i++) {
				double fitness = fitness(population[i]);
				if (fitness < minFitness) {
					minFitness = fitness;
					worstIndex = i;
				}
			}
			population[worstIndex] = offspring;
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
		return bestIndividual;
	}

	public static void main(String[] args) {
		double solution = geneticAlgorithm();
		System.out.println("Approximate maximum of the function: " + solution);
		System.out.println("Function value at the approximate maximum: " + f(solution));
	}
}
