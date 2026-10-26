import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.StringTokenizer;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author Lokesh Khandelwal
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		OutputWriter out = new OutputWriter(outputStream);
		TaskC solver = new TaskC();
		solver.solve(1, in, out);
		out.close();
	}
}

class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt(), m = in.nextInt(), i, j;
        int q[][] = new int[m][4];
        long a[] = new long[n];

        for(i=0;i<m;i++) {
            for(j=0;j<4;j++)
                q[i][j] = in.nextInt();

        }
        Arrays.fill(a, (long)1e18);
        boolean isUpdated[] = new boolean[n], status = true;
        for(i=m-1;i>=0;i--) {
            int l = q[i][1] - 1;
            int r = q[i][2] - 1;
            if(q[i][0] == 1) {
                for(j=l;j<=r;j++) {
                    a[j] -= q[i][3];
                }

            }
            else {
                for(j=l;j<=r;j++) {
                    a[j] = Math.min(a[j], q[i][3]);
                }
            }

        }
        long b[] = new long[n+1];

        for(i=0;i<n;i++)
            b[i] = a[i];
        //DebugUtils.print(a);
        for(i=0;i<m;i++) {
            int l = q[i][1] - 1, r = q[i][2] - 1;
            if(q[i][0] == 1) {
                for(j=l;j<=r;j++) {
                    a[j] += q[i][3];
                }
            }
            else {
                long max = -(long )1e18;
                for(j=l;j<=r;j++)
                    max = Math.max(max, a[j]);
                if(max != q[i][3]) {
                    status = false;
                    break;
                }
            }
            if(!status) break;
        }
        if(!status) out.printLine("NO");
        else {
            out.printLine("YES");
            for(i=0;i<n;i++)
                out.print(Math.min(b[i], (long)1e9)+" ");
            out.printLine();
        }
    }
}

class InputReader
{
    BufferedReader in;
    StringTokenizer tokenizer=null;

    public InputReader(InputStream inputStream)
    {
        in=new BufferedReader(new InputStreamReader(inputStream));
    }
    public String next()
    {
        try{
            while (tokenizer==null||!tokenizer.hasMoreTokens())
            {
                tokenizer=new StringTokenizer(in.readLine());
            }
            return tokenizer.nextToken();
        }
        catch (IOException e)
        {
            return null;
        }
    }
    public int nextInt()
    {
        return Integer.parseInt(next());
    }
    }

class OutputWriter {
	private final PrintWriter writer;

	public OutputWriter(OutputStream outputStream) {
		writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
	}

	public OutputWriter(Writer writer) {
		this.writer = new PrintWriter(writer);
	}

	public void print(Object...objects) {
		for (int i = 0; i < objects.length; i++) {
			if (i != 0)
				writer.print(' ');
			writer.print(objects[i]);
		}
	}

	public void printLine(Object...objects) {
		print(objects);
		writer.println();
	}

	public void close() {
		writer.close();
	}
}

