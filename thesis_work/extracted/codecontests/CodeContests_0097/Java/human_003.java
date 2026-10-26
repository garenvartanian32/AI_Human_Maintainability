import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int[] A = new int[N];
    int[] B = new int[N];
    int[] C = new int[N-1];

    int ans = 0;
    for (int i = 0; i < N ; i++) {
      A[i]=sc.nextInt()-1;
    }
    for (int i = 0; i < N ; i++) {
      B[i]=sc.nextInt();
      ans += B[i];
    }
    for (int i = 0; i < N-1 ; i++) {
      C[i]=sc.nextInt();
    }

    for (int i = 1; i < N; i++) {
      int curMinus1 =A[i]-1;
      int prev = A[i-1];
      if( curMinus1 == prev ){
        ans += C[prev];
      }
    }


    System.out.println(ans);

  }



}
