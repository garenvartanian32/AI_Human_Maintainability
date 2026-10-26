import java.io.IOException;
import java.io.InputStream;
import java.util.*;
 
public class Main implements Runnable {
	
    public static void main(String[] args) {
    	new Thread(null, new Main(), "", 16 * 1024 * 1024).start();
    }
    
    public void run() {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        
        ArrayList<Point> l = new ArrayList<>();
        for(int i=0;i<n;i++){
        	double x = sc.nextDouble();
        	double y = sc.nextDouble();
        	Point p = new Point(x,y);
        	l.add(p);
        }
        ConvexPolygon cp = new ConvexPolygon(l);
        
        System.out.println(cp.diameter());
    }

}

class ConvexPolygon {
	ArrayList<Point> cp; //反時計周り
	
	//既に凸包で反時計回りのリストから作成
	public ConvexPolygon(ArrayList<Point> cp){
		this.cp = cp;
	}
	
	//与えられた点のリストから凸包を作る
	//x最小の中でy最小の頂点から反時計回り
	static ConvexPolygon makeConvexPolygon(ArrayList<Point> l){
		Collections.sort(l);
		
		ArrayList<Point> up = new ArrayList<>(); //上側
		ArrayList<Point> down = new ArrayList<>(); //下側
		
		up.add(l.get(0));
		up.add(l.get(1));
		down.add(l.get(0));
		down.add(l.get(1));
		
		for(int i=2;i<l.size();i++){
			Point now = l.get(i);
			
			boolean con = true;
			while(con){
				Point p0 = down.get(down.size()-2);
				Point p1 = down.get(down.size()-1);
				int d = direction(p0,p1,now);
				if(d==2){ //p1が凹になる
					down.remove(down.size()-1);
					if(down.size()==1){
						con =false;
					}
				}
				else{
					con = false;
				}
			}
			
			down.add(now);
		}
		
		for(int i=2;i<l.size();i++){
			Point now = l.get(i);
			
			boolean con = true;
			while(con){
				Point p0 = up.get(up.size()-2);
				Point p1 = up.get(up.size()-1);
				int d = direction(p0,p1,now);
				if(d==1){ //p1が凹になる
					up.remove(up.size()-1);
					if(up.size()==1){
						con =false;
					}
				}
				else{
					con = false;
				}
			}
			
			up.add(now);
		}
		
		up.remove(0);
		up.remove(up.size()-1);
		Collections.reverse(up);
		down.addAll(up);
		
		return new ConvexPolygon(down);
	}
	
	//最遠頂点対間距離
	double diameter(){
		//まずx軸方向の最遠点対をとる
		double minx = Double.POSITIVE_INFINITY;
		int minxid = 0;
		double maxx = Double.NEGATIVE_INFINITY;
		int maxxid = 0;
		
		for(int i=0;i<cp.size();i++){
			double x = cp.get(i).x;
			if(x<minx){
				minx = x;
				minxid = i;
			}
			else if(x>maxx){
				maxx = x;
				maxxid = i;
			}
		}
		
		int pid = minxid;
		int qid = maxxid;
		
		double diamax = d2(cp.get(pid),cp.get(qid));
		
		boolean con = true;
		while(con){
			double x1 = cp.get(next(pid)).x - cp.get(pid).x;
			double y1 = cp.get(next(pid)).y - cp.get(pid).y;
			double x2 = cp.get(next(qid)).x - cp.get(qid).x;
			double y2 = cp.get(next(qid)).y - cp.get(qid).y;
			double det = det(x1,y1,x2,y2);
			if(det<0){
				pid = next(pid);
			}
			else{
				qid = next(qid);
			}
			diamax = Math.max(diamax,d2(cp.get(pid),cp.get(qid)));
			if(qid==next(minxid) || pid ==next(maxxid)){
				con = false;
			}
		}
		
		return Math.sqrt(diamax);
	}
	//反時計回りに1つ進んだ頂点のindex
	private int next(int id){
		int size = cp.size();
		if(id<=size-2){
			return id+1;
		}
		else{
			return 0;
		}
	}
	//ベクトルのdet
	static double det(double x1, double y1, double x2, double y2){
		return x1*y2 -x2*y1;
	}
	
