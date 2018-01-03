import Base.DataManager;

public class Application {
    public static void main(String[] args) {
        System.out.println("Application started...");
        DataManager dataManager = new DataManager();
        dataManager.Import();
        dataManager.normalizeMinMax();
    }
}