package Base;

import IO.IOManager;

public class DataManager {

    ResultSet data;

    public void Import(){
        IOManager ioManager = new IOManager();
        data = ioManager.ReadResFile();
        for(int i=0; i<10; i++)
            data.get(i).Print();

        ioManager.WriteToResFile(data);
    }
}
