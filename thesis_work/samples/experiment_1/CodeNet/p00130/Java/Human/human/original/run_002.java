import java.util.*;
import java.io.*;
import static java.lang.Math.*;

class Main {

    public static void main( final String[] args ) {

	final Scanner stdin = new Scanner( System.in );
	final int n = Integer.parseInt( stdin.nextLine() );

	for ( int t = 0; t < n; t++ ) {

	    final String input = stdin.nextLine();
	    final char[] train = new char[53];
	    int index = 26;
	    train[index] = input.charAt( 0 );
	    
	    for ( int i = 1; i < ( input.length() + 2 ) / 3; i++ ) {
		if ( input.substring( i * 3 - 2, i * 3 ).equals( "->" ) ){
		    index++;
		} else {
		    index--;
		}
		train[index] = input.charAt( i * 3 );
	    }
	    
	    index = 0;
	    while ( train[index] == 0 ) {
		index++;
	    }
	    while ( train[index] > 0 ) {
		System.out.print( train[index] );
		index++;
	    }
	    System.out.println();
	}	
    }    
}