import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class Main implements Runnable {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static int[] depth;
	static int[][] parents;

	static void initLca(int[][][] g, int r) {
		int n = g.length;
		int[] parent = new int[n];
		depth = new int[n]; // todo global
		dfsLca(g, parent, depth, r, -1, 0);

		parents = parents(parent); // todo global
	}

	static void dfsLca(int[][][] g, int[] parent, int[] depth, int cur, int prev, int d) {
		parent[cur] = prev;
		depth[cur] = d;

		for (int[] to : g[cur]) {
			if (to[0] == prev) continue;
			dfsLca(g, parent, depth, to[0], cur, d+1);
		}
	}

	static int[][] parents(int[] parent) {
		int n = parent.length;
		int logN = Integer.numberOfTrailingZeros(Integer.highestOneBit(n-1))+1;

		int[][] res = new int[logN][n];
		res[0] = parent;

		for (int k=0; k<logN-1; k++) {
			for (int i=0; i<n; i++) {
				if (res[k][i] < 0) res[k+1][i] = -1;
				else res[k+1][i] = res[k][res[k][i]];
			}
		}

		return res;
	}

	static int lca(int u, int v, int[][] parents, int[] depth) {
		if (depth[u] > depth[v]) {
			int t = u; u = v; v = t;
		}

		int logN = parents.length;
		int n = parents[0].length;

		for (int k=0; k<logN; k++) {
			if (((depth[v] - depth[u])>>k&1) == 1) {
				v = parents[k][v];
			}
		}
		if (u == v) return u;

		for (int k=logN-1; k>=0; k--) {
			if (parents[k][u] != parents[k][v]) {
				u = parents[k][u];
				v = parents[k][v];
			}
		}

		return parents[0][u];
	}

	static int[][][] undirectedGraph(int n, int[] v1, int[] v2, int[] cost) {
		int[] cnt = new int[n];
		for (int i : v1) cnt[i]++;
		for (int i : v2) cnt[i]++;

		int[][][] g = new int[n][][];
		for (int i=0; i<n; i++) g[i] = new int[cnt[i]][2];
		for (int i=0; i<v1.length; i++) {
			int s = v1[i];
			int t = v2[i];

			g[s][--cnt[s]][0] = t;
			g[s][cnt[s]][1] = cost[i];

			g[t][--cnt[t]][0] = s;
			g[t][cnt[t]][1] = cost[i];
		}

		return g;
	}

	public void run() {
		int n = in.nextInt();
		int r = -1;

		ArrayList<int[]> list = new ArrayList<>();
		boolean[] refed = new boolean[n];
		for (int i=0; i<n; i++) {
			int k = in.nextInt();
			for (int j=0; j<k; j++) {
				int c = in.nextInt();
				list.add(new int[]{i, c});
				refed[c] = true;
			}
		}

		for (int i=0; i<n; i++) {
			if (!refed[i]) {
				r = i;
			}
		}

		int e = list.size();
		int[] s = new int[e];
		int[] t = new int[e];
		int[] c = new int[e];
		for (int i=0; i<e; i++) {
			s[i] = list.get(i)[0];
			t[i] = list.get(i)[1];
			c[i] = 0;
		}

		int[][][] g = undirectedGraph(n, s, t, c);
		initLca(g, r);

		int q = in.nextInt();
		while (q-- > 0) {
			int u = in.nextInt();
			int v = in.nextInt();
			out.println(lca(u, v, parents, depth));
		}

		out.flush();
		in.close();
		out.close();
	}

	public static void main(String[] args) {
		debug = args.length > 0;
		new Thread(null, new Main(), "", 16 * 1024 * 1024).start();
	}

	static void dump(Object... o) { if (debug) System.err.println(Arrays.deepToString(o)); }
}