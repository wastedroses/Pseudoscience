import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

public class ABCDGuesser1 {
    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        //Getting the input from user and storing it into the method
        String input = in.nextLine();
        //Making sure that the user's input is a double/valid number
        FormatChecker.canParseDouble(input);
        double number = Double.parseDouble(input);
        return number;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        out.println(
                "What would you like one of your numbers to be? It cannot be 1.");
        //Getting input from the user and storing it into the method
        String input = in.nextLine();
        //Making sure the input is a double and that it's positive and greater than 1.
        FormatChecker.canParseDouble(input);
        double number = Double.parseDouble(input);
        if (number == 1.0 || number < 0) {
            out.println("Please choose a number that is not 1.0 or negative.");
        }
        return number;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        //Asking the user what they would like mu to be. Runs through getPositiveDouble
        //and gets the user input that way.
        out.println("What number would you like to approximate?");
        double mu = getPositiveDouble(in, out);
        //Asking the user what they would like w, x, y, and z to be. Runs through
        //getPositiveDoubleNotOne and gets the user input that way.
        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);
        //Creating an array for the exponents so that they are stored somewhere
        double[] exponentials = { -5.0, -4.0, -3.0, -2.0, -1.0, (-1.0 / 2),
                (-1.0 / 3), (-1.0 / 4), 0, (1.0 / 4), (1.0 / 3), (1.0 / 2), 1,
                2, 3, 4, 5 };
        //initializing a, b, c, and d so that it can be used inside of the loop.
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        //initializing these letters is so that the best combination of numbers
        //is stored inside the array for each letter (a, b, c, d) and these letters
        //are used inside of the conditional and loop so that way the best combination
        //is used when the error is in the threshold.
        double i = 0;
        double j = 0;
        double k = 0;
        double l = 0;
        double finalAnswer = 0;
        double answer = 0;
        double minimumError = Double.MAX_VALUE;
        //While loop for every single possibility/value for each exponent. I did
        //these 4 times in a row so that no letter would be missed.
        while (a < exponentials.length) {
            while (b < exponentials.length) {
                while (c < exponentials.length) {
                    while (d < exponentials.length) {
                        //Answer is equal to deJager formula provided in project
                        //summary.
                        answer = ((Math.pow(w, exponentials[a]))
                                * (Math.pow(x, exponentials[b]))
                                * (Math.pow(y, exponentials[c]))
                                * (Math.pow(z, exponentials[d])));
                        //Error equation
                        double error = (Math.abs((answer - mu) / (mu)));
                        //If statement is for when error is less than the minimum
                        //error, and goes through every time the error is less than
                        //that, so that when when the error is greater than, it's
                        //the final and best answer.
                        if (error < minimumError) {
                            minimumError = error;
                            i = exponentials[a];
                            j = exponentials[b];
                            k = exponentials[c];
                            l = exponentials[d];
                            finalAnswer = answer;
                        }
                        d++;
                    }
                    //Resetting the value of every exponent once the right
                    //combination is found.
                    d = 0;
                    c++;
                }
                c = 0;
                b++;
            }
            b = 0;
            a++;
        }
        a = 0;
        out.println(finalAnswer);
        //Closing scanner
        in.close();
        out.close();
    }
}