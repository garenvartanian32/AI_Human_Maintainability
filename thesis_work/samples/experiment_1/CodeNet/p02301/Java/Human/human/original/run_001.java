public class Main{
  public void run(java.io.InputStream in, java.io.PrintStream out){
    java.util.Scanner sc = new java.util.Scanner(in);
/*answer*/
    int n;
    double[] x, y;
    int i, j;
    double s1x, s1y, s2x, s2y, sjx, sjy, max, l;

    n = sc.nextInt();
    x = new double[n];
    y = new double[n];

    for(i = 0;i < n;i++){
      x[i] = sc.nextDouble();
      y[i] = sc.nextDouble();
    }

    max = 0.0;
    for(i = 0;i < n;i++){
      s1x = y[(i - 1 + n) % n] - y[i];
      s1y =  - x[(i - 1 + n) % n] + x[i];
      s2x =  - y[(i + 1) % n] + y[i];
      s2y = x[(i + 1) % n] - x[i];
      for(j = i + 1;j < n;j++){
        if(s1x * s2y - s2x * s1y == 0.0)break;
        sjx = x[j] - x[i];
        sjy = y[j] - y[i];
        if((s1x * sjy - sjx * s1y) * (s2x * sjy - s2y * sjy) <= 0){
          l = sjx * sjx + sjy * sjy;
          if(l > max)max = l;
        }
      }
    }

    System.out.println(Math.sqrt(max));

/*fin*/
    sc.close();
  }
  public static void main(String[] args){
    (new Main()).run(System.in, System.out);
  }
}