package IO;
import Base.RunSet;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class IOManager {
    static final String DefaultInPath = "/results/FusionIn/";
    static final String DefaultOutPath = "/results/FusionOut/";

    IORunSet[] __ioRunSet;


    public IOManager()
    {
        __ioRunSet = getSets();
    }

    public List<RunSet> deserializeAll() {
        List<RunSet> runSets = new ArrayList<>();

        for(IORunSet inputSet : __ioRunSet)
        {
            try { runSets.add(inputSet.deserialize()); }

            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  runSets;

    }

    public void serialize(RunSet runSet)
    {
        try {
            IORunSet.serialize(runSet);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void serializeAsNormalized(List<RunSet> runSetList)
    {
        for(RunSet runSet:runSetList)
            try {
               IORunSet.serializeAsNormlized(runSet);
            }

            catch (FileNotFoundException e) {
                if(Settings.ERROR_VERBOSE) System.out.println("Normalization Error");
                e.printStackTrace();
            }
            catch (UnsupportedEncodingException e) {

            if(Settings.ERROR_VERBOSE) System.out.println("Normalization Error");
            e.printStackTrace();
            }
    }

    private  IORunSet[] getSets(){

        String path = Paths.get("").toAbsolutePath().toString();

        File[] directories = new File(path+DefaultInPath).listFiles(File::isDirectory);

        if(directories != null && directories.length >0 ) {

            __ioRunSet = new IORunSet[directories.length];
            int i = 0;
            for (File dir : directories) {
                File[] runFiles = new File(dir.getAbsolutePath()).listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return  name.toLowerCase().endsWith(".res");
                    }});

                __ioRunSet[i] = new IORunSet(runFiles, dir.getName());
                i++;
            }
        }
        return __ioRunSet;

    }


}

