package IO;

import Base.Run;
import Base.RunLine;
import Base.RunSet;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class BatchRunSet {
    File[] _files;
    String _testName;


    public BatchRunSet(File[] files, String dirName) {
        System.out.println("FILES in dir: "+dirName);
        System.out.println("file numb "+files.length);
        for(File s:files) {
            System.out.println(s.getAbsolutePath());
        }

        _files = files;
        _testName = dirName;

    }

    String getInDir(){
        String path = Paths.get("").toAbsolutePath().toString();
        return path+ IOManager.DefaultInPath + _testName ;
    }

    static String getOutDir(String dirName)
    {
        String path = Paths.get("").toAbsolutePath().toString();
        System.out.println("OUT PATH = "+ path+ IOManager.DefaultOutPath + dirName );
        return path+ IOManager.DefaultOutPath + dirName+"/";

    }

    File[] getFileNames(){ return _files; }

    public RunSet read() throws IOException {
        List<Run> runs = new ArrayList<Run>();

        for(File file :_files) {
            System.out.println("reading file "+ getInDir());
            Run run = new Run(file.getName());

            BufferedReader in = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String str = null;
            while ((str = in.readLine()) != null) {
                RunLine resItem = new RunLine(str);
                run.add(resItem);
            }
            runs.add(run);
        }

        return new RunSet(runs, _testName);

    }

    public static void write(RunSet runSet) throws FileNotFoundException, UnsupportedEncodingException {


        File directory = new File(getOutDir(runSet.Name()));
        if(!directory.exists())
            directory.mkdir();

        for(Run run :runSet.RunList()) {
           PrintWriter writer = new PrintWriter( (getOutDir(runSet.Name())+run.Name()), "UTF-8");

            String str = null;
            for(int i=0; i<run.size(); i++)
            {
                writer.println(run.get(i).getRunAsLine());
            }

        }
    }
}
