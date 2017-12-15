package IO;

import Base.ResultItem;
import Base.ResultSet;

import java.io.*;
import java.util.ArrayList;

import static IO.IOSettings.DefaultOutPath;

public class IOResult {

    public void  readResFile(String filename)
    {
        ResultSet items = new ResultSet();

        BufferedReader in = null;
        try {

            in = new BufferedReader(new FileReader(filename));
            String str=null;
            while((str = in.readLine()) != null){
                items.add(new ResultItem(str));
            }
        }
        catch(IOException e2)
        {
            e2.printStackTrace();
        }
    }


    public void  WriteToFile(ResultSet set, String filename)
    {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filename, "UTF-8");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        for(int i=0; i<set.size(); i++)
        {
            writer.println(set.get(i));
        }


    }
}

