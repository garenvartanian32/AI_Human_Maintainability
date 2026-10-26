import java.util.Scanner;
public class Main {
    public static void main (String[] args) {
	Scanner scan = new Scanner(System.in);
	double[] data = new double[8];
	String[] input;
	while (scan.hasNext()) {
	    input = scan.nextLine().split(",");
	    for (int i = 0; i < 8; i++) {
		data[i] = Double.parseDouble(input[i]);
	    }
	    if (isConvex(data)) {
		System.out.println("NO");
	    } else {
		System.out.println("YES");
	    }
	}
    }
    static boolean isConvex (double[] data) {
	double xa = data[0];
	double ya = data[1];
	double xb = data[2];
	double yb = data[3];
	double xc = data[4];
	double yc = data[5];
	double xd = data[6];
	double yd = data[7];
	double tb = closs (xa, ya, xc, yc, xb, yb);
	double td = closs (xa, ya, xc, yc, xd, yd);
	double ta = closs (xb, yb, xd, yd, xa, ya);
	double tc = closs (xb, yb, xd, yd, xc, yc);
	if (tb * td > 0.0 || ta * tc > 0.0) {
	    return true;
	} else {
	    return false;
	}
    }
    static double closs (double xa, double ya, double xb, double yb, double xc, double yc) {
	return (xa - xb) * (yc - ya) + (ya - yb) * (xa - xc);
    }
    
}