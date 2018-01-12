package IO;
import Base.RunSet;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class IOManager {
    static final String DefaultInPath = "/results/FusionIn/";
    static final String DefaultOutPath = "/results/FusionOut/";

    BatchRunSet[] _batch;


    public IOManager()
    {
        _batch = getBatchSets();
    }

    public List<RunSet> readRunSetList() {
        List<RunSet> runSets = new ArrayList<>();

        for(BatchRunSet batchSet : _batch)
        {
            try {
                runSets.add(batchSet.read());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  runSets;

    }

    public void writeRunSet(RunSet runSet)
    {
        try {
            BatchRunSet.write(runSet);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void writeRunSetList(List<RunSet> runSetList)
    {
       for(RunSet runSet:runSetList)
           writeRunSet(runSet);
    }

    private  BatchRunSet[] getBatchSets(){
        String path = Paths.get("").toAbsolutePath().toString();
        File[] directories = new File(path+DefaultInPath).listFiles(File::isDirectory);

        if(directories != null && directories.length >0 ) {
            _batch = new BatchRunSet[directories.length];
            int i = 0;
            for (File dir : directories) {

                File[] runFiles = new File(dir.getAbsolutePath()).listFiles(File::isFile);
                _batch[i] = new BatchRunSet(runFiles, dir.getName());
                i++;
            }
        }
        return _batch;

    }


}

