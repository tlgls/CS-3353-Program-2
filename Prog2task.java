import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Prog2task {
    
    public static ArrayList<Link> Task1(int n, ArrayList<Link> pipes, MyHelper helper) {
        MyGraph graph = new MyGraph(n);

        //adds all edges to graph
        for(Link pipe : pipes){
            graph.addEdge(pipe.v1, pipe.v2, pipe.w);
        }
    
        return graph.MST(); // computes and return MST
     }

    public static ArrayList<Link> Task2(int n, ArrayList<Link> pipes, Link newPipe, MyHelper helper) {
        MyGraph graph = new MyGraph(n);

        // constructs graph using existing edges
        for(Link pipe : pipes){
            graph.addEdge(pipe.v1, pipe.v2, pipe.w);
        }

        // compute original MST
        ArrayList<Link> originalMST = graph.MST();
        graph.addEdge(newPipe.v1, newPipe.v2, newPipe.w); // add new edge to graph
        ArrayList<Link> newMST = graph.MST(); // compute new MST

        // creates adjacency list representation of MST
        ArrayList<Link>[] mstAdj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++){
            mstAdj[i] = new ArrayList<>();
        }

        // populates adjacency list with MST edges
        for(Link edge : originalMST){
            mstAdj[edge.v1].add(edge);
            mstAdj[edge.v2].add(edge);
        }
        
        // finds path in the original MST between two vertices of newPipe
        ArrayList<Link> path = new ArrayList<>();
        boolean[] visited = new boolean[n+1];
        findPath(mstAdj, newPipe.v1, newPipe.v2, -1, visited, path);

        // if no path is found, return empty list (graph is discontineud)
        if(path.isEmpty()){
            return new ArrayList<>();
        }

        // find maximum weighted edge in path
        Link maxEdge = null;
        for(Link edge : path){
            if(maxEdge == null || edge.w > maxEdge.w){
                maxEdge = edge;
            }
        }

        // if new edge improves MST by replacing heavier edge, return heavier edge
        if(maxEdge != null && newPipe.w < maxEdge.w){
            ArrayList<Link> result = new ArrayList<>();
            result.add(maxEdge);
            return result;
        }
        return new ArrayList<>();
     }

     private static boolean findPath(ArrayList<Link>[] adj, int current, int target, int parent, boolean[] visited, List<Link> path) {
    visited[current] = true;
        for (Link edge : adj[current]) {
            int neighbor = (edge.v1 == current) ? edge.v2 : edge.v1; // determines connected vertex
            if (neighbor == parent) continue; // avoid revisiting the immediate parent
            if (neighbor == target) {
                path.add(edge); // target found, add the edge and return success
                return true;
            }
            if (!visited[neighbor]) {
                if (findPath(adj, neighbor, target, current, visited, path)) {
                    path.add(edge); // edge is part of the path, add it to the list
                    return true;
                }
            }
        }
        return false; // no path is found
    }   

}

