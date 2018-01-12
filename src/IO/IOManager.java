package IO;

import Base.Run;
import Base.RunLine;
import Base.RunSet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class IOManager {
    static final String DefaultInPath = "../../results/FusionIn/";
    static final String DefaultOutPath = "../../results/FusionOut/";

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

    private  BatchRunSet[] getBatchSets()
    {
        File mainFolder = new File(DefaultInPath);
        String[] TestDirNames =  mainFolder.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        _batch = new BatchRunSet[TestDirNames.length];

        int i=0;
        for(String dirName: TestDirNames)
        {
            File dir = new File(dirName);
            String[] runFiles = dir.list(new FilenameFilter() {
                @Override
                public boolean accept(File file, String name) {
                    return ((!file.isDirectory()) && (name.contains(".res")));
                }
            });

            _batch[i]= new BatchRunSet(runFiles, dirName);
            i++;
        }
        return _batch;

    }


}

