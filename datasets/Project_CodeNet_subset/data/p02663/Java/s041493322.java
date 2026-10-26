import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Main{

    public static void main(String[] args){
        //START

        Scanner sc=new Scanner(System.in);
        int H1=sc.nextInt();
        int M1=sc.nextInt();
        int H2=sc.nextInt();
        int M2=sc.nextInt();
        int K=sc.nextInt();

        int h=(H2-H1)*60;
        int m=(M2-M1);

        int sum=h+m-K;

        System.out.println(sum);

        //END
    }
}

