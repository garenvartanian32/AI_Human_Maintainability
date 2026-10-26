import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;
public class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		Point[] p=new Point[n];
		int count=0;
		long ans=0;
		for(int i = 0; i<n; i++){
			long a=sc.nextLong();
			int b = sc.nextInt();
			p[i]=new Point(a,b);
		}
		Arrays.sort(p,Comparator.comparing(Point::getX));
		while(m>0){
			if(p[count].y<m){
				m-=p[count].y;
				ans+=p[count].x*p[count].y;
			}else{
				ans+=p[count].x*m;
				m=0;
			}
			count++;
		}
		System.out.println(ans);
	}
	static class Point{
		long x;
		int y;
		public Point(long x, int y){
			this.x=x;
			this.y=y;
		}
		
		public long getX(){
			return x;
		}
		 
		public int getY(){
			return y;
		}
	}
}