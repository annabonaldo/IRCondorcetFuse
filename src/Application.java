import Fusion.FusionManager;
import IO.IOManager;

public class Application {
    public static void main(String[] args) {
        System.out.println("Application started...");

        FusionManager fusionManager = new FusionManager();
        fusionManager.Import();
        fusionManager.Fuse();
    }
}