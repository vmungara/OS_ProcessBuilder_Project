package examples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.StringTokenizer;

public class AddLines
{
  public static void main(String[] args)
  {
    try
    {
/*      File currentDirectory = new File(new File(".").getAbsolutePath());
      File input = new File(currentDirectory, "numberdata.txt");

      Reader reader = new InputStreamReader(input);
      BufferedReader breader = new BufferedReader(new FileReader());*/
    	
    	Reader reader = new InputStreamReader(System.in);
        BufferedReader breader = new BufferedReader(reader);
        String lineRead = null;
        while ((lineRead = breader.readLine()) != null)
        {
        int sum = 0;
        StringTokenizer st = new StringTokenizer(lineRead);
        while (st.hasMoreTokens())
        {
          String nums = st.nextToken();
          try
          {
            int temp = Integer.parseInt(nums);
            sum += temp;
          }
          catch (NumberFormatException ex)
          {
            System.err.println("Ignoring " + nums);
          }
        }
        System.out.println(sum);
        System.out.flush();
      }
    }
    catch (Exception ex)
    {
      throw new RuntimeException(ex);
    }
  }
}
