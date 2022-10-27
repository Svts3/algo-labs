package ua.lviv.iot.dfs;

import java.util.*;

public class DFS {
    private Vertex[][] graph;

    public DFS(Vertex[][] graph) {
        this.graph = graph;
    }

    Stack<Vertex> stack = new Stack<>();

    public void doDFS(int numberOfLevels){
        for(int i = 0; i < numberOfLevels; i++){
            dfs(graph[numberOfLevels][i]);
        }
    }

    ArrayList<Integer> experiences = new ArrayList<>();

    private void dfs(Vertex startVertex){

        int experienceCounter = 0;

        stack.add(startVertex);

        while (!stack.isEmpty()){
            Vertex v = stack.pop();

            experienceCounter += v.experience;

            if(v.left == null && v.right == null){
                experiences.add(experienceCounter);
                experienceCounter = 0;
            }

            if(v.left != null){
                stack.add(v.left);
            }
            if(v.right != null){
                stack.add(v.right);
            }
        }

    }

    public int getMaxExperience(){
        return Collections.max(experiences);
    }
    

}
