package IO;

import Base.Run;
import Base.RunLine;
import Base.RunSet;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class manages RunSet input/output operation and RunSet creation from all .res files contained into
 * a single Directory placed at path in IOManager.DefaultInPath.
 */
public class IORunSet {
    /**
     * This field contains all files of the current Runset directory.
     * Each file is a single Run of current Runset.
     */
    File[] _files;
    /**
     *  is the RunSet name that corresponds to input directory name.
     *  The input directory is the directory that contains all runs of current RunSet.
     */
    String _testName;
    /**
     * Prefix for output folder that contain normalized input runs.
     */
    static final String normPrefix = "NORM_";
    /** Default extention for output Run files
     **/
    static final String outFileExtension = ".txt";

    public IORunSet(File[] files, String dirName) {
       if(Settings.PATH_IN) {
           System.out.println("Number of batch files = " + files.length);
           for (File s : files) { System.out.println("*** File "+ s.getAbsolutePath()); }
       }

        _files = files;
        _testName = dirName;

    }

    /**
     * @return the input directory path string.
     */
    String getInDir(){
        String path = Paths.get("").toAbsolutePath().toString();
        return path+ IOManager.DefaultInPath + _testName ;
    }

    /**
     * @param dirName is the RunSet's name.
     *                RunSet fusion output files produced from this RunSet are saved into a folder in
     *                IOManager.DefaultOutPath that have the same name of the Runset.
     * @return The path string to the output directory for fusion results.
     */
    public static String getOutDir(String dirName)
    {
        String path = Paths.get("").toAbsolutePath().toString();
        if(Settings.PATH_OUT)
            System.out.println("OUT PATH = "+ path+ IOManager.DefaultOutPath + dirName );

        return path+ IOManager.DefaultOutPath + dirName+"/";

    }

    File[] getFileNames(){ return _files; }

    /** Perform RunSet deserialization using _testName
     * to identify the correct directory to write output in IOManager.DefaultOutPath.
     * @return The deserialized RunSet
     * @throws IOException In case of error in file reading operation.
     */

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

    /**
     * Perform RunSet serialization. A RunSet serialization create a folder (if missing) in IOManager.DefaultOutPath.
     * @param runSet <code>RunSet</code> we want to serialize.
     * @throws FileNotFoundException for error in file writing process
     * @throws UnsupportedEncodingException File writing error
     */
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

    /**
     * Write to file <code> RunSet</code> once normalization has been performed.
     * Normalized <code>RunSet</code> serialization does not overwrite input <code>RunSet</code>
     * files.
     * @param runSet The <em>normalized</em> <code>RunSet</code> we want to serialized.
     * @throws FileNotFoundException Output file/directory not found
     * @throws UnsupportedEncodingException File writing error
     */
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

    /**
     * Setting file output extension. Output file extension is
     * decided in parameter
     * @see IORunSet#outFileExtension
     * @param fileName the name of file we want to add extension string
     * @return the filename extension with default extension added.
     */
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
