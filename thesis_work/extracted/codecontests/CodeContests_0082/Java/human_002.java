import java.util.*;
import java.io.*;
public class Main{
static Scanner sc;// = new Scanner(System.in);
  static PrintStream ps;
  public static void main(String[] args)throws FileNotFoundException{
    sc = new Scanner( System.in );
    ps = System.out;
    long count = 0;
    long i = 0;
    while ( sc.hasNext() ) {
      String s = sc.nextLine();
      if(s.charAt(0) == '+')
        ++i;
      else if(s.charAt(0) == '-')
        --i;
      else
        count+= i* s.substring( s.indexOf(':')+1 ).length();
      //s = sc.nextLine();
    }
    ps.println(count);
  }
}

 	 	 		 		    		 	  	   	  		