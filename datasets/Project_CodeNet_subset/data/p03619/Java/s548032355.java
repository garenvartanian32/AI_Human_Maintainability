import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		StdIn in = new StdIn();
		int x1=in.nextInt(), y1=in.nextInt(), x2=in.nextInt(), y2=in.nextInt(), n=in.nextInt();
		if(x2<x1) {
			int temp=x1;
			x1=x2;
			x2=temp;
			temp=y1;
			y1=y2;
			y2=temp;
		}
		int minY=Math.min(y1, y2), maxY=Math.max(y1, y2);
		List<Point> ptsList = new ArrayList<Point>();
		for(int i=0; i<n; ++i) {
			int xi=in.nextInt(), yi=in.nextInt();
			if(xi<x1||xi>x2||yi<minY||yi>maxY)
				continue;
			ptsList.add(new Point(xi, yi));
		}
		if(ptsList.size()==0) {
			System.out.printf("%.12f\n", (x2-x1+Math.abs(y1-y2))*100.);
			return;
		}
		if(x1==x2||y1==y2) {
			System.out.printf("%.12f\n", (x2-x1+Math.abs(y1-y2))*100L+10*Math.PI-20);
			return;
		}
		ptsList.sort(new Comparator<Point>() {
			public int compare(Point a, Point b) {
				return a.x-b.x;
			}
		});
		int[] a = new int[ptsList.size()];
		int sign=y2-y1>0?1:-1;
		for(int i=0; i<a.length; ++i)
			a[i]=ptsList.get(i).y*sign;
		double t=(x2-x1+Math.abs(y1-y2))*100L+(5*Math.PI-20)*LongestIncreasingSubsequenceLength(a, a.length);
		/*boolean addExtra1=true, addExtra2=true;
		for(int i=0; i<a.length-1; ++i) {
			if(a[i+1]-a[i]!=1) {
				addExtra1=false;
				break;
			}
		}
		for(int i=0; i<a.length-1; ++i) {
			if(ptsList.get(i+1).x-ptsList.get(i).x!=1) {
				addExtra2=false;
				break;
			}
		}
		//while(addExtra2&&x1==ptsList.get(0).x&&x2==ptsList.get(ptsList.size()-1).x);
		if(addExtra1&&y1==sign*a[0]&&y2==sign*a[a.length-1]||addExtra2&&x1==ptsList.get(0).x&&x2==ptsList.get(ptsList.size()-1).x)
			if(LongestIncreasingSubsequenceLength(a, a.length)!=Math.min(x2-x1, Math.abs(y1-y2))+1)
				throw new RuntimeException();
			else
				t+=Math.PI*5;*/
		if(LongestIncreasingSubsequenceLength(a, a.length)==Math.min(x2-x1, Math.abs(y1-y2))+1)
			t+=Math.PI*5;
		System.out.printf("%.12f\n", t);
	}
	
	static int CeilIndex(int A[], int l, int r, int key)
    {
        while (r - l > 1)
        {
            int m = l + (r - l)/2;
            if (A[m]>=key)
                r = m;
            else
                l = m;
        }
 
        return r;
    }
 
    static int LongestIncreasingSubsequenceLength(int A[], int size)
    {
        // Add boundary case, when array size is one
 
        int[] tailTable   = new int[size];
        int len; // always points empty slot
 
        tailTable[0] = A[0];
        len = 1;
        for (int i = 1; i < size; i++)
        {
            if (A[i] < tailTable[0])
                // new smallest value
                tailTable[0] = A[i];
 
            else if (A[i] > tailTable[len-1])
                // A[i] wants to extend largest subsequence
                tailTable[len++] = A[i];
 
            else
                // A[i] wants to be current end candidate of an existing
                // subsequence. It will replace ceil value in tailTable
                tailTable[CeilIndex(tailTable, -1, len-1, A[i])] = A[i];
        }
 
        return len;
    }
	
	interface Input {
		public String next();
		public String nextLine();
		public int nextInt();
		public long nextLong();
		public double nextDouble();
	}
	static class StdIn implements Input {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public StdIn() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}
		
		public StdIn(String filename) {
			try{
				din = new DataInputStream(new FileInputStream(filename));
			} catch(Exception e) {
				throw new RuntimeException();
			}
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}
		
		public String next() {
			int c;
			while((c=read())!=-1&&(c==' '||c=='\n'||c=='\r'));
			StringBuilder s = new StringBuilder();
			while (c != -1)
			{
				if (c == ' ' || c == '\n'||c=='\r')
					break;
				s.append((char)c);
				c=read();
			}
			return s.toString();
		}

		public String nextLine() {
			int c;
			while((c=read())!=-1&&(c==' '||c=='\n'||c=='\r'));
			StringBuilder s = new StringBuilder();
			while (c != -1)
			{
				if (c == '\n'||c=='\r')
					break;
				s.append((char)c);
				c = read();
			}
			return s.toString();
		}

		public int nextInt() {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do
			{
				ret = ret * 10 + c - '0';
			}  while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}
		
		public int[] readIntArray(int n) {
			int[] ar = new int[n];
			for(int i=0; i<n; ++i)
				ar[i]=nextInt();
			return ar;
		}

		public long nextLong() {
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			}
			while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}
		
		public long[] readLongArray(int n) {
			long[] ar = new long[n];
			for(int i=0; i<n; ++i)
				ar[i]=nextLong();
			return ar;
		}

		public double nextDouble() {
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			}
			while ((c = read()) >= '0' && c <= '9');

			if (c == '.')
			{
				while ((c = read()) >= '0' && c <= '9')
				{
					ret += (c - '0') / (div *= 10);
				}
			}

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() {
			try{
				if (bufferPointer == bytesRead)
					fillBuffer();
				return buffer[bufferPointer++];
			} catch(IOException e) {
				throw new RuntimeException();
			}
		}

		public void close() throws IOException {
			if (din == null)
				return;
			din.close();
		}
	}
}