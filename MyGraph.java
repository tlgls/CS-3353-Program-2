import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MyGraph {
    
    private ArrayList<Link>[] adjList; // adj list representation of grpah
    private int numVertices; // num of vertices in graph

    // Create an empty graph with n vertices and no edges
    public MyGraph(int n) {
        this.numVertices = n;
        adjList = new ArrayList[n+1]; // 1 based indexing
        for(int i = 0; i <= n; i++){
            adjList[i] = new ArrayList<>();
        }
	}

    // Copy g into the new graph
    public MyGraph(MyGraph g) {
        this.numVertices = g.numVertices;
        this.adjList = new ArrayList[numVertices + 1];

        // copies adjacency list to ensure deep copy
        for(int i = 0; i <= numVertices; i++){
            this.adjList[i] = new ArrayList<>(g.adjList[i]);
        }
    }

    // add an edge a->b with weight w to the graph
    // 
    public boolean addEdge(int a, int b, float w) {
        if(a <= 0 || b <= 0 || a > numVertices || b > numVertices){ // validates vertices
            return false;
        }
        for (Link edge : adjList[a]){ // checks if edge already exists
            if ((edge.v1 == a && edge.v2 == b) || (edge.v1 == b && edge.v2 == a)){
                return false; // edge exists
            }
        }
        Link newEdge = new Link(a, b, w); // creates and add new edge
        adjList[a].add(newEdge);
        adjList[b].add(newEdge); // do this because its undirected graph
        return true;
	}


    // return MST of a graph
    public ArrayList<Link>  MST() {
	/*ArrayList<Link> res = new ArrayList<Link>();
	Link l1 = new Link(1, 2, (float)1.0);
	Link l2 = new Link(3, 2, (float)1.0);
        res.add(l1);
        res.add(l2);
        return res; 
        */

        // Using Prim's Algorithm
        PriorityQueue<Link> pq = new PriorityQueue<>(new Comparator<Link>() {
            public int compare(Link e1, Link e2) {
                return Float.compare(e1.w, e2.w); // sorts edges by weight
            }
        });

        boolean[] visited = new boolean[numVertices + 1]; // track visited vertices
        ArrayList<Link> mstEdges = new ArrayList<>(); // stores edges in MST

        visited[1] = true; // starts from vertex 1

        pq.addAll(adjList[1]); // add all edges of vertex 1 to priority queue

        // continue until MST contains (numVertices - 1 ) edges
        while(!pq.isEmpty() && mstEdges.size() < numVertices - 1){
            Link edge = pq.poll(); // get smallest edge
            int v1 = edge.v1, v2 = edge.v2;

            // if both vertices are already in MST, skip edge (to prevent cycles)
            if(visited[v1] && visited[v2]) continue;
            mstEdges.add(edge);

            // add new vertex to MST and push its edges into the priority queue
            if(!visited[v1]){
                visited[v1] = true;
                pq.addAll(adjList[v1]);
            }
            if(!visited[v2]){
                visited[v2] = true;
                pq.addAll(adjList[v2]);
            }
        }
        return mstEdges;
        
    }

    public void output() {
        System.out.println(numVertices);
        for(int i = 0; i <= numVertices; i++){
            for(Link edge : adjList[i]){
                edge.fix(); // ensures that v1 < v2
                System.out.println(edge.v1 + " " + edge.v2 + " " + edge.w);
            }
        }   
    }
}

