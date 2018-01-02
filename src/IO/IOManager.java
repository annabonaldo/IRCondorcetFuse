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
            System.out.println("Enter the resource file to read (in terrier-core-4.2/var/res):");
            String ResFile = scanner.next();
            in = new BufferedReader(new FileReader(IOSettings.DefaultInPath + ResFile));
            String str=null;
            int i=0;
            while((str = in.readLine()) != null){
                items.add(new ResultItem(str));
                //if (i<10)
                   // System.out.println(str);
                //i++;

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

