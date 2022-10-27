package ua.lviv.iot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import ua.lviv.iot.dfs.DFS;
import ua.lviv.iot.dfs.Vertex;

public class Main {
    public static int numberOfLevels;

    public static Integer[][] readFile(String fileName) throws IOException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        numberOfLevels = Integer.parseInt(scanner.nextLine());
        String[] graph = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())))
                .split("\r\n");
        Integer[][] companies = new Integer[numberOfLevels + 1][];
        for (int i = 0; i < graph.length; i++) {
            companies[i] = new Integer[graph[i].split(" ").length];
        }

        for (int i = 0; i < companies.length; i++) {
            for (int j = 0; j < companies[0].length;) {
                for (int g = 0; g < graph[i].split(" ").length; g++) {
                    String[] splittedValues = graph[i].split(" ");
                    companies[i][j] = Integer.parseInt(splittedValues[g]);
                    j++;
                    splittedValues = null;
                }
            }
        }
        return companies;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {

        Integer[][] graph = readFile("src//input1.txt");

        Vertex[][] vertexGraph = new Vertex[graph.length][];

        for (int i = 0; i < graph.length; i++) {
            vertexGraph[i] = new Vertex[graph[i].length];
        }

        for (int i = 1; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                vertexGraph[i][j] = new Vertex(graph[i][j]);
            }
        }

        for (int i = vertexGraph.length - 1; i > 1; i--) {
            for (int j = 0; j < vertexGraph[i].length; j++) {
                if (j == 0) {
                    vertexGraph[i][j].right = vertexGraph[i - 1][j];
                    vertexGraph[i][j].left = null;
                } else if (j == vertexGraph[i].length - 1) {
                    vertexGraph[i][j].right = null;
                    vertexGraph[i][j].left = vertexGraph[i - 1][j - 1];
                } else {
                    vertexGraph[i][j].left = vertexGraph[i - 1][j - 1];
                    vertexGraph[i][j].right = vertexGraph[i - 1][j];
                }

            }
        }

        DFS dfs = new DFS(vertexGraph);

        dfs.doDFS(numberOfLevels);
        System.out.println(dfs.getMaxExperience());

        File file = new File("src\\career.out.txt");
        FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
        fileWriter.write(Integer.toString(dfs.getMaxExperience()));
        fileWriter.close();

    }

}
