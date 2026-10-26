import java.util.Scanner;

public class ER38ProblemB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in= new Scanner(System.in);
		
		int n=in.nextInt();
		
		int b[]=new int[1000001];
		int a[]=new int[n];
		
		for(int i=0;i<n;i++) {
			a[i]=in.nextInt();
			b[a[i]]++;
		}
		
		
		int x=0;
		int y=0;
		for(int i=1;i<=500000;i++) {
			if(b[i]==1) {
				x=i-1;
			}
		}
		
		for(int i=1000000;i>500000;i--) {
			if(b[i]==1) {
				y= 1000000-i;
			}
		}
		
		System.out.println(Math.max(x, y));

	}

}
