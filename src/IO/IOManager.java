package IO;

import Base.ResultItem;
import Base.ResultSet;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static IO.IOSettings.DefaultOutPath;

public class IOManager {

    Scanner scanner = new Scanner(System.in);

    public ResultSet ReadResFile()
    {
        ResultSet items = new ResultSet();

        BufferedReader in = null;
        try {
            System.out.println("Enter the resource file to read (in terrier-core-4.2/var/results):");
            String ResFile = scanner.next();
            in = new BufferedReader(new FileReader(IOSettings.DefaultInPath + ResFile));
            String str=null;
            while((str = in.readLine()) != null){
                ResultItem resItem = new ResultItem(str);
                items.add(resItem);
            }
        }
        catch(IOException e2)
        {
            e2.printStackTrace();
        }
        return  items;
    }

    public void WriteToResFile(ResultSet set)
    {
        PrintWriter writer = null;
        try {
            System.out.println("Enter name file to save output (in main dir):");
            String OutFile = scanner.next();
            writer = new PrintWriter(IOSettings.DefaultOutPath + OutFile, "UTF-8");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        for(int i=0; i<set.size(); i++)
        {
            writer.println(set.get(i).getLine());
        }
    }
}

