import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] vals = new int[20];
        for (int i = 0; i < 20; i++) vals[i] = sc.nextInt();
        Integer[] w = new Integer[10], k = new Integer[10];
        for (int i=0;i<10;i++) w[i]=vals[i];
        for (int i=0;i<10;i++) k[i]=vals[10+i];
        Arrays.sort(w, Collections.reverseOrder());
        Arrays.sort(k, Collections.reverseOrder());
        int sw = w[0]+w[1]+w[2];
        int sk = k[0]+k[1]+k[2];
        System.out.println(sw + " " + sk);
    }
}