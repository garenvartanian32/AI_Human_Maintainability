import java.util.*;
public class cf260b {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String s = in.next().trim();
    ArrayList<String> dates = new ArrayList<String>();
    for(int i=0; i<=s.length() - 10; i++)
      if(isDate(s.substring(i,i+10)))
          dates.add(s.substring(i,i+10));
    Collections.sort(dates);
    String bestDate = "";
    int bestCount = 0;
    int last = 0;
    for(int i=0; i<=dates.size(); i++) {
      if(i == dates.size() || !dates.get(i).equals(dates.get(last))) {
        int count = i-last;
        if(count > bestCount) {
          bestDate = dates.get(last);
          bestCount = count;
        }
        last = i;
      }
    }
    System.out.println(bestDate);
  }
  static boolean isDate(String s) {
    for(int i=0; i<s.length(); i++)
      if(i == 2 || i == 5) {
        if(s.charAt(i) != '-')
          return false;
      }
      else {
        if(!Character.isDigit(s.charAt(i)))
          return false;
      }
    int b = Integer.parseInt(s.substring(0,2));
    int a = Integer.parseInt(s.substring(3,5));
    int c = Integer.parseInt(s.substring(6));
    if(b <= 0) return false;
    if(c < 2013 || c > 2015) return false;
    switch(a) {
      case 1: 
        return b <= 31;
      case 2:
        return b <= 28;
      case 3:
        return b <= 31;
      case 4:
        return b <= 30;
      case 5:
        return b <= 31;
      case 6:
        return b <= 30;
      case 7:
        return b <= 31;
      case 8:
        return b <= 31;
      case 9:
        return b <= 30;
      case 10:
        return b <= 31;
      case 11:
        return b <= 30;
      case 12:
        return b <= 31;
      default:
        return false;
    }
  }
}
