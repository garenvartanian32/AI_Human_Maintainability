import java.util.*;
public class Problem48 {

    public static void main(final String args[]) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List<Integer> arr = new ArrayList<Integer>();
        int c = 0, o = 0, e = 0;
        for(int i = 0; i < n; i++) {
            int a = sc.nextInt(); 
            a /= 100;
            if(a%2 == 0) {
                e++;
            }
            else {
                o++;
            }
            arr.add(a);
            //System.out.println("aux "+c);
        }

        int k = e*2 + o;

        if(k % 2 != 0) {
            System.out.println("NO");
            return;
        }

        Collections.sort(arr);

        int i1 = 0, m = 0;
        while(m < k/2) {

            m += arr.get(i1++);
            //System.out.println("AUX "+m);
        }
//System.out.println("AUX  aa "+m);
        if(m == k/2) {
            System.out.println("YES");
        }
        else {
            if(o == 0) {
                System.out.println("NO");
            }
            else {
                System.out.println("YES");   
            }
        }

        
        /*else if((e*2) <= (k/2)) {
            System.out.println("YES");
        }
        else {
            if(o == 0 && e%2 ==0) {
                System.out.println("NO");    
            }
            else {
                System.out.println("YES");
            }
        }*/
/*
        if(c == 0 || (o%2 == 0 && e%2 == 0) || (e*2 == o)) {
            
        }
        else {
            System.out.println("NO");
        }*/
        
    }
}