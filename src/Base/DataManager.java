package Base;

import IO.IOManager;
import Fusion.*;
import java.util.ArrayList;
import java.util.Set;

public class DataManager {

    RunSet _runSet;
    int _setSize;
    IOManager ioManager = new IOManager();
    BaseFusionMethod baseFusionMethod;
    Condorcet condorcet;
    
    public DataManager(int setSize) { _setSize = setSize; }

    public void Import() {

        ArrayList<Run> listRun = new ArrayList<Run>();
        for(int i=0; i<_setSize; i++) {
            Run run = ioManager.ReadResFile();
            System.out.println("Reading File...");
            for(int j=0; j<run.size(); j++) {
                run.get(j).Print();
            }
            listRun.add(run);
        }
        _runSet = new RunSet(listRun);
    }

    public void NormalizeMinMax() {
        if (_runSet != null) {
            for(int i=0; i<_setSize; i++) {
                System.out.println("Normalizing Min/Max...");
                _runSet.NormalizeSetMinMax();
                Run run = _runSet.getRunList().get(i);
                /*for(int j=0; j<run.size(); j++) {
                    run.get(j).Print();
                }*/
                ioManager.WriteToResFile(run);
            }
        }
    }
    
    public void Fuse() {
        if (_runSet != null) {
            System.out.println("Fusion CombMAX...");
            baseFusionMethod = new CombMAXBaseFusionMethod();
            ioManager.WriteToResFile(baseFusionMethod.Fuse(_runSet));
            System.out.println("Fusion CombMNZ...");
            baseFusionMethod = new CombMNZBaseFusionMethod();
            ioManager.WriteToResFile(baseFusionMethod.Fuse(_runSet));
            System.out.println("Fusion CombSUM...");
            baseFusionMethod = new CombSUMBaseFusionMethod();
            ioManager.WriteToResFile(baseFusionMethod.Fuse(_runSet));
            condorcet = new Condorcet();
            System.out.println("Fusion CombSUM...");
            ioManager.WriteToResFile(condorcet.Fuse(_runSet));
        }
    }

}
