package parse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuiltInRegex {

    public static void main(String[] args) {
        // Sample input string that contains the data to be parsed
        String input = "Name: John Doe, Age: 30, Email: john.doe@example.com";

        // Define regular expressions for parsing different fields (Name, Age, Email)
        // The regular expressions match specific patterns in the input string.
        String nameRegex = "Name: (.*?),";  // Match the name field, non-greedy match for characters between "Name:" and ","
        String ageRegex = "Age: (\\d+),";   // Match the age field, which consists of digits (\\d+), followed by a comma
        String emailRegex = "Email: (.*?)$"; // Match the email field, non-greedy match for any characters following "Email:"

        // Create Pattern objects for each regular expression
        // Pattern.compile() compiles the regular expression into a Pattern object, which can be used for matching
        Pattern namePattern = Pattern.compile(nameRegex);
        Pattern agePattern = Pattern.compile(ageRegex);
        Pattern emailPattern = Pattern.compile(emailRegex);

        // Create Matcher objects for each pattern
        // The Matcher object is used to find matches of the regular expression in the input string
        Matcher nameMatcher = namePattern.matcher(input);
        Matcher ageMatcher = agePattern.matcher(input);
        Matcher emailMatcher = emailPattern.matcher(input);

        // Extract and print the name, age, and email from the input string

        // Check if the name pattern is found in the input string
        if (nameMatcher.find()) {
            // Extract the first captured group (name) from the match
            String name = nameMatcher.group(1);
            System.out.println("Name: " + name);  // Print the extracted name
        }

        // Check if the age pattern is found in the input string
        if (ageMatcher.find()) {
            // Extract the first captured group (age), which is a string of digits
            int age = Integer.parseInt(ageMatcher.group(1)); // Convert the age string to an integer
            System.out.println("Age: " + age);  // Print the extracted age
        }

        // Check if the email pattern is found in the input string
        if (emailMatcher.find()) {
            // Extract the first captured group (email)
            String email = emailMatcher.group(1);
            System.out.println("Email: " + email);  // Print the extracted email
        }
    }
}
