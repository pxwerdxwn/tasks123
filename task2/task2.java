package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        try {
            Scanner circle = new Scanner(new File(args[0]));
            double x0 = circle.nextDouble();
            double y0 = circle.nextDouble();
            double r = circle.nextDouble();
            circle.close();

            Scanner points = new Scanner(new File(args[1]));
            while (points.hasNextDouble()) {
                double x = points.nextDouble();
                double y = points.nextDouble();

                double dist1 = Math.pow(x - x0, 2) + Math.pow(y - y0, 2);
                double dist2 = Math.sqrt(dist1);

                int position;
                if (Math.abs(dist2 - r) < 1e-9) { 
                    position = 0;
                } else if (dist2 < r) {
                    position = 1;
                } else {
                    position = 2;
                }

                System.out.println(position);
            }
            points.close();

        } catch (FileNotFoundException e) {
            System.out.println("Ошибка");
        }
    }
}