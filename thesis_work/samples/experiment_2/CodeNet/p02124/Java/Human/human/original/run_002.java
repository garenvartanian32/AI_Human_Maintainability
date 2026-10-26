import java.util.*;

public class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		StringBuilder sb = new StringBuilder("ai1333");
		for (int i = 0; i < n / 100; i++) {
		    sb.append("3");
		}
		System.out.println(sb);
	}
}

