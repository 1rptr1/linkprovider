package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class FileToHashmap {
    public static Map<String, String> getData(String filePath)
    {

        Map<String, String> map
                = new HashMap<>();
        BufferedReader bufferedReader = null;

        try {
            File file = new File(filePath);
            bufferedReader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("~");
                String name = parts[0].trim();
                String number = parts[1].trim();

                if (!name.equals("") && !number.equals(""))
                    map.put(name, number);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {

            // Always close the BufferedReader
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (Exception e) {
                e.printStackTrace();
                }
            }
        }

        return map;
    }
}

