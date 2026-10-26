
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class g1 {
	private static class pair implements Comparable<pair> {
		int x;
		int weight;

		pair(int X, int w) {
			x = X;
			weight = w;
		}
//		public  int  hashCode(){
//			return (x*737+weight).hash;
	//	}

		public int compareTo(pair e) {
			return weight - e.weight;
		}

		public int hashCode() {
			final int prime = 731;
			int result = 1;
			result = prime * result + weight;
			result = prime * result + x;
			return result;
		}

		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			pair other = (pair) obj;
			if (weight != other.weight)
				return false;
			if (x != other.x)
				return false;
			return true;
		}
		
	}

	private static class edge implements Comparable<edge> {
		int x;
		int y;
		int weight;

		edge(int X, int Y, int w) {
			x = X;
			y = Y;
			weight = w;
		}

		public int compareTo(edge e) {
			return weight - e.weight;
		}

	}

	static boolean hemos = true;;
	static final int destroyed = -1;
	static final int empty = 0;
	static final int safe = 1;
	static int tx, ty,ones,zeros;
	static ArrayList<Integer>[] adjlist;
	static ArrayList<pair>[] adjlist2;
	static HashMap<pair,Integer>hs;
	static HashMap<Integer, HashMap<Integer,Integer>>hm;
	static int[][] adjmatrix;
	static boolean[] vis;
	static boolean[][] visgrid;
	static int R, C;
	static boolean[] intialvis;
	static boolean[] vis2;
	static int[] counter;
	static int[] dist;
	static int[][] distgrid;

	static int[] level;
	static int[][] levelgrid;

	static int V, E;
	static Stack<Integer> st;
	static ArrayList<Integer> arrylist;
	static boolean flag;
	final static int INF = Integer.MAX_VALUE;
	static int[] dx = new int[] { 1,1,1,-1 ,-1,-1,0,0};
	static int[] dy = new int[] { 0,1,-1 ,0,1,-1,1,-1 };

	public static int dijkstragrid() {
		distgrid = new int[R][C];
		for (int i = 0; i < R; i++)
			Arrays.fill(distgrid[i], INF);
		distgrid[0][0] = adjmatrix[0][0];
		PriorityQueue<edge> pq = new PriorityQueue<edge>();
		pq.add(new edge(0, 0, adjmatrix[0][0]));
		while (!pq.isEmpty()) {
			edge edge = pq.poll();
			int x = edge.x;
			int y = edge.y;
			int w = edge.weight;
			if (x == R - 1 && y == C - 1)
				return distgrid[x][y];
			if (w > distgrid[x][y])
				continue;
			for (int i = 0; i < 4; i++) {
				int x1 = x + dx[i];
				int y1 = y + dy[i];
				if (valid(x1, y1) && distgrid[x1][y1] > w + adjmatrix[x1][y1])
					pq.add(new edge(x1, y1, distgrid[x1][y1] = w + adjmatrix[x1][y1]));
			}
		}
		return -1;
	}

	public static int dijkstra(int s, int e) {
		dist = new int[V];
		Arrays.fill(dist, INF);
		dist[s] = 0;
		PriorityQueue<pair> pq = new PriorityQueue<pair>();
		pq.add(new pair(s, 0));
		while (!pq.isEmpty()) {
			pair pair = pq.poll();
			int x = pair.x;
			int w = pair.weight;
			if (x == e)
				return dist[x];
			if (w > dist[x])
				continue;
			for (pair v : adjlist2[x])
				if (dist[v.x] > dist[x] + v.weight)
					pq.add(new pair(v.x, dist[v.x] = dist[x] + v.weight));

		}
		return -1;
	}
	//
	// public static boolean bellmanFord(int s) {
	// dist = new int[V];
	// Arrays.fill(dist, INF);
	// dist[s] = 0;
	// Boolean finished = true;
	// for (int i = 0; finished && i < V; i++) {
	// finished = false;
	// for (int j = 0; j < V; j++) {
	// for (edge v : adjlist2[j])
	// if (dist[v.x] > dist[j] + v.y) {
	// dist[v.x] = dist[j] + v.y;
	// finished = true;
	// }
	// }
	// if (i == V - 1)
	// return finished;
	// }
	// return false;
	//
	// }

	public static void dfstopologSort(int u) {
		vis[u] = true;
		intialvis[u] = true;
		for (int v : adjlist[u]) {
			if (intialvis[v]) {
				hemos = false;
				// System.out.println(Arrays.toString(intialvis));
				return;
			} else if (!vis[v])
				dfstopologSort(v);

		}
		intialvis[u] = false;
		st.add(u);
	}

	public static void bfstopologSort() {
		PriorityQueue<Integer> q = new PriorityQueue<>();
		for (int i = 0; i < V; i++) {
			if (counter[i] == 0)
				q.add(i);
		}
		while (!q.isEmpty()) {
			int u = q.poll();
			arrylist.add(u);
			for (int v : adjlist[u]) {
				if (--counter[v] == 0) {
					q.add(v);
				}
			}
		}
	}

	public static boolean valid2(int x, int y) {
		return (x > -1 && y > -1 && x < V && y < V && adjmatrix[x][y] != empty && !visgrid[x][y]);
	}

	public static void dfsgrid(int x, int y) {
		visgrid[x][y] = true;
		int x1, y1;
		for (int i = 0; i < 4; i++) {
			x1 = x + dx[i];
			y1 = y + dy[i];
			if (valid(x1, y1))
				dfsgrid(x1, y1);
		}
	}

	public static boolean bipartite2(int u) {
		for (int v : adjlist[u]) {
			if (counter[v] == -1) {
				counter[v] = 1 ^ counter[u];
				if (!bipartite2(v))
					return false;
			} else {
				if (vis2[v] == vis2[u]) {
					return false;
				}
			}

		}
		return true;
	}

	public static void bipartite(int u) {
		for (int v : adjlist[u]) {
			if (counter[v] == -1) {
				counter[v] = 1 ^ counter[u];
				if (counter[v] == 0)
					zeros++;
				else
					ones++;
				bipartite(v);
			} else {
				if (counter[v] == counter[u]) {
					flag = false;
				}
			}

		}
	}

	public static boolean valid(int x, int y) {
		pair p=new pair(x,y);
		return (x > 0 && x <= 1e9 && y > 0 && y <=1e9&&hs.containsKey(p)&&hs.get(p)==INF );
	}

	// public static void gridbfs(int x, int y) {
	// visgrid[x][y] = true;
	// levelgrid[x][y] = 0;
	// Queue<edge> q = new LinkedList<edge>();
	// q.add(new edge(x, y));
	// while (!q.isEmpty()) {
	// edge u = q.poll();
	// for (int i = 0; i < 4; i++) {
	// int x1 = u.x + dx[i];
	// int y1 = u.y + dy[i];
	// if (valid(x1, y1)) {
	// q.add(new edge(x1, y1));
	// levelgrid[x1][y1] = levelgrid[u.x][u.y] + 1;
	// visgrid[x1][y1] = true;
	// }
	// }
	// }
	//
	// }

	public static int bfs(pair p) {
//		if(!hm.containsKey(u))return -1;
//		if(!hm.get(u).containsKey(v))return -1;
	     hs.put(p, 0);
		Queue<pair> q = new LinkedList<pair>();
		q.add(p);
		while (!q.isEmpty()) {
			pair pair = q.poll();
			int x=pair.x;
			int y=pair.weight;
			if(x==tx&&y==ty)return hs.get(pair);
			for (int i=0;i<8;i++) {
				int x1=x+dx[i];
				int y1=y+dy[i];
				if (valid(x1, y1)) {
					pair p2=new pair(x1, y1);
					q.add(p2);
					//hm.get(x1).put(y1, hm.get(x).get(y)+1);
					hs.put(p2, hs.get(pair)+1);
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		PrintWriter pw = new PrintWriter(System.out);
	//	hm= new HashMap<Integer, HashMap<Integer,Integer>>();
		hs=new HashMap<g1.pair, Integer>();
		
		Scanner sc = new Scanner(System.in);
			int sx=sc.nextInt();
			int sy=sc.nextInt();
			tx=sc.nextInt();
			ty=sc.nextInt();
			E = sc.nextInt();
//			for (int i = 0; i < V; i++)
//				adjlist2[i] = new ArrayList<pair>();
			for (int j = 0; j < E; j++) {
				int r = sc.nextInt();
				int c1 = sc.nextInt();
				int c2 = sc.nextInt();
				
				for(int i=c1;i<=c2;i++)
					hs.put(new pair(r, i), INF);
			}
			System.out.println(bfs(new pair(sx, sy)));
	}

	private static class Scanner {
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s) {
			br = new BufferedReader(new InputStreamReader(s));
		}

		public String next() throws IOException {
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		public long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		public String nextLine() throws IOException {
			return br.readLine();
		}

		public double nextDouble() throws IOException {
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if (x.charAt(0) == '-') {
				neg = true;
				start++;
			}
			for (int i = start; i < x.length(); i++)
				if (x.charAt(i) == '.') {
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				} else {
					sb.append(x.charAt(i));
					if (dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg ? -1 : 1);
		}

		public boolean ready() throws IOException {
			return br.ready();
		}

	}
}
