package ua.lviv.iot;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static Double getHypotenuse(Double a, Double b) {

        return Math.sqrt((a * a) + (b * b));
    }

    static Boolean atTheEnd = false;

    public static Double dynamicRecursion(int index, Double maxValueFromLeftAndRight,
            List<Double> heights, Double lowerCatet, Queue<Double> queue) {

        atTheEnd = false;

        // Check if we have reached the end
        if (index + 1 == heights.size()) {
            return maxValueFromLeftAndRight;
        }

        Double left, right;
        if (atTheEnd) {
            left = queue.peek();
            queue.poll();
            right = queue.peek();
            queue.poll();
        } else {
            left = dynamicRecursion(index + 1, heights.get(index + 1), heights, lowerCatet, queue);
            atTheEnd = true;
            right = dynamicRecursion(index + 1, 1D, heights, lowerCatet, queue);

            queue.add(left);
            queue.add(right);
        }

        Double leftHypotenuse = getHypotenuse(heights.get(index + 1) - maxValueFromLeftAndRight,
                lowerCatet);
        Double rightHypotenuse = getHypotenuse(1 - maxValueFromLeftAndRight, lowerCatet);

        // find max value between left + the previous left and right + the previous
        // right
        // else find max value between left and right
        if (index + 2 != heights.size())
            maxValueFromLeftAndRight = Math.max(leftHypotenuse + left, rightHypotenuse + right);
        else {
            maxValueFromLeftAndRight = Math.max(leftHypotenuse, rightHypotenuse);
        }

        return maxValueFromLeftAndRight;
    }

    public static Double getMaxWireLength(List<Double> heights, Double widthCatet,
            Queue<Double> queue) {
        Double maxValue = dynamicRecursion(0, heights.get(0), heights, widthCatet, queue);
        if (heights.get(0) != 1) {
            heights.set(0, 1D);
            Double maxValueRight = dynamicRecursion(0, heights.get(0), heights, widthCatet, queue);
            maxValue = Math.max(maxValue, maxValueRight);
        }
        return maxValue;
    }

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src//input.txt");

        Scanner scanner = new Scanner(file);

        Double distanceBetweenSupports = Double.valueOf(scanner.nextLine());
        List<Integer> list = new ArrayList<>();

        String[] lines = scanner.nextLine().split(" ");

        List<Double> numbers = new ArrayList<>();

        for (int i = 0; i < lines.length; i++) {
            numbers.add(Double.valueOf(lines[i]));
        }

        Queue<Double> queue = new ArrayDeque<>();

        Double maxWireLength = getMaxWireLength(numbers, distanceBetweenSupports, queue);

        BigDecimal bigDecimal = new BigDecimal(maxWireLength).setScale(2, RoundingMode.DOWN);

        System.out.println("Max wire length needed: " + bigDecimal.doubleValue());

    }

}
