import java.io.*;
import java.util.*;

public class Main implements Runnable {

	public void _main() throws IOException {
		double a = nextDouble();
		double b = nextDouble();
		double c = nextDouble();
		Locale.setDefault(Locale.UK);
		if (a == 0) {
			if (b == 0) {
				if (c == 0)
					out.print(-1);
				else
					out.print(0);
				return;
			}
			out.println(1);
			out.printf("%.5f", -c / b);
			return;
		}
		double d = b * b - 4 * a * c;
		if (d < 0) {
			out.println(0);
			return;
		}				
		double x1 = (-b - Math.sqrt(d)) / (2 * a);
		double x2 = (-b + Math.sqrt(d)) / (2 * a);
		if (Math.abs(x1 - x2) < 1e-9) {
			out.println(1);
			out.printf("%.5f", x1);
		}
		else {
			out.println(2);
			out.printf("%.5f\n", Math.min(x1, x2));
			out.printf("%.5f\n", Math.max(x1, x2));
		}
	}

	private BufferedReader in;
	private PrintWriter out;
	private StringTokenizer st;

	private String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String rl = in.readLine();
			if (rl == null)
				return null;
			st = new StringTokenizer(rl);
		}
		return st.nextToken();
	}

	private int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	private long nextLong() throws IOException {
		return Long.parseLong(next());
	}

	private double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	public static void main(String[] args) {
		new Thread(new Main()).start();
	}

	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);

			_main();

			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(202);
		}
	}

}
