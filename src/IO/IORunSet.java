package IO;

import Base.Run;
import Base.RunLine;
import Base.RunSet;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IORunSet {
    File[] _files;
    String _testName;
    static final String normPrefix = "NORM_";

    public IORunSet(File[] files, String dirName) {
       if(Settings.PATH_IN) {
           System.out.println("Number of batch files = " + files.length);
           for (File s : files) { System.out.println("*** File "+ s.getAbsolutePath()); }
       }

        _files = files;
        _testName = dirName;

    }

    String getInDir(){
        String path = Paths.get("").toAbsolutePath().toString();
        return path+ IOManager.DefaultInPath + _testName ;
    }

    public static String getOutDir(String dirName)
    {
        String path = Paths.get("").toAbsolutePath().toString();
        if(Settings.PATH_OUT)
            System.out.println("OUT PATH = "+ path+ IOManager.DefaultOutPath + dirName );

        return path+ IOManager.DefaultOutPath + dirName+"/";

    }

    File[] getFileNames(){ return _files; }

    public RunSet deserialize() throws IOException {
        List<Run> runs = new ArrayList<Run>();

        for(File file :_files) {

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

    public static void serialize(RunSet runSet) throws FileNotFoundException, UnsupportedEncodingException {

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

    public static void serializeAsNormlized(RunSet runSet) throws FileNotFoundException, UnsupportedEncodingException {

        String normDirRelPath = runSet.Name()+"/"+ normPrefix+runSet.Name();File parentDir = new File(IORunSet.getOutDir(runSet.Name()));
        if(!parentDir.exists()) parentDir.mkdir();

        File normDir = new File(IORunSet.getOutDir(normDirRelPath));
        if(!normDir.exists()) normDir.mkdir();

        for(Run run :runSet.RunList()) {
            PrintWriter writer = new PrintWriter((IORunSet.getOutDir(normDirRelPath)+run.Name()), "UTF-8");

            String str = null;
            for(int i=0; i<run.size(); i++)
            {
                writer.println(run.get(i).getRunAsLine());
            }

        }

    }

}
