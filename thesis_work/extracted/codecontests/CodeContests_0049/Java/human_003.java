public class Main{
  public void run(java.io.InputStream in, java.io.PrintStream out){
    java.util.Scanner sc = new java.util.Scanner(in);
/*answer*/
    int n, i, j, count, tmp;
    int[] a;

    for(;;){
      n = sc.nextInt();
      if(n == 0)break;
      a = new int[n];
      for(i = 0;i < n;i++)a[i] = sc.nextInt();
      count = 0;

      for(i = 0;i < n - 1;i++)for(j = 0;j < n - 1 - i;j++)if(a[j] > a[j + 1]){
        tmp = a[j]; a[j] = a[j + 1]; a[j + 1] = tmp; count++;
      }

      out.println(count);
    }

    sc.close();
  }
  public static void main(String[] args){
    (new Main()).run(System.in, System.out);
  }
}