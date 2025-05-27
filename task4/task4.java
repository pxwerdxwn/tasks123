package task4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class task4 {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            System.out.println("Ошибка");
            return;
        }
        String filePath = args[0];
        int[] nums = readNumbers(filePath);
        Arrays.sort(nums);
        int mid = nums[nums.length / 2];

        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - mid);
        }
        System.out.println(moves);
    }

    private static int[] readNumbers(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        int count = 0;
        while (scanner.hasNextInt()) {
            scanner.nextInt();
            count++;
        }
        scanner.close();

        scanner = new Scanner(new File(filePath));
        int[] nums = new int[count];
        for (int i = 0; i < count; i++) {
            nums[i] = scanner.nextInt();
        }
        scanner.close();
        return nums;
    }
}