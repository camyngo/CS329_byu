package edu.byu.cs329;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DijkstraMain {
    final Logger logger = LoggerFactory.getLogger(Dijkstra.class);
    public static void main(String[] args) {
        final int[][] graph = { { 0, 2, 4, Dijkstra.M, Dijkstra.M, Dijkstra.M }, { 2, 0, 1, 4, Dijkstra.M, Dijkstra.M },
            { 4, 1, 0, 2, 6, Dijkstra.M }, { Dijkstra.M, 4, 2, 0, 1, 3 }, { Dijkstra.M, Dijkstra.M, 6, 1, 0, 5 },
            { Dijkstra.M, Dijkstra.M, Dijkstra.M, 3, 5, 0 } };
        Dijkstra d = new Dijkstra(graph);
        int sp = d.shortestPath(0, 4);
        System.out.println("d = " + sp);
    }

}