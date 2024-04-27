
public class StringParsingFSM {

	// Define states for the Finite State Machine
	private enum State {
		START, DIGIT, DECIMAL, END
	}

	// Parse a string to extract numbers using Finite State Machine (FSM)
	public static void parseString(String input) {
		// Initialize FSM variables
		State state = State.START;
		StringBuilder buffer = new StringBuilder();

		// Iterate through each character of the input string
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);

			// Transition based on current state and input character
			switch (state) {
			case START:
				if (Character.isDigit(ch)) {
					state = State.DIGIT;
					buffer.append(ch);
				} else if (ch == '.') {
					state = State.DECIMAL;
					buffer.append(ch);
				}
				break;
			case DIGIT:
				if (Character.isDigit(ch)) {
					buffer.append(ch);
				} else if (ch == '.') {
					state = State.DECIMAL;
					buffer.append(ch);
				} else {
					// Output the number and reset buffer
					System.out.println("Number: " + buffer.toString());
					buffer.setLength(0);
					state = State.START;
				}
				break;
			case DECIMAL:
				if (Character.isDigit(ch)) {
					buffer.append(ch);
				} else {
					// Output the number and reset buffer
					System.out.println("Number: " + buffer.toString());
					buffer.setLength(0);
					state = State.START;
				}
				break;
			case END:
				// FSM has reached the end state
				break;
			}
		}

		// Output the final number if FSM ends in DIGIT or DECIMAL state
		if (state == State.DIGIT || state == State.DECIMAL) {
			System.out.println("Number: " + buffer.toString());
		}
	}

	public static void main(String[] args) {
		// Sample input string containing numbers
		String input = "The price is $20.99 and quantity is 5.";

		// Parse the input string using FSM to extract numbers
		parseString(input);
	}
}
