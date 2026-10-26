import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static long X;

	public static void main(String[] args) {
		int N = sc.nextInt();
		X = sc.nextInt();
		Exam[] exs = new Exam[N];
		for (int i = 0; i < N; i++) {
			long B = Long.parseLong(sc.next());
			long L = Long.parseLong(sc.next());
			long U = Long.parseLong(sc.next());
			exs[i] = new Exam(B, L, U);
		}
		Arrays.sort(exs);
		long point = 0;
		for (int i = 0; i < N; i++) {
			point += exs[i].b * exs[i].l;
		}
		int t = N;
		while (point > 0) {
			t--;
			point -= exs[t].value();
		}
		if (t < N) {
			point += exs[t].value();
			t++;
		}
		long base = (N - t) * X;
		long ans = Long.MAX_VALUE;
		for (int i = 0; i < t; i++) {
			long use = (point + exs[i].l - 1) / exs[i].l;
			ans = Math.min(ans, base + use);
			use = (point + exs[i].b * (exs[i].u - exs[i].l) + exs[i].u - 1) / exs[i].u;
			ans = Math.min(ans, base + use);
		}
		for (int i = t; i < N; i++) {
			long cp = point + exs[i].value() - exs[t - 1].value();
			long use = (cp + exs[i].l - 1) / exs[i].l;
			ans = Math.min(ans, base + use);
			use = (cp + exs[i].b * (exs[i].u - exs[i].l) + exs[i].u - 1) / exs[i].u;
			ans = Math.min(ans, base + use);
		}
		System.out.println(ans);
	}

	static class Exam implements Comparable<Exam> {
		long b, l, u;

		public Exam(long b, long l, long u) {
			this.b = b;
			this.l = l;
			this.u = u;
		}

		@Override
		public int compareTo(Exam o) {
			return Long.compare(this.value(), o.value());
		}

		long value() {
			return this.u * X - (this.u - this.l) * this.b;
		}
	}

}
