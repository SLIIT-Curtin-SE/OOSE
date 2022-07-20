import java.util.*;
import java.io.*;

public class B{
    public static void main(String args[])
    {  
        File file = new File("name.txt");
        try (
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr)
            )
        {
            while (br.ready())
            {
                System.out.println(br.readLine());
            }
        }
        catch (IOException e)
        {
            System.out.println(file.getName());
        }
    }
}