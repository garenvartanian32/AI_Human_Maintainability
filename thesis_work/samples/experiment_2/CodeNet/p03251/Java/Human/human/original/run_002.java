import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int N,M,x,y,X,Y;
        N=sc.nextInt();
        M=sc.nextInt();
        X=sc.nextInt();
        Y=sc.nextInt();
        int maxX=sc.nextInt();
        for(int i=0;i<N-1;i++){
            x=sc.nextInt();
            if(maxX<x)maxX=x;
        }
        int minY=sc.nextInt();
        for(int i=0;i<M-1;i++){
            y=sc.nextInt();
            if(minY>y)minY=y;
        }
        if(maxX>=minY||maxX>=Y||minY<=X){
            System.out.println("War");
        }else{
            System.out.println("No War");
        }
    }
}
