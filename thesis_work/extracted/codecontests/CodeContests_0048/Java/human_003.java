import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
public class Main {
	static String a;
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		while(stdIn.hasNext()) {
			String[] tmp = stdIn.nextLine().split(",");
			double x1 = Double.parseDouble(tmp[0]);
			double y1 = Double.parseDouble(tmp[1]);
			double x2 = Double.parseDouble(tmp[2]);
			double y2 = Double.parseDouble(tmp[3]);
			double x3 = Double.parseDouble(tmp[4]);
			double y3 = Double.parseDouble(tmp[5]);
			double x4 = Double.parseDouble(tmp[6]);
			double y4 = Double.parseDouble(tmp[7]);
			double s1 = heron(x1,y1,x2,y2,x4,y4) + heron(x2,y2,x3,y3,x4,y4);
 			double s2 = heron(x1,y1,x2,y2,x3,y3) + heron(x1,y1,x3,y3,x4,y4);

 			if(Math.abs(s2 - s1) <= 1.0e-10) {
 				System.out.println("YES");
 			}
 			else {
 				System.out.println("NO");
 			}
		}
	}
	//??¢?????????
		public static double heron(double x1, double y1, double x2, double y2, double x3, double y3) {
			double ab,bc,ca;
		    double S,s;
		    ab=Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
		    bc=Math.sqrt((x2-x3)*(x2-x3)+(y2-y3)*(y2-y3));
		    ca=Math.sqrt((x3-x1)*(x3-x1)+(y3-y1)*(y3-y1));
		    s=0.5*(ab+bc+ca);
		    S=Math.sqrt(s*(s-ab)*(s-bc)*(s-ca));
		    return S;
		}
	
}