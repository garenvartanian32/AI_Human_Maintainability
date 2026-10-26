/**
 * DA-IICT
 * Author : PARTH PATEL
 */
import java.io.*;
import java.math.*;
import java.util.*;

import static java.util.Arrays.fill;
import static java.lang.Math.*;
import static java.util.Arrays.sort;
import static java.util.Collections.sort;


public class C578 {

	public static int mod = 1000000007;
	static FasterScanner in = new FasterScanner();
	static PrintWriter out = new PrintWriter(System.out);
	static int n;
	static long[] arr;
	public static double f(double x)
	{
		double[] temparr1=new double[n];
		for(int i=0;i<n;i++)
		{
			temparr1[i]=arr[i]-x;
		}
		double maxsum1=0;
		double tempsum1=0;
		for(int i=0;i<n;i++)
		{
			tempsum1+=temparr1[i];
			if(tempsum1<0)
			{
				tempsum1=0;
			}
			maxsum1=max(maxsum1, tempsum1);
		}
		double[] temparr2=new double[n];
		for(int i=0;i<n;i++)
		{
			temparr2[i]=-1*temparr1[i];
		}
		double maxsum2=0;
		double tempsum2=0;
		for(int i=0;i<n;i++)
		{
			tempsum2+=temparr2[i];
			if(tempsum2<0)
			{
				tempsum2=0;
			}
			maxsum2=max(maxsum2, tempsum2);
		}
		return max(maxsum1, maxsum2);
	}
	public static void main(String[] args) {

		n=in.nextInt();
		arr=new long[n];
		for(int i=0;i<n;i++)
		{
			arr[i]=in.nextLong();
		}
		double l=-20000.0;
		double r=20000.0;
		double answer=f(0);
		int iteration=100;
		while(iteration-->0)
		{
			double m1=l+(r-l)/3;
			double m2=r-(r-l)/3;
			double fm1=f(m1);
			double fm2=f(m2);
			answer=min(answer, min(fm1,fm2));
			if(fm1<fm2)
			{
				r=m2;
			}
			else
			{
				l=m1;
			}
		}
		out.println(answer);
		out.close();

	}

	/////////////////SEGMENT TREE (BUILD-UPDATE-QUERY)/////////////////////////////
	/////////////////UPDATE FOLLOWING METHODS AS PER NEED//////////////////////////
	/*
	public static void buildsegmenttree(int node,int start,int end)
	{
		if(start==end)
		{
			// Leaf node will have a single element
			segmenttree[node]=arr[start];
		}
		else
		{
			int mid=start+(end-start)/2;
			// Recurse on the left child
			buildsegmenttree(2*node, start, mid);
			// Recurse on the right child
			buildsegmenttree(2*node+1, mid+1, end);
			// Internal node will have the sum of both of its children
			segmenttree[node]=segmenttree[2*node]+segmenttree[2*node+1];
		}
	}
	
	public static void updatesegmenttree(int node,int start,int end,int idx,int val)
	{
		if(start==end)
		{
			//Leaf Node
			arr[idx]+=val;
			segmenttree[node]+=val;
		}
		else
		{
			int mid=start+(end-start)/2;
			if(start<=idx && idx<=mid)
			{
				// If idx is in the left child, recurse on the left child
				updatesegmenttree(2*node, start, mid, idx, val);
			}
			else
			{
				// if idx is in the right child, recurse on the right child
				updatesegmenttree(2*node+1, mid+1, end, idx, val);
			}
			// Internal node will have the sum of both of its children
			segmenttree[node]=segmenttree[2*node]+segmenttree[2*node+1];
		}
	}
	
	public static long querysegmenttree(int node,int start,int end,int l,int r)
	{
		if(r<start || end<l)
		{
			// range represented by a node is completely outside the given range
			return 0;
		}
		if(l <= start && end <= r)
	    {
	        // range represented by a node is completely inside the given range
	        return segmenttree[node];
	    }
		// range represented by a node is partially inside and partially outside the given range
		int mid=start+(end-start)/2;
		long leftchild=querysegmenttree(2*node, start, mid, l, r);
		long rightchild=querysegmenttree(2*node+1, mid+1, end, l, r);
		return (leftchild+rightchild);
	}
	*/

	public static long pow(long x, long n, long mod) {
		long res = 1;
		for (long p = x; n > 0; n >>= 1, p = (p * p) % mod) {
			if ((n & 1) != 0) {
				res = (res * p % mod);
			}
		}
		return res;
	}

	public static long gcd(long n1, long n2) {
		long r;
		while (n2 != 0) {
			r = n1 % n2;
			n1 = n2;
			n2 = r;
		}
		return n1;
	}

	public static long lcm(long n1, long n2) {
		long answer = (n1 * n2) / (gcd(n1, n2));
		return answer;
	}

	static class FasterScanner {
		private byte[] buf = new byte[1024];
		private int curChar;
		private int snumChars;

		public int read() {
			if (snumChars == -1)
				throw new InputMismatchException();
			if (curChar >= snumChars) {
				curChar = 0;
				try {
					snumChars = System.in.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (snumChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		public String nextLine() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isEndOfLine(c));
			return res.toString();
		}

		public String nextString() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public long nextLong() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public int nextInt() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public int[] nextIntArray(int n) {
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = nextInt();
			}
			return arr;
		}

		public long[] nextLongArray(int n) {
			long[] arr = new long[n];
			for (int i = 0; i < n; i++) {
				arr[i] = nextLong();
			}
			return arr;
		}

		private boolean isSpaceChar(int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		private boolean isEndOfLine(int c) {
			return c == '\n' || c == '\r' || c == -1;
		}
	}

}
