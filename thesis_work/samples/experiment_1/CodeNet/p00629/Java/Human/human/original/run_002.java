
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		new Main().run();
	}

	private void run() throws IOException {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			int n = scanner.nextInt();
			if (n == 0)
				break;
			Team[] teams = new Team[n];
			for (int i = 0; i < n; i++)
				teams[i] = new Team(scanner.nextInt(), scanner.nextInt(),
						scanner.nextInt(), scanner.nextInt());
			Arrays.sort(teams);
			int[] num = new int[1001];
			int s = 0;
			for (int i = 0; i < n && s < 26; i++) {
				int a = num[teams[i].u];
				if (s < 10 && a < 3 || s < 20 && a < 2 || s < 26 && a == 0) {
					System.out.println(teams[i].i);
					num[teams[i].u]++;
					s++;
				}
			}
		}
	}

	class Team implements Comparable<Team> {
		int i, u, a, p;

		public Team(int i, int u, int a, int p) {
			super();
			this.i = i;
			this.u = u;
			this.a = a;
			this.p = p;
		}

		@Override
		public int compareTo(Team o) {
			if (o.a == a && p == o.p)
				return i - o.i;
			if (o.a == a)
				return p - o.p;
			return o.a - a;
		}

	}
}