import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main
{
  public static void main(String[] args) throws IOException
  {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    int counter = 0;

    while (input.ready())
    {
      String[] inputStr = input.readLine().split(" ");

      if (inputStr[0].equals("0") && inputStr[1].equals("0"))
      {
        break;
      }

      if (counter > 0)
      {
        System.out.println();
      }
      counter++;

      ArrayList<Integer> ansList = leapYearCount(Integer.parseInt(inputStr[0]),
          Integer.parseInt(inputStr[1]));

      if (ansList.size() == 0)
      {
        System.out.println("NA");
        continue;
      }

      for (Integer each : ansList)
      {
        System.out.println(each);
      }
    }

  }

  private static ArrayList<Integer> leapYearCount(int fromYear, int toYear)
  {
    ArrayList<Integer> yearList = new ArrayList<>();
    for (int i = fromYear; i <= toYear; i++)
    {
      if (isLeapYear(i))
      {
        yearList.add(i);
      }
    }
    return yearList;
  }

  private static boolean isLeapYear(int year)
  {
    if (year % 4 != 0)
    {
      return false;
    }
    
    if (year % 400 == 0)
    {
      return true;
    }

    if (year % 100 == 0)
    {
      return false;
    }
    
    return true;
  }
}