import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    
    
    int a,b,c;
 
    Scanner sc = new Scanner(System.in);
    a = sc.nextInt();
    b = sc.nextInt();
    
	double doubleA;
    double doubleB;
    double doubleC;
      
    doubleA = (double) a;
    doubleB = (double) b;
    doubleC = Math.ceil( (doubleB - 1.0 ) / (doubleA - 1.0 ) );
    c = (int) doubleC;
    System.out.println(c);
    
    return;
  }
}

    
    
    