package chapter10.streams.optional;

import java.util.Optional;
import java.util.logging.Logger;

public class Application2 {
    static Logger logger = Logger.getLogger(Application2.class.getName());

    public static void main(String[] args) {
        try {
            System.out.println("**********************************");

            var result3 = average();

            System.out.printf("Average: %s%n", result3);

            System.out.println("**********************************");

            var result = average(90, 100, 80, 90, 45);

            System.out.printf("Average: %s%n", result);

        } catch (IllegalStateException e) {
            logger.log(java.util.logging.Level.SEVERE, e.getMessage());
        }
    }


    public static Optional<Double> average(int... scores) {
        if (scores.length == 0) {
            return Optional.empty();
        }

        int sum = 0;

        for (int score : scores) {
            sum += score;
        }

        var result = (double) sum / scores.length;

        return Optional.of(result);
    }
}

