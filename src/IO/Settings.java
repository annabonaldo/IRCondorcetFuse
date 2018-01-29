package IO;

import Fusion.FusionManager;

import java.util.Arrays;
import java.util.stream.Stream;

public class Settings {
    //region DEBUG FEATURES
    public  static final Boolean VERBOSE = true;
    public  static final Boolean RUN_IN_INFO = false;
    /**  to activate/deactivate  path-printing on terminal for input files (for debug scope)*/
    public  static final Boolean PATH_IN = false;
    /** to activate/deactivate  path-printing on terminal for output files (for debug scope)*/
    public  static final Boolean PATH_OUT = false;
    /** to activate/deactivate  path-printing on terminal for normalized data
     * output files (for debug scope)
     */
    public  static final Boolean PATH_NORM_OUT  = false;

     /** to activate/deactivate error information printing on terminal (for debug scope)*/
    public  static final Boolean ERROR_VERBOSE = false;
    /** to activate/deactivate imported run info on terminal (for debug scope)*/
    public  static final Boolean RUN_FUSION_DETAIL =false;

    //endregion DEBUG FEATURES

    //region NORMALIZATION FEATURES
    /**  to set normalization as MinMax normalization.
     * Only one normalization method should be activated at each time */
    public static final Boolean MinMax = true;
    /**  to set normalization as StandardDeviation normalization.
     * Only one normalization method should be activated at each time */
    public static final Boolean StandardDeviation = false;

    public static final Boolean Outliers = false;
    //endregion NORMALIZATION FEATURES

    //region ACTIVATE/DEACTIVATE FUSION METHODS
    // ACTIVATE / DEACTIVATE FUSION METHOD EXECUTED ON INPUT RUNS
    /** to activate/deactivate CombMAX method on input RunSet
     * More than one method can be activated at each time
     * @see FusionManager#Fuse()
     * @see Fusion.BaseFusion
     * @see Fusion.CombMAXBaseFusion */
    public static final Boolean FuseMAX =true;
    /** to activate/deactivate CombFuse method on input RunSet
     * More than one method can be activated at each time
     * @see FusionManager#Fuse()
     * @see Fusion.BaseFusion
     * @see Fusion.CombMNZBaseFusion */
    public static final Boolean FuseMNZ =true;
    /** to activate/deactivate CombSUM fusion method on input RunSet
     * More than one method can be activated at each time
     * @see FusionManager#Fuse()
     * @see Fusion.BaseFusion
     * @see Fusion.CombSUMBaseFusion */
    public static final Boolean FuseSUM =true;
    /** to activate/deactivate CombMIN fusion method on input RunSet
     * More than one method can be activated at each time
     * @see FusionManager#Fuse()
     * @see Fusion.BaseFusion
     * @see Fusion.CombMINBaseFusion */
    public static final Boolean FuseMIN =true;
    /** to activate/deactivate CondANZ method on input RunSet
     * More than one method can be activated at each time
     * @see FusionManager#Fuse()
     * @see Fusion.BaseFusion
     * @see Fusion.CombANZBaseFusion */
    public static final Boolean FuseANZ =true;
    /** to activate/deactivate CombMED method on input RunSet
     * More than one method can be activated at each time
     * @see FusionManager#Fuse()
     * @see Fusion.BaseFusion
     * @see Fusion.CombMEDBaseFusion */
    public static final Boolean FuseMED =true;

    /** to activate/deactivate Condorcet fusion method on input RunSet
     * More than one method can be activated at each time
     * @see FusionManager#Fuse()
     * @see Fusion.Condorcet.CondorcetFusion */
    public static final Boolean FuseCONDORCET =true;
    //endregion ACTIVATE/DEACTIVATE FUSION METHODS

    //region INPUT DIRECTORIES SETTINGS
    /** Setting all directories in default path as fusion input
     * @see IOManager#DefaultInPath
     * */
    public static final Boolean ALLFusionInDir = false;

    /** RunBasic group directories names */
    public static final String[] SUBSET_runBasic = {"runBasic_2/",
                                                    "runBasic_4/",
                                                    "runBasic_6/",
                                                    "runBasic_8/",
                                                    "runBasic_10/"};


    /** Without StopList  group directories names */
    public static final String[] SUBSET_runNoSW = {     "run_noSW_2/",
                                                        "run_noSW_4/",
                                                        "run_noSW_6/",
                                                        "run_noSW_8/",
                                                        "run_noSW_10/"};

    /** Without Stemmer group directories names */
    public static final String[] SUBSET_runNoSM = {     "run_noSM_2/",
                                                        "run_noSM_4/",
                                                        "run_noSM_6/",
                                                        "run_noSM_8/",
                                                        "run_noSM_10/" };

    /** Without StopList and without stemmer group directories names */
    public static final String[] SUBSET_runNoSM_NoSW = {"run_noSM_noSW_2/",
                                                        "run_noSM_noSW_4/",
                                                        "run_noSM_noSW_6/",
                                                        "run_noSM_noSW_8/",
                                                        "run_noSM_noSW_10/"};

    /** Directory in default path take as input for <code>Settings.ALLFusionInDir = false;</code>
     * @see Settings#ALLFusionInDir */
    public static final String[] SUBSET_FusionInDir = join(SUBSET_runNoSW, SUBSET_runNoSM);

    /**
     * Utility method to execute fusion only on a subset of input directories.
     * @param a Directory set 1
     * @param b Directory set 2
     * @return a anb concatenated
     */
    static  String[] join(String[] a, String[] b){
        Stream<String> streamA = Arrays.stream(a);
        Stream<String> streamB = Arrays.stream(b);
        return Stream.concat(streamA, streamB).toArray(String[]::new);
    }

    //endregion INPUT DIRECTORIES SETTINGS
}
