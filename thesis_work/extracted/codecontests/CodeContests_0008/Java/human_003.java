/**
 * @author egaeus
 * @mail sebegaeusprogram@gmail.com
 * @veredict
 * @url
 * @category
 * @date
 **/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;

public class CFF {
    static TreeSet<Integer> lAdy[];
    static int[] vis;
    static ArrayList<int[]> list;

    public static void main(String args[]) throws Throwable {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = parseInt(in.readLine());
        for (int t = 0; t++ < T; ) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int N = parseInt(st.nextToken()), K = parseInt(st.nextToken());
            int[][] arr = new int[N][];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(in.readLine());
                arr[i] = new int[]{parseInt(st.nextToken()), parseInt(st.nextToken())};
            }
            int S = 2 * N, D = 2 * N + 1;
            MaxFlow_MinCost maxFlow_minCost = new MaxFlow_MinCost(2 * N + 2);
            for (int i = 0; i < N; i++)
                maxFlow_minCost.addEdge(S, i, 1, 0);
            for (int i = 0; i < N; i++)
                maxFlow_minCost.addEdge(N + i, D, 1, 0);
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    maxFlow_minCost.addEdge(i, N + j, 1, -(arr[i][1] * Math.min(j, K - 1) + (j < K - 1 || j == N - 1 ? arr[i][0] : 0)));
            int[] maxFlow = maxFlow_minCost.getMinCostFlow(S, D);
            ArrayList<Integer> solution = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++)
                    if (maxFlow[j] == i) {
                        solution.add(j + 1);
                        if (i >= K - 1 && i < N - 1)
                            solution.add(-(j + 1));
                    }
            }
            System.out.println(solution.size());
            System.out.println(solution.stream().map(a -> a+"").collect(Collectors.joining(" ")));
        }
    }

    static class MaxFlow_MinCost {
        static final long INF = 1000000000;
        private int n;
        private List<Edge>[] edges;

        @SuppressWarnings("unchecked")
        public MaxFlow_MinCost(int n) {
            this.n = n;
            edges = new List[n];
            for (int i = 0; i < n; i++)
                edges[i] = new ArrayList<Edge>();
        }

        public void addEdge(int s, int t, int cap, int cost) {
            edges[s].add(new Edge(s, t, edges[t].size(), cap, cost));
            edges[t].add(new Edge(t, s, edges[s].size() - 1, 0, -cost));
        }

        public int[] getMinCostFlow(int source, int sink) {
            long flow = 0, cost = 0;
            long[] potential = new long[n]; // allows Dijkstra to work with
            // negative edge weights
            while (true) {
                Edge[] parent = new Edge[n]; // used to store an augmenting path
                long[] dist = new long[n]; // minimal cost to vertex
                Arrays.fill(dist, INF);
                dist[source] = 0;
                // Dijkstra on cost
                PriorityQueue<Item> que = new PriorityQueue<Item>();
                que.add(new Item(0, source));
                while (!que.isEmpty()) {
                    Item item = que.poll();
                    if (item.dist != dist[item.v])
                        continue;
                    for (Edge e : edges[item.v]) {
                        long temp = dist[item.v] + e.cost + potential[item.v]
                                - potential[e.t];
                        // if can push some flow, and new cost is cheaper than
                        // push
                        if (e.cap > e.flow && dist[e.t] > temp) {
                            dist[e.t] = temp;
                            parent[e.t] = e;
                            que.add(new Item(temp, e.t));
                        }
                    }
                }
                // couldn't reach sink
                if (parent[sink] == null)
                    break;
                // update potentials for Dijkstra
                for (int i = 0; i < n; i++)
                    if (parent[i] != null)
                        potential[i] += dist[i];
                // maximum flow that can be pushed
                long augFlow = INF;
                for (int i = sink; i != source; i = parent[i].s)
                    augFlow = Math.min(augFlow, parent[i].cap - parent[i].flow);
                // push the flow
                for (int i = sink; i != source; i = parent[i].s) {
                    Edge e = parent[i];
                    e.flow += augFlow;
                    edges[e.t].get(e.rev).flow -= augFlow;
                    cost += augFlow * e.cost;
                }
                flow += augFlow;
            }
            int[] result = new int[n / 2 - 1];
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < edges[i].size(); j++)
                    if (edges[i].get(j).flow == 1)
                        result[i] = edges[i].get(j).t - result.length;
            }
            return result;
        }

        public class Item implements Comparable<Item> {
            long dist;
            int v;

            public Item(long dist, int v) {
                this.dist = dist;
                this.v = v;
            }

            public int compareTo(Item that) {
                if (this.dist < that.dist)
                    return -1;
                if (this.dist > that.dist)
                    return 1;
                return 0;
            }
        }

        public class Edge {
            public int s, t, rev, cap, flow, cost;

            public Edge(int s, int t, int reverse, int cap, int cost) {
                this.s = s;
                this.t = t;
                this.rev = reverse;
                this.cap = cap;
                this.cost = cost;
            }
        }
    }

}
