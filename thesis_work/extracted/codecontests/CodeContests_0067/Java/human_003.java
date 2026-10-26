import java.io.*;
import java.util.*;

public class Watch195A {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int a = Integer.parseInt(st.nextToken());
		double b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		System.out.println(((int)(Math.ceil((a*c)/b)))-c);
	}
}