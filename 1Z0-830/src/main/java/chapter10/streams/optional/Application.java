package chapter10.streams.optional;

import java.util.Optional;
import java.util.logging.Logger;

public class Application {
    static Logger logger = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        try {
            var result = average(90, 100, 80, 90, 45);
            System.out.printf("Average: %.2f%n", result.orElseThrow(() -> new IllegalStateException("No score provided")));

            System.out.println("**********************************");

            var result2 = average();
            System.out.printf("Average: %.2f%n", result2.orElseThrow(() -> new IllegalStateException("No score provided")));

            System.out.println("**********************************");

            var result3 = average();
            System.out.printf("Average: %s", result3);


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

