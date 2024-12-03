import java.util.Scanner;
public class SafeInput
{
    /**
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = ""; // Set this to zero length. Loop runs until it isn't
        do {
            System.out.print("\n" + prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        } while (retString.length() == 0);

        return retString;

    }

    public static int getInt(Scanner pipe, String prompt) {
        int userInput = 0;
        boolean valid = false;
        String trash = "";
        while (!valid) {

            System.out.print(prompt + ": ");
            if (pipe.hasNextInt()) {
                userInput = pipe.nextInt();
                pipe.nextLine();
                valid = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("\nYou entered: " + trash);
                System.out.println("You have to enter a valid integer!");
            }
        }
        return userInput;
    }

    public static double getDouble(Scanner pipe, String prompt) {
        double userInput = 0.0;
        boolean valid = false;
        String trash = "";
        while (!valid) {

            System.out.print(prompt + ": ");
            if (pipe.hasNextDouble()) {
                userInput = pipe.nextDouble();
                pipe.nextLine();
                valid = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("\nYou entered: " + trash);
                System.out.println("You have to enter a valid input!");
            }
        }
        return userInput;
    }
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high)
    {
        int userInput = 0;
        boolean valid = false;
        String trash = "";
        while (!valid) {

            System.out.print(prompt + ": ");
            if (pipe.hasNextInt()) {
                userInput = pipe.nextInt();
                pipe.nextLine();
                if(userInput >= low && userInput <= high)
                {
                    valid = true;
                }
                else
                {
                    System.out.println("Enter a valid input within the range");
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("\nYou entered: " + trash);
                System.out.println("You have to enter a valid integer within the range!");
            }
        }
        return userInput;
    }
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high)
    {
        double userInput = 0;
        boolean valid = false;
        String trash = "";
        while (!valid) {

            System.out.print(prompt + ": ");
            if (pipe.hasNextDouble()) {
                userInput = pipe.nextDouble();
                pipe.nextLine();
                if(userInput >= low && userInput <= high)
                {
                    valid = true;
                }
                else
                {
                    System.out.println("Enter a valid input within the range");
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("\nYou entered: " + trash);
                System.out.println("You have to enter a valid number within the range!");
            }
        }
        return userInput;
    }
    public static boolean getYNConfirm(Scanner pipe, String prompt)
    {
        String userInput;
        boolean valid = false;
        while(!valid)
        {
            System.out.print(prompt + " (Y/N): ");
            userInput = pipe.nextLine();
            if(userInput.equalsIgnoreCase("Y"))
            {
                return true;
            }
            else if(userInput.equalsIgnoreCase("N"))
            {
                return false;
            }
            else
            {
                System.out.println("Please enter Y or N");
            }
        }
        return valid;
    }
    public static String getRegExString(Scanner pipe, String prompt, String regEx)
    {
        String userInput = "";
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt + ": ");
            userInput = pipe.nextLine();

            if (userInput.matches(regEx))
            {
                valid = true;
            } else {
                System.out.println("Input does not match the required format. Please try again.");
            }
        }
        return userInput;
    }
    public static void prettyHeader(String msg)
    {
        int totalWidth = 60;
        int messageLength = msg.length(); // used google
        int inside = totalWidth - messageLength - 6;
        int leftInside = inside / 2;
        for (int i = 0; i < totalWidth; i++) {
            System.out.print("*");
        }
        System.out.println();
        System.out.print("***");
        for (int i = 0; i < leftInside; i++) {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int i = 0; i < (inside - leftInside); i++) {
            System.out.print(" ");
        }
        System.out.println("***");
        for (int i = 0; i < totalWidth; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
    public static double getAverage(int values[])
    {
        int sum = 0;
        for(int x = 0; x < values.length; x++)
        {
            sum = sum + values[x];
        }
        double avg = (double) sum / values.length;
        return avg;
    }
}
