import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.File;
import java.io.Writer;
import java.util.Collection;
import java.util.HashSet;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Queue;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author monsterspy
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		FastScanner in = new FastScanner(inputStream);
		Output out = new Output(outputStream);
		TaskC solver = new TaskC();
		solver.solve(1, in, out);
		out.close();
	}
}

class TaskC {

    class Pair implements Comparable<Pair>{

        long x;
        long y;
        long d;
        public Pair(long x, long y, long d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
        public int compareTo(Pair o) {
            return Long.compare(d,o.d);
        }
    }


    public void solve(int testNumber, FastScanner s, Output ww) {
        try{
            long x0 = s.nextLong();
            long y0 = s.nextLong();
            long x1 = s.nextLong();
            long y1 = s.nextLong();

            HashSet<Long> st = new HashSet<Long>();
            int n = s.nextInt();
            while(n-->0){
                long r = s.nextLong();
                long a = s.nextLong();
                long b = s.nextLong();
                for(long i=a;i<=b;i++) st.add((r<<32)+i);
            }


            Queue<Pair> p = new PriorityQueue<Pair>();
            p.add(new Pair(x0,y0,0));
            st.remove((x0<<32)+y0);

            while(!p.isEmpty()){
                Pair pair = p.poll();
                if(pair.x == x1 && pair.y == y1){
                    ww.printLine(pair.d);
                    return;
                }
                for(int dx= -1;dx <= 1;dx++){
                    for(int dy = -1;dy <= 1;dy++){
                        long x = pair.x+dx;
                        long y = pair.y+dy;
                        if(st.contains((x<<32)+y)){
                            st.remove((x<<32)+y);
                            p.add(new Pair(x,y,(pair.d+1)));
                        }
                    }
                }
            }
            ww.printLine(-1);


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

class FastScanner {
    BufferedReader s;
    StringTokenizer st;

    public FastScanner(InputStream InputStream){
        st = new StringTokenizer("");
        s = new BufferedReader(new InputStreamReader(InputStream));
    }

    public FastScanner(File f) throws FileNotFoundException{
        st = new StringTokenizer("");
        s = new BufferedReader (new FileReader(f));
    }

    public int nextInt() throws IOException{
        if(st.hasMoreTokens())
            return Integer.parseInt(st.nextToken());
        else{
            st = new StringTokenizer(s.readLine());
            return nextInt();
        }
    }

    public long nextLong() throws IOException{
        if(st.hasMoreTokens())
            return Long.parseLong(st.nextToken());
        else{
            st = new StringTokenizer(s.readLine());
            return nextLong();
        }
    }

}

class Output {

    private final PrintWriter writer;

    public Output(OutputStream outputStream) {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }

    public Output(Writer writer) {
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
