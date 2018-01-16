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
    static final String outFileExtension = ".txt";

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
            if(Settings.RUN_IN_INFO) System.out.println(run.Name()+" Size : "+ run.size());
            runs.add(run);
        }

        return new RunSet(runs, _testName);

    }

    public static void serialize(RunSet runSet) throws FileNotFoundException, UnsupportedEncodingException {

        File directory = new File(getOutDir(runSet.Name()));
        if(!directory.exists())
            directory.mkdir();

        for(Run run :runSet.RunList()) {

            String fileName = setExtension(run.Name());
           PrintWriter writer = new PrintWriter( (getOutDir(runSet.Name())+fileName), "UTF-8");

            String str = null;
            for(int i=0; i<run.size(); i++)
            {
                writer.println(run.get(i).getRunAsLine());
            }
            writer.close();

        }
    }


    public static void serializeAsNormlized(RunSet runSet) throws FileNotFoundException, UnsupportedEncodingException {

        String normDirRelPath = runSet.Name()+"/"+ normPrefix+runSet.Name();
        File parentDir = new File(IORunSet.getOutDir(runSet.Name()));
        if(!parentDir.exists()) parentDir.mkdir();

        File normDir = new File(IORunSet.getOutDir(normDirRelPath));
        if(Settings.PATH_NORM_OUT) System.out.println("normDir "+normDir);
        if(!normDir.exists()) normDir.mkdir();

        for(Run run :runSet.RunList()) {
            String fileName = setExtension(run.Name());
            PrintWriter writer = new PrintWriter((IORunSet.getOutDir(normDirRelPath)+fileName), "UTF-8");

            String str = null;
            for(int i=0; i<run.size(); i++)
            {
                writer.println(run.get(i).getRunAsLine());
            }
            writer.close();

        }

    }

    private static String   setExtension(String fileName)
    {
        String file = fileName;
        if(file.endsWith(".res"))
           file = file.replace(".res", outFileExtension);
        else
            file= file+outFileExtension;

        return file;
    }
}
