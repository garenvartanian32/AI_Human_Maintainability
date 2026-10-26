import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

class LazySegmentTree {

	int[] nodes;
	int[] lazy;
	boolean[] isLazy;

	int n;
	int e; // 単位元

	public LazySegmentTree(int size) {
		n = 1;
		while (n < size) {
			n *= 2;
		}

		nodes = new int[2 * n - 1];
		lazy = new int[2 * n - 1];
		isLazy = new boolean[2 * n - 1];

		e = 0; // 単位元は0
	}

	private void eval(int k, int l, int r) {
		if (isLazy[k]) {

			// 葉でないなら
			if (r - l > 1) {
				// 子に伝搬
				lazy[2 * k + 1] = lazy[k] / 2;
				lazy[2 * k + 2] = lazy[k] / 2;

				isLazy[2 * k + 1] = true;
				isLazy[2 * k + 2] = true;
			}

			nodes[k] = lazy[k];

			lazy[k] = 0;
			isLazy[k] = false;
		}
	}

	private void update(int a, int b, int val, int k, int l, int r) {
		eval(k, l, r);
		if (a >= r || b <= l) {
			return;
		}

		if (a <= l && r <= b) {
			lazy[k] = (r - l) * val;
			isLazy[k] = true;
			eval(k, l, r);
			return;
		}

		update(a, b, val, 2 * k + 1, l, (l + r) / 2);
		update(a, b, val, 2 * k + 2, (l + r) / 2, r);

		nodes[k] = nodes[2 * k + 1] + nodes[2 * k + 2];

	}

	private int getSum(int a, int b, int k, int l, int r) {
		if (a >= r || b <= l) {
			return e;
		}

		eval(k, l, r);
		if (a <= l && r <= b) {
			return nodes[k];
		}

		int vl = getSum(a, b, 2 * k + 1, l, (l + r) / 2);
		int vr = getSum(a, b, 2 * k + 2, (l + r) / 2, r);

		return vl + vr;

	}

	public void update(int a, int b, int val) {
		update(a, b, val, 0, 0, n);
	}

	public int getSum(int a, int b) {
		return getSum(a, b, 0, 0, n);
	}

	public String debug() {
		StringBuffer buf = new StringBuffer();
		
		for(int s=0,n=1;s<nodes.length;s+=n,n*=2) {
			buf.append(_debug(nodes,s,s+n, this.n));
			buf.append("\n");
		}
		
		buf.append("--\n");
		
		for(int s=0,n=1;s<lazy.length;s+=n,n*=2) {
			buf.append(_debug(lazy,s,s+n, this.n));
			buf.append("\n");
		}
		return buf.toString();
	}
	
	private String _debug(int[] a, int s, int e, int x) {
		StringBuffer buf = new StringBuffer();
		for(int i=s;i<e;i++) {
			buf.append(a[i]);
			for(int j=0;j<(2*x)/(e-s) -1;j++) 
				buf.append(" ");
		}
		return buf.toString();
	}
	

	
}

public class Main {
	InputStream in = System.in;
	PrintStream out = System.out;

	public void _main(String[] args) {
		Scanner sc = new Scanner(in);

		int n = sc.nextInt();
		int q = sc.nextInt();

		LazySegmentTree seg = new LazySegmentTree(n);

		for (int i = 0; i < q; i++) {
			int c = sc.nextInt();
			if (c == 0) {
				int s = sc.nextInt();
				int t = sc.nextInt() + 1;
				int x = sc.nextInt();

				seg.update(s, t, x);

			} else {
				int s = sc.nextInt();
				int t = sc.nextInt() + 1;

				out.println(seg.getSum(s, t));
			}
		}

		sc.close();

	}

	public static void main(String[] args) {
		new Main()._main(args);
	}
}

