import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

public class ABCDGuesser2 {
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
     * de Jager formula method.
     *
     * @param j
     *            double for constants that are to be put to the power of
     *            something.
     * @param k
     *            integer for exponents
     * @return a positive double
     */
    private static double deJager(double j, int k) {
        //Creating array
        final double[] exponentials = { -5.0, -4.0, -3.0, -2.0, -1.0,
                (-1.0 / 2), (-1.0 / 3), (-1.0 / 4), 0, (1.0 / 4), (1.0 / 3),
                (1.0 / 2), 1, 2, 3, 4, 5 };
        //answer calculates the math for one of the integers and their exponent.
        //j = w/x/y/z, k = a, b, c, d
        double answer = ((Math.pow(j, exponentials[k])));
        return answer;
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
        final double[] exponentials = { -5.0, -4.0, -3.0, -2.0, -1.0,
                (-1.0 / 2), (-1.0 / 3), (-1.0 / 4), 0, (1.0 / 4), (1.0 / 3),
                (1.0 / 2), 1, 2, 3, 4, 5 };
        //initializing a, b, c, and d so that it can be used inside of the loop.
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        //initializing these letters is so that the best combination of numbers
        //is stored inside the array for each letter (a, b, c, d) and these letters
        //are used inside of the conditional and loop so that way the best combination
        //is used when the error is in the threshold.
        double finalAnswer = 0;
        double answer = 0;
        double minimumError = Double.MAX_VALUE;
        //For loop for each letter. The letters start off at 0 and increment in
        //the for loop, and also increment by the length of the array and index
        //at the array.
        for (a = 0; a < exponentials.length; a++) {
            for (b = 0; b < exponentials.length; b++) {
                for (c = 0; c < exponentials.length; c++) {
                    for (d = 0; d < exponentials.length; d++) {
                        //Answer is equal to deJager formula provided in project
                        //summary. deJager method is above.
                        answer = deJager(w, a) * deJager(x, b) * deJager(y, c)
                                * deJager(z, d);
                        //Error equation
                        double error = (Math.abs((answer - mu) / (mu)));
                        //If statement is for when error is less than the minimum
                        //error, and goes through every time the error is less than
                        //that, so that when when the error is greater than, it's
                        //the final and best answer.
                        //I removed the i, j, k, and l because it wasn't necessary
                        //for this loop.
                        //It would be more necessary for the while loop but since
                        //the for loop increments on its own and changes the value
                        //to the final value itself.
                        if (error < minimumError) {
                            minimumError = error;
                            finalAnswer = answer;
                        }
                    }
                    //Resetting the value of every exponent once the right
                    //combination is found.
                    //I got rid of the d++ and whatnot because it wasn't
                    //necessary because again, the for loop increments on its own.
                    d = 0;

                }
                c = 0;

            }
            b = 0;

        }
        a = 0;
        out.println(finalAnswer);
        //Closing scanner
        in.close();
        out.close();
    }
}