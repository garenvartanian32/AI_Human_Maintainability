import java.util.Scanner;
 
public class Main {
 
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int N = scanner.nextInt();
            long [] A = new long[N];
            for (int i=0; i<N; i++)
                A[i] = scanner.nextLong();
            long [] B = new long[N];
            for (int i=0; i<N; i++)
                B[i] = scanner.nextLong();
            long Pos=0;
            long Neg=0;
            for (int i=0; i<N; i++){
                long s=A[i]-B[i];
                if (s < 0){
                    Neg +=s/2;
                } else {
                    Pos +=s;
                }
            }
            long Judge = Neg + Pos;
            if (Judge > 0) {
                System.out.println("No");
            } else {
                System.out.println("Yes");
            } 
      }
}