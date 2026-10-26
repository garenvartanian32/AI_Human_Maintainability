import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int start, end;
		boolean flag;
		String s;
		
		start = sc.nextInt();
		end = sc.nextInt();
		s = "";
		while (start + end != 0) {
			flag = true;
			System.out.print(s);
			for (int i = start; i <= end; i++) {
				if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
					System.out.println(i);
					flag = false;
				}
			}
			if (flag) {
				System.out.println("NA");
			}
			
			start = sc.nextInt();
			end = sc.nextInt();
			s = "\n";
		}
	}
}