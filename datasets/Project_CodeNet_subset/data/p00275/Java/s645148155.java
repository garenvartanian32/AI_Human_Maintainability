import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true){
			int n = sc.nextInt();
			if(n==0) break;
			
			String s = sc.next();
			int ba = 0;
			int[] te = new int[n];
			for(int i=0;i<100;i++){
				if(s.charAt(i)=='M'){
					te[i%n]++;
				}else if(s.charAt(i)=='S'){
					ba += te[i%n] + 1;
					te[i%n] = 0;
				}else{
					te[i%n] += ba + 1;
					ba = 0;
				}
			}
			Arrays.sort(te);
			for(int i=0;i<n;i++) System.out.print(te[i] + " ");
			System.out.println(ba);
		}
	}
}