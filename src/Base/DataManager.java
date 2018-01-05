package Base;

import IO.IOManager;

public class DataManager {

    ResultSet data;
    IOManager ioManager = new IOManager();

    public void Import(){

        data = ioManager.ReadResFile();
        for(int i=0; i<data.size(); i++)
            data.get(i).Print();
        ioManager.WriteToResFile(data);
    }

    public void normalizeMinMax(){

        data.normalizeSetMinMax();
        for(int i=0; i<data.size(); i++)
            data.get(i).Print();
		ioManager.WriteToResFile(data);

    }
}
