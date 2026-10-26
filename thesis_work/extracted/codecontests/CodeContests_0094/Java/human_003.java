import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
public class main{
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
         Map<String,Integer> m = new HashMap<>();
                    sc.nextInt();
//                    m.put("ASD",5);
//                    m.replace("ASD",6);
//                    System.out.println(m.toString());
                    String s = sc.next();
                    String max="";
                    int smax=0;
                    for(int i=0;i<s.length()-1;i++){
                        if(m.containsKey(s.charAt(i)+""+s.charAt(i+1))){
                            int n = m.get(s.charAt(i)+""+s.charAt(i+1));
                            m.replace(s.charAt(i)+""+s.charAt(i+1),n+1);
                            if(n+1>smax) {
                                smax=n+1;
                                max=s.charAt(i)+""+s.charAt(i+1);
                            }
                        }
                        else m.put(s.charAt(i)+""+s.charAt(i+1), 1);
                    }
                                        if(max.equals("")) max = s.charAt(0)+""+s.charAt(1);
                    System.out.println(max);
    }
}