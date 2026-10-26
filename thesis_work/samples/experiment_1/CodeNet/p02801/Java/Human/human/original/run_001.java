import java.util.Scanner;
 
public class Main {
 
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		char[] c = scn.nextLine().toCharArray();
		c[0]++;
		System.out.println(c[0]);
	}
 
}