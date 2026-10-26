import java.util.Scanner;

public class Main{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()){
			int n = scan.nextInt();
			if(n == 0){
				break;
			}
			int[] list = new int[n];
			for(int i = 0;i < n;i++){
				list[i] = scan.nextInt();
			}
			int count = 0;
			while(n > 1){
				for(int i = 0;i < n-1;i++){
					if(list[i] > list[i + 1]){
						int t = list[i];
						list[i] = list[i + 1];
						list[i + 1] = t;
						count++;
					}
				}
				n--;
			}
			System.out.println(count);
		}
	}
}