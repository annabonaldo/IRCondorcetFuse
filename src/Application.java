import Fusion.FusionManager;
import IO.Settings;

public class Application {

    public static void main(String[] args) {

        FusionManager fusionManager = new FusionManager();
        fusionManager.Import();
        fusionManager.Fuse();
    }
}