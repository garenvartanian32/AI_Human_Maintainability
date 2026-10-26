import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		FastScanner sc = new FastScanner(System.in);
		String[] s = {"a","b","c","d","e","f","g",
				"h","i","j","k","l","m","n",
				"o","p","q","r","s","t","u",
				"v","w","x","y","z"};
		long n = sc.nextLong()-1;
		ArrayList<Long> list = new ArrayList<>();

		while(n >= 26) {
			list.add(n%26);
			n = n/26 - 1;
		}
		list.add(n);
		for(int i = list.size()-1; i >= 0; i--) {
			long a = list.get(i);
			System.out.print(s[(int)a]);
		}
	}
	//1000000000000001


	static class FastScanner {
		private BufferedReader reader = null;
	    private StringTokenizer tokenizer = null;
	    public FastScanner(InputStream in) {
	        reader = new BufferedReader(new InputStreamReader(in));
	        tokenizer = null;
	    }

	    public String next() {
	        if (tokenizer == null || !tokenizer.hasMoreTokens()) {
	            try {
	                tokenizer = new StringTokenizer(reader.readLine());
	            } catch (IOException e) {
	                throw new RuntimeException(e);
	            }
	        }
	        return tokenizer.nextToken();
	    }

	    public String nextLine() {
	        if (tokenizer == null || !tokenizer.hasMoreTokens()) {
	            try {
	                return reader.readLine();
	            } catch (IOException e) {
	                throw new RuntimeException(e);
	            }
	        }

	        return tokenizer.nextToken("\n");
	    }

	    public long nextLong() {
	        return Long.parseLong(next());
	    }

	    public int nextInt() {
	        return Integer.parseInt(next());
	    }

	    public double nextDouble() {
	         return Double.parseDouble(next());
	     }

	    public int[] nextIntArray(int n) {
	        int[] a = new int[n];
	        for (int i = 0; i < n; i++)
	            a[i] = nextInt();
	        return a;
	    }

	    public long[] nextLongArray(int n) {
	        long[] a = new long[n];
	        for (int i = 0; i < n; i++)
	            a[i] = nextLong();
	        return a;
	    }
	}
}
