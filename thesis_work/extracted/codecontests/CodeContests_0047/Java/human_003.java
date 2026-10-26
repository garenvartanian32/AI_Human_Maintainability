import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] num = new int[3];
		for(int i = 0;i < 3;++i)
			num[i] = sc.nextInt();
		sc.close();
		
		int max = 0, sum = 0;
		for(int i = 0;i < 3;++i) {
			sum += num[i];
			if(max < num[i])
				max = num[i];
		}
		if(sum%2 == 0 && sum/2 == max)
			System.out.println("Yes");
		else
			System.out.println("No");
	}
}
