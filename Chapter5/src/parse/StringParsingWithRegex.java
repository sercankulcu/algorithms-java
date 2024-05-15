package parse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParsingWithRegex {

	public static void main(String[] args) {
		// Sample input string
		String input = "Name: John Doe, Age: 30, Email: john.doe@example.com";

		// Define regular expressions for parsing different fields
		String nameRegex = "Name: (.*?),";
		String ageRegex = "Age: (\\d+),";
		String emailRegex = "Email: (.*?)$";

		// Create Pattern objects for each regular expression
		Pattern namePattern = Pattern.compile(nameRegex);
		Pattern agePattern = Pattern.compile(ageRegex);
		Pattern emailPattern = Pattern.compile(emailRegex);

		// Create Matcher objects for each pattern
		Matcher nameMatcher = namePattern.matcher(input);
		Matcher ageMatcher = agePattern.matcher(input);
		Matcher emailMatcher = emailPattern.matcher(input);

		// Extract and print the name, age, and email from the input string
		if (nameMatcher.find()) {
			String name = nameMatcher.group(1);
			System.out.println("Name: " + name);
		}

		if (ageMatcher.find()) {
			int age = Integer.parseInt(ageMatcher.group(1));
			System.out.println("Age: " + age);
		}

		if (emailMatcher.find()) {
			String email = emailMatcher.group(1);
			System.out.println("Email: " + email);
		}
	}
}
