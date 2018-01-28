package IO;

import java.util.Arrays;
import java.util.stream.Stream;

public class Settings {
    public  static final Boolean VERBOSE = true;
    public  static final Boolean RUN_IN_INFO = false;

    //  to activate/deactivate  path-printing on terminal (for debug scope)
    public  static final Boolean PATH_IN = false;
    public  static final Boolean PATH_OUT = false;
    public  static final Boolean PATH_NORM_OUT  = false;

    public  static final Boolean ERROR_VERBOSE = false;
    public  static final Boolean RUN_FUSION_DETAIL =false;

    //  to activate/deactivate each NORMALIZATION Method performed during EXECUTION

    public static final Boolean MinMax = true;
    public static final Boolean StandardDeviation = false;
    public static final Boolean Outliers = false;

    //  to activate/deactivate each FUSION Method performed during EXECUTION

    public static final Boolean FuseMAX =true;
    public static final Boolean FuseMNZ =true;
    public static final Boolean FuseSUM =true;
    public static final Boolean FuseMIN =true;
    public static final Boolean FuseANZ =true;
    public static final Boolean FuseMED =true;
    public static final Boolean FuseCONDORCET =true;


    public static final Boolean ALLFusionInDir = false;

    public static final String[] SUBSET_runBasic = {"runBasic_2/",
                                                    "runBasic_4/",
                                                    "runBasic_6/",
                                                    "runBasic_8/",
                                                    "runBasic_10/"};
    public static final String[] SUBSET_runBasicK100 = {"runBasic_k100_2/",
                                                    "runBasic_k100_4/",
                                                    "runBasic_k100_6/",
                                                    "runBasic_k100_8/",
                                                    "runBasic_k100_10/"};


    public static final String[] SUBSET_runNoSW = {     "run_noSW_2/",
                                                        "run_noSW_4/",
                                                        "run_noSW_6/",
                                                        "run_noSW_8/",
                                                        "run_noSW_10/"};

    public static final String[] SUBSET_runNoSM = {     "run_noSM_2/",
                                                        "run_noSM_4/",
                                                        "run_noSM_6/",
                                                        "run_noSM_8/",
                                                        "run_noSM_10/" };

    public static final String[] SUBSET_runNoSM_NoSW = {"run_noSM_noSW_2/",
                                                        "run_noSM_noSW_4/",
                                                        "run_noSM_noSW_6/",
                                                        "run_noSM_noSW_8/",
                                                        "run_noSM_noSW_10/"};


    public static final String[] SUBSET_FusionInDir = SUBSET_runBasicK100;
  //  public static final String[] SUBSET_FusionInDir = join(SUBSET_runNoSW, SUBSET_runNoSM);

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

}
