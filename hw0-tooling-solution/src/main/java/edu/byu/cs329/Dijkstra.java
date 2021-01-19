package edu.byu.cs329;
import java.util.HashSet;
import java.util.Set;

public class Dijkstra {
    static final int M = Integer.MAX_VALUE;
    private final int[][] graph;
    private final int dimension;
    private int[][] l = null;

    public Dijkstra(final int[][] g) {
        if (g == null) {
            throw new IllegalArgumentException("The graph must be non-null");
        }
        dimension = g.length;
        if (dimension == 0) {
            throw new IllegalArgumentException("The graph must be non-empty");
        }
        for (int i = 0; i < dimension; ++i) {
            if (g[i].length != dimension) {
                throw new IllegalArgumentException("The graph must be square");
            }
        }
        graph = g;
    }

    private void allShortestPathLengths() {
        final int[][] lengths = new int[graph.length][graph.length];
        initializeL(graph, lengths);
        for (int a = 0; a < lengths.length; ++a) {
            final int[] thisL = lengths[a];
            final Set<Integer> s = new HashSet<Integer>();
            while (s.size() < lengths.length) {
                final int u = getMinimumIndex(thisL, s);
                s.add(u);
                for (int v = 0; v < thisL.length; ++v) {
                    if (s.contains(v)) {
                        continue;
                    }
                    final int newDistance = thisL[u] + graph[u][v];
                    if (newDistance > 0 && newDistance < thisL[v]) {
                        thisL[v] = newDistance;
                    }
                }
            }
        }

        l = lengths;
    }

    public int shortestPath(final int from, final int to) {
        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("Indices must be nonnegative!");
        }
        if (from >= graph.length || to >= graph.length) {
            throw new IllegalArgumentException("Indices must be within the graph's dimension!");
        }
        if (l == null) {
            allShortestPathLengths();
        }
        return l[from][to];
    }

    static int getMinimumIndex(final int[] thisL, final Set<Integer> s) {
        int u = M;
        final int length = thisL.length;
        for (int i = 0; i < length; ++i) {
            if (s.contains(i)) {
                continue;
            }
            if (u == M || thisL[i] < thisL[u]) {
                u = i;
            }
        }
        return u;
    }

    private void initializeL(final int[][] graph, final int[][] l) {
        for (int i = 0; i < graph.length; ++i) {
            for (int j = 0; j < graph.length; ++j) {
                if (i == j) {
                    l[i][j] = 0;
                } else {
                    l[i][j] = M;
                }
            }
        }
    }

    public static String tableToString(final int[][] l) {
        final StringBuilder sb = new StringBuilder();
        final String eol = System.getProperty("line.separator");
        final int length = l.length;
        for (int i = 0; i < length; ++i) {
            final int[] tl = l[i];
            for (int j = 0; j < length; ++j) {
                final int value = tl[j];
                if (value == M) {
                    sb.append("-");
                } else {
                    sb.append(tl[j]);
                }
                if (j < length - 1) {
                    sb.append(" ");
                }
            }
            if (i < length - 1) {
                sb.append(eol);
            }
        }

        return sb.toString();
    }

}