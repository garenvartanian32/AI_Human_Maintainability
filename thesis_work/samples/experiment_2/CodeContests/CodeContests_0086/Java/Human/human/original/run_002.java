import java.io.*;
import java.util.*;
public class D691 {
public static void main(String[] args) throws IOException {
	input.init(System.in);
	PrintWriter out = new PrintWriter(System.out);
	int n = input.nextInt(), m = input.nextInt();
	int[] ps = new int[n];
	for(int i = 0; i<n; i++) ps[i] = input.nextInt();
	int[] as = new int[m], bs = new int[m];
	ArrayList<Integer>[] g = new ArrayList[n];
	for(int i = 0; i<n; i++) g[i] = new ArrayList<Integer>();
	for(int i = 0; i<m; i++)
	{
		as[i] = input.nextInt()-1;
		bs[i] = input.nextInt()-1;
		g[as[i]].add(bs[i]);
		g[bs[i]].add(as[i]);
	}
	int[] ids = new int[n];
	Arrays.fill(ids, -1);
	int at = -1;
	for(int i = 0; i<n; i++)
	{
		if(ids[i] != -1) continue;
		at++;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(i);
		ids[i] = at;
		while(!q.isEmpty())
		{
			int a = q.poll();
			for(int e : g[a])
			{
				if(ids[e] == -1)
				{
					ids[e] = at;
					q.add(e);
				}
			}
		}
	}
	at++;
	ArrayList<Integer>[] comps = new ArrayList[at];
	for(int i = 0; i<at; i++) comps[i] = new ArrayList<Integer>();
	for(int i = 0; i<n; i++) comps[ids[i]].add(i);
	int[] res = new int[n];
	for(int i = 0; i<at; i++)
	{
		ArrayList<Integer> cur = new ArrayList<Integer>();
		for(int x : comps[i]) cur.add(ps[x]);
		Collections.sort(cur);
		Collections.reverse(cur);
		for(int j = 0; j<comps[i].size(); j++)
		{
			res[comps[i].get(j)] = cur.get(j);
		}
	}
	for(int x : res) out.print(x+" ");
	out.close();
}

public static class input {
	static BufferedReader reader;
	static StringTokenizer tokenizer;

	static void init(InputStream input) {
		reader = new BufferedReader(new InputStreamReader(input));
		tokenizer = new StringTokenizer("");
	}

	static String next() throws IOException {
		while (!tokenizer.hasMoreTokens())
			tokenizer = new StringTokenizer(reader.readLine());
		return tokenizer.nextToken();
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}
}
}

