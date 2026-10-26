import java.util.Scanner;

class Main{
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int N = Integer.parseInt(scanner.next());
    long[] input = new long[N];
    boolean is0 = false;
    for(int i=0; i<N; i++){
      input[i] = Long.parseLong(scanner.next());
      if(input[i] == 0){
        System.out.println(0);
        is0 = true;
        break;
      }
    }
    if(!is0){
      long ans = 1l;
      long judge = 1000000000000000000l;
      double now_pow = 0d;
      for(int i=0; i<N; i++){
        if(now_pow+Math.log10(input[i])<=18){
          ans *= input[i];
          now_pow = Math.log10(ans);
          if(ans > judge){
            System.out.println(-1);
            break;
          }else if(i == N-1){
            System.out.println(ans);
          }
        }else{
          System.out.println(-1);
          break;
        }
      }
    }
  }
}
