import java.util.ArrayList;
import java.util.Scanner;

public class D {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int a = in.nextInt();
		int b = in.nextInt();
		int c = in.nextInt();
		int d = in.nextInt();
		int need = n-1+2;
		if(k<need || n==4){
			System.out.println(-1);
		}
		else{
			ArrayList<Integer> p1 = new ArrayList<Integer>();
			p1.add(a);
			p1.add(c);
			for(int i =1; i<=n; i++){
				if(i!=a && i!=b && i!=c && i!=d){
					p1.add(i);
				}
			}
			p1.add(d);
			p1.add(b);
			ArrayList<Integer> p2 = new ArrayList<Integer>();
			p2.add(c);
			p2.add(a);
			for(int i = 2;i<p1.size()-2; i++){
				p2.add(p1.get(i));
			}
			p2.add(b);
			p2.add(d);
			StringBuilder sb = new StringBuilder();
			sb.append(p1.get(0));
			for(int i =1; i<p1.size(); i++){
				sb.append(" "+p1.get(i));
			}
			System.out.println(sb.toString());
			sb = new StringBuilder();
			sb.append(p2.get(0));
			for(int i = 1; i<p2.size(); i++){
				sb.append(" "+p2.get(i));
			}
			System.out.println(sb.toString());
		}
	}
}
