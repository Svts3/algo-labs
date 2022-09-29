package ua.lviv.iot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

import ua.lviv.iot.bfs.Node;

public class Main {

    public static boolean isSafe(int[][] matrix, boolean visited[][], int x, int y) {
        return (matrix[x][y] == 1 && !visited[x][y]);
    }

    public static boolean isValid(int x, int y, int rows, int cols) {
        return (x < rows && y < cols && x >= 0 && y >= 0);
    }

    public static int findMinimunNumberOfStepsToReachLastColumn(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        final int[] row = { -1, 0, 0, 1 };
        final int[] col = { 0, -1, 1, 0 };

        boolean[][] visited = new boolean[rows][cols];

        Queue<Node> nodesToBeVisited = new ArrayDeque<Node>();

        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 1) {
                nodesToBeVisited.add(new Node(i, 0, 0));
                visited[i][0] = true;
            }
        }

        while (!nodesToBeVisited.isEmpty()) {
            int frontNodeX = nodesToBeVisited.peek().getX();
            int frontNodeY = nodesToBeVisited.peek().getY();
            int distance = nodesToBeVisited.peek().getDistance();
            nodesToBeVisited.poll();

            if (frontNodeY == cols - 1) {
                return distance;
            }

            for (int i = 0; i < row.length; i++) {
                if (isValid(frontNodeX + row[i], frontNodeY + col[i], rows, cols)
                        && isSafe(matrix, visited, frontNodeX + row[i], frontNodeY + col[i])) {
                    visited[frontNodeX + row[i]][frontNodeY + col[i]] = true;
                    nodesToBeVisited
                            .add(new Node(frontNodeX + row[i], frontNodeY + col[i], distance + 1));
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    public static int findShortestDistance(int[][] matrix) {

        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] r = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] c = { -1, 0, 1, -1, 1, -1, 0, 1 };

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int j2 = 0; j2 < r.length; j2++) {
                    if (matrix[i][j] == 0 && isValid(i + r[j2], j + c[j2], rows, cols)
                            && matrix[i + r[j2]][j + c[j2]] == 1) {
                        matrix[i + r[j2]][j + c[j2]] = Integer.MAX_VALUE;

                    }

                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == Integer.MAX_VALUE) {
                    matrix[i][j] = 0;
                }
            }
        }

        return findMinimunNumberOfStepsToReachLastColumn(matrix);
    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(new File("src\\input.txt"));
        int[][] matrix = new int[10][10];
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                if (input.hasNextInt()) {
                    matrix[i][j] = input.nextInt();
                }
            }
        }
        int distance = findShortestDistance(matrix);

        System.out.println("The shortest path: " + distance);

    }
}
