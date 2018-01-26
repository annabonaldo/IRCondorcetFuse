package IO;

public class Settings {
    public  static final Boolean VERBOSE = true;
    public  static final Boolean RUN_IN_INFO = false;

    //  to activate/deactivate  path-printing on terminal (for debug scope)
    public  static final Boolean PATH_IN = false;
    public  static final Boolean PATH_OUT = false;
    public  static final Boolean PATH_NORM_OUT  = false;

    public  static final Boolean ERROR_VERBOSE = false;
    public  static final Boolean RUN_FUSION_DETAIL =false;

    //  to activate/deactivate  each FUSION Method performed during EXECUTION

    public static final Boolean FuseMAX =true;
    public static final Boolean FuseMNZ =true;
    public static final Boolean FuseSUM =true;
    public static final Boolean FuseMIN =true;
    public static final Boolean FuseANZ =true;
    public static final Boolean FuseANZ =true;
    public static final Boolean FuseMED =true;
    public static final Boolean FuseCONDORCET =false;


    public static final Boolean ALLFusionInDir = false;
    public static final String[] SUBSET_runBasic_K100 =
            {"runBasic_k100_2/", "runBasic_k100_4/","runBasic_k100_6/", "runBasic_k100_8/", "runBasic_k100_10/"};

    public static final String[] SUBSET_runBasic =
            {"runBasic_2/", "runBasic_4/","runBasic_6/", "runBasic_8/", "runBasic_10/"};

    public static final String[] SUBSET_runQE =
            {"runQE_2/", "runQE_4/","runQE_6/", "runQE_8/", "runQE_10/"};

    public static final String[] SUBSET_FusionInDir = SUBSET_runQE; //{"runBasic_10/"};


}
