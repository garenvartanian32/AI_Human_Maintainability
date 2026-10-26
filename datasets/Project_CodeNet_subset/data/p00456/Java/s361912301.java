import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int wt=0,kt=0;
		int[] w=new int[10];
		int[] k=new int[10];
		for(int i=0;i<10;i++){
			w[i]=sc.nextInt();
		}
		for(int i=0;i<10;i++){
			k[i]=sc.nextInt();
		}
		Arrays.sort(w);
		wt=w[9]+w[8]+w[7];
		Arrays.sort(k);
		kt=k[9]+k[8]+k[7];
		System.out.println(wt+" "+kt);
	}
}