import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	double a, b;
	double S;
	final double t = 3.305785;

	a = stdIn.nextInt();
	b = stdIn.nextInt();

	S = a * b / t;
	System.out.println(S);
    }
}