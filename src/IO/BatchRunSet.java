package IO;

import Base.Run;
import Base.RunLine;
import Base.RunSet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class BatchRunSet {
    String[] _files;
    String _testName;


    public BatchRunSet(String[] files, String dirName) {
        _files = files;
        _testName = dirName;
    }

    String getInDir(){ return IOManager.DefaultInPath + _testName + "/";}


    String[] getFileNames(){ return _files;}

    public RunSet read() throws IOException {
        List<Run> runs = new ArrayList<Run>();
        for(String file :_files) {
            Run run = new Run(_testName);
            BufferedReader in = new BufferedReader(new FileReader(getInDir() + file));
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

        String outDir = IOManager.DefaultOutPath + runSet.getName() + "/";
        File directory = new File(String.valueOf(outDir));
        if(!directory.exists())
            directory.mkdir();

        for(Run run :runSet.getRunList()) {
           PrintWriter writer = new PrintWriter( outDir+run.getName(), "UTF-8");

            String str = null;
            for(int i=0; i<run.size(); i++)
            {
                writer.println(run.get(i).getLine());
            }

        }
    }
}
