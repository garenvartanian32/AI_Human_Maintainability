import java.util.*;
public class Main {

		static void solve() {
			Scanner sc = new Scanner(System.in);
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			sc.close();
			int m1 = x2 - x1;
			int m2 = y2 - y1;
			int x3 = x2 - m2;
			int x4 = x1 - m2;
			int y3 = y2 + m1;
			int y4 = y1 + m1;
			System.out.println(x3+" "+y3+" "+x4+" "+y4);
		}
	public static void main(String[] args) {
		solve();

	}

}
