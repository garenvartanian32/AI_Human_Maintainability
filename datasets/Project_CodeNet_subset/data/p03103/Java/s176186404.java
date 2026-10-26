import java.util.*;

public class Main{
  static long cost = 0;
  private int a;
  private int b;

  Main(int a, int b){
    this.a = a;
    this.b = b;
  }
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    int m = sc.nextInt();

    Main[] d = new Main[n];

    for(int i=0; i<n; i++){
      d[i] = new Main(sc.nextInt(),sc.nextInt());
    }
    //for(int i=0; i<n; i++){
      //System.out.println(d[i].a+","+d[i].b);
    //}
    Arrays.sort(d,new Comparator<Main>(){
      public int compare(Main o1,Main o2){
        return o1.a - o2.a;
      }
    });
    //for(int i=0; i<n; i++){
      //System.out.println(d[i].a+","+d[i].b);
    //}
    int j = 0;
    while(m>0){
      if(m-d[j].b>=0){
        cost += (long)d[j].a * (long)d[j].b;
        m -=  d[j].b;
      }else if(m-d[j].b<0){
        cost += (long)d[j].a * (long)m;
        m = 0;
      }
      j++;

    }
    System.out.println(cost);


  }

}