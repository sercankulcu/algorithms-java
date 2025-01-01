package parse;

public class Regex {

    public static void main(String[] args) {
        // Sample input string
        String input = "Name: John Doe, Age: 30, Email: john.doe@example.com";

        // Define the starting points of each field in the input string
        String nameStart = "Name: ";
        String ageStart = "Age: ";
        String emailStart = "Email: ";

        // Extract the name, age, and email manually (without using regex functions)
        
        // Extract Name
        String name = extractField(input, nameStart, ",");
        System.out.println("Name: " + name);

        // Extract Age
        String age = extractField(input, ageStart, ",");
        System.out.println("Age: " + Integer.parseInt(age));

        // Extract Email
        String email = extractField(input, emailStart, null); // No delimiter for the email
        System.out.println("Email: " + email);
    }

    // Manually extract a field from the input string based on the start marker and optional end marker
    public static String extractField(String input, String start, String end) {
        // Find the starting position of the field
        int startPos = input.indexOf(start);
        if (startPos == -1) {
            return ""; // Return empty string if the start field is not found
        }

        // Move the start position to the end of the start marker
        startPos += start.length();

        // If there's no end marker, take the substring from start to the end of the string
        if (end == null) {
            return input.substring(startPos);
        }

        // Find the ending position of the field, based on the end marker
        int endPos = input.indexOf(end, startPos);
        if (endPos == -1) {
            return input.substring(startPos); // If no end marker, return the substring to the end
        }

        // Return the substring between the start and end positions
        return input.substring(startPos, endPos);
    }
}
