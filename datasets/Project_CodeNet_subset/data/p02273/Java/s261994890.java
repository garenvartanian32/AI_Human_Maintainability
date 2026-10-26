
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Point p1 = new Point(0.0, 0.0);
		Point p2 = new Point(100.0, 0.0);
		System.out.printf("%.8f %.8f\n", p1.getX(), p1.getY());
		kock(n, p1, p2);
		System.out.printf("%.8f %.8f\n", p2.getX(), p2.getY());
		sc.close();
	}

	static void kock(int n, Point p1, Point p2) {
		if(n == 0) {
			return;
		}

		//p1, p2からs,t,uを算出
		double s_x = (2 * p1.getX() + p2.getX()) / 3;
		double s_y = (2 * p1.getY() + p2.getY()) / 3;
		double t_x = (p1.getX() + 2 * p2.getX()) / 3;
		double t_y = (p1.getY() + 2 * p2.getY()) / 3;
		double u_x = (t_x - s_x) * Math.cos(Math.toRadians(60)) - (t_y - s_y) * Math.sin(Math.toRadians(60)) + s_x;
		double u_y = (t_x - s_x) * Math.sin(Math.toRadians(60)) + (t_y - s_y) * Math.cos(Math.toRadians(60)) + s_y;
		Point s = new Point(s_x, s_y);
		Point u = new Point(u_x, u_y);
		Point t = new Point(t_x, t_y);

		kock(n-1, p1, s);
		System.out.printf("%.8f %.8f\n", s.getX(), s.getY());
		kock(n-1, s, u);
		System.out.printf("%.8f %.8f\n", u.getX(), u.getY());
		kock(n-1, u, t);
		System.out.printf("%.8f %.8f\n", t.getX(), t.getY());
		kock(n-1, t, p2);
	}
}

class Point{
	private double x, y;
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public double getX() {return x;}
	public double getY() {return y;}
}

