package utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;

public class WriteToText {
    public static void write(String filename, String data)
    {
        try (FileWriter fileWriter = new FileWriter(filename, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter))
        {
                printWriter.println(data);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void mapData(String filename, Map<String, String> data)
    {
        try (FileWriter fileWriter = new FileWriter(filename, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter))
        {
            for (Map.Entry<String, String> entry : data.entrySet())
            {
                printWriter.println(entry.getKey()+"|"+entry.getValue());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
