import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static Scanner scan = new Scanner(System.in);
	static PrintWriter out = new PrintWriter(System.out);

	public static void main(String[] args) {
		int n = si();
		while(n-->0){
			char[] mozi = scan.next().toCharArray();
			Arrays.sort(mozi);
			String min = "",max = "";
			for (int i = 0; i < mozi.length; i++){
				min+=mozi[i];
				max=mozi[i]+max;
			}
			out.println(Integer.parseInt(max)-Integer.parseInt(min));
		}
		out.flush();
	}

	static int si() {
		return Integer.parseInt(scan.next());
	}
}