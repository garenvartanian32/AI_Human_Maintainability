import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class codeforces 
{
	public static void main( String[] args ) throws IOException
	{
		Reader.init(System.in);
		int query = Reader.nextInt();
		for( int q =0 ; q < query ; q++ )
		{
			long l = Reader.nextLong();
			long r = Reader.nextLong();
			long d = Reader.nextLong();
			long res = 0;
			if ( d == 1 && l != 1 )
			{
				System.out.println(1);
			}
			else if ( d < l || d > r)
			{
				System.out.println(d);
			}
			else
			{
				long r1 = ((r/d)+1)*d;
				System.out.println(r1);
			}
		}
	}
}

class Reader 
{
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    static void init(InputStream input) 
    {
        reader = new BufferedReader(new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }
   
    static String next() throws IOException 
    {
        while ( ! tokenizer.hasMoreTokens() ) 
        {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException 
    {
        return Integer.parseInt( next() );
    }
    
    static double nextDouble() throws IOException 
    {
        return Double.parseDouble( next() );
    }
    
    static long nextLong() throws IOException 
    {
        return Long.parseLong( next() );
    }
}