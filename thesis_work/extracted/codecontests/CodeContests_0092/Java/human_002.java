import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	final static int mod = 1_000_000_007;

	public static void main(String[] args) throws Exception {
		
		int n = nextInt(), m = nextInt();
		edges = new HashSet[n];
		for(int i = 0; i < n; i++) edges[i] = new HashSet<>();
		visited = new HashSet<>(); unvisited = new HashSet<>();
		while(m-- > 0) {
			int u = nextInt()-1, v = nextInt()-1;
			edges[u].add(v); edges[v].add(u);
		}
		ArrayList<Integer> ans = new ArrayList<>();
		int first = 0, prev = 0;;
		for(int i = 0; i < n; i++)
			if(edges[i].size() < edges[first].size())
				first = i;
		for(int i : edges[first])
			unvisited.add(i);
		for(int i = 0; i < n; i++)
			if(!edges[first].contains(i))
				dfs(i);
		ans.add(visited.size() - prev);
		for(int i = 0; i < n; i++) {
			if(!visited.contains(i)) {
				prev = visited.size();
				dfs(i);
				ans.add(visited.size() - prev);
			}
		}
		Collections.sort(ans);
		System.out.println(ans.size());
		StringBuilder sb = new StringBuilder();
		for(int i : ans)
			sb.append(i + " ");
		System.out.println(sb);
		
	}
	
	static HashSet<Integer>[] edges;
	static HashSet<Integer> visited, unvisited;

	static void dfs(int n) {
		if(visited.contains(n)) return;
		visited.add(n);
		for(int i : unvisited)
			if(!visited.contains(i) && !edges[n].contains(i))
				dfs(i);
	}
	

	// Fast Scanner

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
	static StringTokenizer st = new StringTokenizer("");

	static int nextInt() throws Exception {
		return Integer.parseInt(next());
	}

	static long nextLong() throws Exception {
		return Long.parseLong(next());
	}

	static double nextDouble() throws Exception {
		return Double.parseDouble(next());
	}

	static String next() throws Exception {
		while (!st.hasMoreTokens())
			st = new StringTokenizer(br.readLine());
		return st.nextToken();
	}

	static String nextLine() throws Exception {
		return br.readLine();
	}
}