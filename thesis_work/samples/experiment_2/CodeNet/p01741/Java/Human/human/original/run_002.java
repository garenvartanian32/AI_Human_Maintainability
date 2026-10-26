import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		double d = in.nextDouble();
		double cand1 = Math.sqrt(Math.pow(d,2) / 2) * 2;
		double cand2 = Double.MIN_VALUE;
		if(d<=Math.sqrt(Math.pow((int)d,2)+1)){
			cand2 = (int)d + 1;
		}
		System.out.println(Math.max(cand1,cand2));
	}
}