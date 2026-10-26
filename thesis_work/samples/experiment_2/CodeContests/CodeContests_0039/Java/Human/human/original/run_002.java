import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		printNum("", 'a', n);
	}

	private static void printNum(String str, char mx, int n)
	{
		if (str.length() == n) {
			System.out.println(str);
		} else {
			for (char c='a'; c<=mx; c++) {
				printNum(str+c, (char)((c==mx)?mx+1:mx), n);
			}
		}
	}
}
