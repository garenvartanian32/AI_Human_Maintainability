import java.util.Scanner;

class Main{
      public static void main(String args[]){
	Scanner sc = new Scanner(System.in);
	long a = sc.nextLong();
	long v = sc.nextLong();
	long b = sc.nextLong();
	long w = sc.nextLong();
	long t = sc.nextLong();
	boolean esc = false;
	if(a<=b){
	    if(a+t*v>=b+t*w){
		esc = true;
	    }
	}else{
	    if(a-t*v<=b-t*w){
		esc = true;
	    }
	}
	if(esc){
	    System.out.println("YES");
	}else{
	    System.out.println("NO");
	}
      }
}
