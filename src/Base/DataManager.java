package Base;

import IO.IOManager;
import java.util.ArrayList;
import java.util.Set;

public class DataManager {

    RunSet _runSet;
    int _setSize;
    IOManager ioManager = new IOManager();
    
    public DataManager(int setSize) { _setSize = setSize; }

    public void Import() {

        ArrayList<Run> listRun = new ArrayList<Run>();
        for(int i=0; i<_setSize; i++) {
            Run run = ioManager.ReadResFile();
            for(int j=0; j<run.size(); j++) {
                run.get(j).Print();
            }
            listRun.add(run);
            ioManager.WriteToResFile(run);
        }
        _runSet = new RunSet(listRun);
    }

    public void normalizeMinMax() {
        if (_runSet != null) {
            for(int i=0; i<_setSize; i++) {
                _runSet.NormalizeSetMinMax();
            }   
        }
    }

}
