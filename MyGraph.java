import java.util.Scanner;
import java.util.ArrayList;

public class MyGraph {
    
    // Create an empty graph with n vertices and no edges
    public MyGraph(int n) {
	}

    // Copy g into the new graph
    public MyGraph(MyGraph g) {
    }

    // add an edge a->b with weight w to the graph
    // 
    public boolean addEdge(int a, int b, float w) {
        return true;
	}


    // return MST of a graph
    public ArrayList<Link>  MST() {
	ArrayList<Link> res = new ArrayList<Link>();
	Link l1 = new Link(1, 2, (float)1.0);
	Link l2 = new Link(3, 2, (float)1.0);
        res.add(l1);
        res.add(l2);
        return res; 
    }

    public void output() {
    }
}

