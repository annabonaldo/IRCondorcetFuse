package IO;

import Base.Run;
import Base.RunLine;

import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class IOManager {

    Scanner scanner = new Scanner(System.in);
    
    public int ReadSetSize() {
        int setSize;
        do {
            System.out.println("Enter a positive number of runs to read:");
            while(!scanner.hasNextInt()) {
                System.out.println("You didn't type a number. Please type again.");
                scanner.next();
            }
            setSize = scanner.nextInt();
        } while (setSize <= 0);
        return setSize;
    }

    public Run ReadResFile()
    {
        Run items = new Run();

        BufferedReader in = null;
        try {
            System.out.println("Enter the resource file to read (in terrier-core-4.2/var/results):");
            String ResFile = scanner.next();
            in = new BufferedReader(new FileReader(IOSettings.DefaultInPath + ResFile));
            String str=null;
            while((str = in.readLine()) != null){
                System.out.println(str);
                RunLine resItem = new RunLine(str);
                items.add(resItem);
            }
        }
        catch(IOException e2)
        {
            e2.printStackTrace();
        }
        return  items;
    }

    public void WriteToResFile(Run set)
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