	//p0を起点に、p1へのベクトルからみてp2へのベクトルが反時計回りなら1、時計回りなら2
	//↑が同一直線状にあるとき、→p2が→p1と反対側なら3、→p2が→p1の延長線上なら4、→p2が→p1上にあれば5を返す
	static int direction(Point p0, Point p1, Point p2) {
		final double allowableERROR = 0.00000001;
		if(p0.x == p1.x) {
			if(p2.x == p0.x) {
				if((p0.y <= p2.y && p2.y <= p1.y) || (p1.y <= p2.y && p2.y <= p0.y)) {
					return 5;
				}else if (d2(p0,p2) < d2(p1,p2)) {
					return 3;
				}else {
					return 4;
				}
			}else if ((p0.y < p1.y && p2.x < p0.x) || (p0.y > p1.y && p2.x > p0.x)) {
				return 1;
			}else {
				return 2;
			}

		}else{
			//y=ax+b上にp0,p1が存在
			double a = (p1.y - p0.y) / (p1.x - p0.x);
			double b = p1.y - a * p1.x;
			if(Math.abs(p2.y - (a * p2.x + b)) < allowableERROR) {
				if((p0.x <= p2.x && p2.x <= p1.x) || (p1.x <= p2.x && p2.x <= p0.x)) {
					return 5;
				}else if (d2(p0,p2) < d2(p1,p2)) {
					return 3;
				}else {
					return 4;
				}
			}else if ((p0.x < p1.x && p2.y - (a * p2.x + b) > 0) || (p0.x > p1.x && p2.y - (a * p2.x + b) < 0)) {
				return 1;
			}else {
				return 2;
			}
		}
	}
	//ユークリッド距離の2乗
	static double d2(Point p1, Point p2){
		return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
	}

}


class Point implements Comparable<Point>{
	double x,y;
	
	public Point(double a, double b){
		this.x = a;
		this.y = b;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Point o){
		if(x!=o.x){
			return Double.compare(x,o.x);
		}
		return Double.compare(y, o.y);
	}
		
}

class FastScanner {
	private final InputStream in = System.in;
	private final byte[] buffer = new byte[1024];
	private int ptr = 0;
	private int buflen = 0;
	private boolean hasNextByte() {
		if (ptr < buflen) {
			return true;
		} else {
			ptr = 0;
			try {
				buflen = in.read(buffer);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (buflen <= 0) {
				return false;
			}
		}
		return true;
	}
	private int readByte() {
		if (hasNextByte())
			return buffer[ptr++];
		else
			return -1;
	}
	private static boolean isPrintableChar(int c) {
		return 33 <= c && c <= 126;
	}
	public boolean hasNext() {
		while (hasNextByte() && !isPrintableChar(buffer[ptr]))
			ptr++;
		return hasNextByte();
	}
	public String next() {
		if (!hasNext())
			throw new NoSuchElementException();
		StringBuilder sb = new StringBuilder();
		int b = readByte();
		while (isPrintableChar(b)) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}
	public long nextLong() {
		if (!hasNext())
			throw new NoSuchElementException();
		long n = 0;
		boolean minus = false;
		int b = readByte();
		if (b == '-') {
			minus = true;
			b = readByte();
		}
		if (b < '0' || '9' < b) {
			throw new NumberFormatException();
		}
		while (true) {
			if ('0' <= b && b <= '9') {
				n *= 10;
				n += b - '0';
			} else if (b == -1 || !isPrintableChar(b)) {
				return minus ? -n : n;
			} else {
				throw new NumberFormatException();
			}
			b = readByte();
		}
	}
	public int nextInt() {
		long nl = nextLong();
		if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE)
			throw new NumberFormatException();
		return (int) nl;
	}
	public int[] nextIntArray(int n){
		int[] a = new int[n];
		for(int i=0;i<n;i++){
			a[i] = nextInt();
		}
		return a;
	}
	public Integer[] nextIntegerArray(int n){
		Integer[] a = new Integer[n];
		for(int i=0;i<n;i++){
			a[i] = nextInt();
		}
		return a;
	}
	public double nextDouble() {
		return Double.parseDouble(next());
	}
}
