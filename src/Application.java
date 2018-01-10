import Base.DataManager;
import IO.IOManager;

public class Application {
    public static void main(String[] args) {
        System.out.println("Application started...");
        IOManager ioManager = new IOManager();
        int setSize = ioManager.ReadSetSize();
        DataManager dataManager = new DataManager(setSize);
        dataManager.Import();
        dataManager.NormalizeMinMax();
    }
}