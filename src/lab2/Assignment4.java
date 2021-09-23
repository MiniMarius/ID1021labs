package lab2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Assignment4 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader("src/lab2/bajskorv.txt"));
        int[] numbers = new int[in.nextInt()];
        for (int i = 0; i < numbers.length; i++) {
            if (in.hasNextInt()) {
                numbers[i] = in.nextInt();
            }
        }
        System.out.println(Arrays.toString(numbers));
    }
}
