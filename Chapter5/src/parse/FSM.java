package parse;

public class FSM {

	// Define states for the Finite State Machine (FSM)
	// The FSM will transition between these states based on the input characters.
	private enum State {
		START,   // Initial state or when no number is being processed
		DIGIT,   // State when processing digits of a number
		DECIMAL, // State when processing the decimal point
		END      // End state (not actively used in this logic, but could be for future extensions)
	}

	// Parse a string to extract numbers using the Finite State Machine (FSM)
	public static void parseString(String input) {
		// Initialize FSM variables:
		// Start with the START state, and use a StringBuilder to build numbers.
		State state = State.START;
		StringBuilder buffer = new StringBuilder();

		// Iterate through each character of the input string
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i); // Get the current character

			// Transition based on current state and input character
			switch (state) {
			case START:
				// If the character is a digit, move to DIGIT state and append the digit to buffer
				if (Character.isDigit(ch)) {
					state = State.DIGIT;
					buffer.append(ch);
				} 
				// If the character is a decimal point, move to DECIMAL state
				else if (ch == '.') {
					state = State.DECIMAL;
					buffer.append(ch);
				}
				break;
			case DIGIT:
				// If the character is a digit, stay in DIGIT state and append to buffer
				if (Character.isDigit(ch)) {
					buffer.append(ch);
				} 
				// If a decimal point is encountered, switch to DECIMAL state
				else if (ch == '.') {
					state = State.DECIMAL;
					buffer.append(ch);
				} 
				// If any other character is encountered, output the current number and reset FSM
				else {
					System.out.println("Number: " + buffer.toString());
					buffer.setLength(0);  // Reset buffer for next number
					state = State.START;  // Transition back to START state
				}
				break;
			case DECIMAL:
				// If the character is a digit, stay in DECIMAL state and append to buffer
				if (Character.isDigit(ch)) {
					buffer.append(ch);
				} 
				// If any other character is encountered, output the current number and reset FSM
				else {
					System.out.println("Number: " + buffer.toString());
					buffer.setLength(0);  // Reset buffer for next number
					state = State.START;  // Transition back to START state
				}
				break;
			case END:
				// The END state is not actively used here, but it's included for completeness
				break;
			}
		}

		// If the FSM ends in DIGIT or DECIMAL state, output the last number in the buffer
		// This ensures the last number is printed even if it is at the end of the string
		if (state == State.DIGIT || state == State.DECIMAL) {
			System.out.println("Number: " + buffer.toString());
		}
	}

	public static void main(String[] args) {
		// Sample input string containing numbers to be extracted
		String input = "The price is $20.99 and quantity is 5.";

		// Parse the input string using FSM to extract numbers
		parseString(input);
	}
}
