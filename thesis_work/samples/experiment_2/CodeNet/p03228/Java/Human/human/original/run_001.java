import java.util.Scanner;
public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int A = sc.nextInt();

		int B = sc.nextInt();

		int K = sc.nextInt();

		boolean ab = false;


		for(int i=0;i<K;i++) {

			if(ab == false) {	//A

				if(A%2!=0) A -= 1;

				int a = A/2;

				A -= a;

				B += a;

				ab = true;

			}else {

				if(B%2!=0)  B -= 1;

				int b = B/2;

				B -= b;

				A += b;

				ab = false;
			}
		}

		System.out.println(A+" "+B);

	}

}
