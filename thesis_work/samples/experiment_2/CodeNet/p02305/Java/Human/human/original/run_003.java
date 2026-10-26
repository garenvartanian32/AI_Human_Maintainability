import java.util.Scanner;

class problemA{

  void solve(){
    int c1x=0, c1y=0, c1r=0;
    int c2x=0, c2y=0, c2r=0;
    int length = 0;//length of between center coordinate of c1 and c2
    int large = 0, small = 0;

    Scanner scan = new Scanner(System.in);
    c1x = scan.nextInt();
    c1y = scan.nextInt();
    c1r = scan.nextInt();

    c2x = scan.nextInt();
    c2y = scan.nextInt();
    c2r = scan.nextInt();

    large = c1r;
    small = c2r;
    if(large < small){
      large = c2r;
      small = c1r;
    }

    length = (int)(Math.sqrt((c2x-c1x)*(c2x-c1x) + (c2y-c1y)*(c2y-c1y)));

    if(length > (c1r + c2r)) System.out.println("4");
    else if (length < (c1r + c2r)){
        if(large == (length + small)) System.out.println("1");
        else if(large > (length + small))System.out.println("0");
        else System.out.println("2");
    }
    else System.out.println("3");
  }
}

public class Main{
  public static void main(String[] args){
    new problemA().solve();
  }
}

