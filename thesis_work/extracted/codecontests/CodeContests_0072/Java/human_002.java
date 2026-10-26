import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
	static Graph G;
	static boolean[] visited;
	static int pos;
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		pos = scanner.nextInt()-1;
	    G = new Graph(n);
	    visited = new boolean[n];
	    List<Integer> components = new ArrayList<Integer>();
	    int[] a = new int[n];
		for(int i = 0; i < n; i++) {
			int to = scanner.nextInt()-1;
			a[i] = to;
			if(to != -1) {
				G.addEdge(i, to);
			}
		}
		for(int i = 0; i < n; i++) {
			if(!visited[i]) {
				int size = componentSize(i);
				if(size >= 0) components.add(size);
			}
		}
		int pre = 0;
		int start = pos;
		while(a[start] != -1) {
			pre++;
			start = a[start];
		}
		boolean[][] dp = new boolean[components.size()+1][n+1];
		dp[0][0] = true;
		for(int i = 1; i <= components.size(); i++) {
			for(int j = 0; j <= n; j++) {
				dp[i][j] = dp[i-1][j] || (j >= components.get(i-1) && dp[i-1][j-components.get(i-1)]);
			}
		}
		for(int i = 0; i <= n; i++) {
			if(dp[components.size()][i]) System.out.println(i + 1 + pre);
		}
	}
	
	public static int componentSize(int source) {
		if(source == pos) return -1000000;
		visited[source] = true;
		int size = 0;
		for(int next : G.adj(source)) {
			if(!visited[next]) {
				size += componentSize(next);
			}
		}
		return size+1;
	}

	
		
	
	
	
		
}
class Graph {
	
	List<List<Integer>> adj; 
	
	public Graph(int n) {
		adj = new ArrayList<List<Integer>>();
		for(int i = 0; i < n; i++) {
			adj.add(new LinkedList<Integer>());
		}
	}
	public void addEdge(int e1 ,int e2) {
		adj.get(e1).add(e2);
		adj.get(e2).add(e1);
	}
	public List<Integer> adj(int v) {
		return adj.get(v);
	}
	void printGraph() {
		for(int i = 0;  i < adj.size(); i++) {
			System.out.println(adj.get(i));
		}

	}
}

