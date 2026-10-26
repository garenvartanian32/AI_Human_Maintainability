import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import static java.util.Comparator.*;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.next());
        int M = Integer.parseInt(sc.next());
        ArrayList<ArrayList<Long>> a = new ArrayList<>();
        for(int i = 0; i < N ;i++){
            ArrayList<Long> a2 = new ArrayList<>();
            a2.add(Long.parseLong(sc.next()));
            a2.add(Long.parseLong(sc.next()));
            a.add(a2);
        }
        a.sort(comparing(x->x.get(0)));
        long count = 0l;
        for(ArrayList<Long> ae : a){
            while(M > 0 && ae.get(1) > 0){
                count += ae.get(0);
                ae.set(1, ae.get(1)-1);
                M--;
            }
        }
        System.out.println(count);
    }

}