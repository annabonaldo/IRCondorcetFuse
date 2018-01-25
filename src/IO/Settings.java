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
    public static final Boolean FuseMED =true;
    public static final Boolean FuseCONDORCET =false;


    public static final Boolean ALLFusionInDir = false;
    public static final String[] SUBSET_FusionInDir = {"runBasic_2/"};


}
