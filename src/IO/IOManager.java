package IO;
import Base.RunSet;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class IOManager {
    /**
     * Default path for input RunSet folders.
     * Input directories containing runset files in TERRIER format.
     */
    static final String DefaultInPath = "/results/FusionIn/";
    /**
     * Default path for output  RunSet  folders. */
    static final String DefaultOutPath = "/results/FusionOut/";

    /**
     * RunSet to import as fusion input.
     */
    IORunSet[] __ioRunSet;


    /**
     * Constructor of input-output manager.
     * Object construction call inner <code>IORunSet[]</code> object configuration.
     * @see IOManager#getSets()
     * @see IOManager#__ioRunSet
     * */
    public IOManager()
    {
        __ioRunSet = getSets();
    }

    /**
     * Deserialize all
     * @see IORunSet in this.
     * @return  the list of dematerialized IORunSet as
     * @see RunSet s.
     */
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

    /**
     * Serialize all
     * @param  runSet is the object we want to serialize.
     * @see IORunSet in this.
     */
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

    /**
     * Nomrmalized RunSet object serialization. This method does not overwrite the original
     * <code>RunSet</code> data, but it will create a new folder for normalized objects
     * in the default output path position.
     * @see IOManager#DefaultOutPath
     * @param runSetList is the input RunSet (previously normalized) we want to write on
     *                   files.
     */

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

    /**
     * Call IORunSets object configuration.
     * The IORunSet configuration checks input directories to search for RunSets directories names.
     * RunSet directories names (and paths) will be useful later in deserialization an RunSet creation.
     * @return the configured array of IORunSet[]
     * @see IORunSet[]
     */
    private  IORunSet[] getSets(){

        String path = Paths.get("").toAbsolutePath().toString();
        File[] directories;
        if(Settings.ALLFusionInDir)
             directories = new File(path+DefaultInPath).listFiles(File::isDirectory);
        else
            directories  = getDirectorieSubSet();

        if(directories != null && directories.length >0 ) {

            __ioRunSet = new IORunSet[directories.length];
            int i = 0;
            for (File dir : directories) {
                File[] runFiles = new File(dir.getAbsolutePath()).listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return  name.endsWith(".res");
                    }});

                __ioRunSet[i] = new IORunSet(runFiles, dir.getName());
                i++;
            }
        }
        return __ioRunSet;

    }


    /**
     * Utility method for <code>RunSet</code> subdirectories path building in
     * default path position.
     * @see IOManager#DefaultInPath
     * @return the <code>File[] </code> array containing all subdirectories file paths information.
     */
    public File[] getDirectorieSubSet() {
        String ABSpath = Paths.get("").toAbsolutePath().toString();
        File[] dir = new File[Settings.SUBSET_FusionInDir.length];
        int i =0;
       for(String path : Settings.SUBSET_FusionInDir) {
           dir[i] = new File(ABSpath + DefaultInPath + path);
           i++;
       }

        return dir;
    }
}

