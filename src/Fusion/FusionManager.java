package Fusion;

import Base.Run;
import Base.RunSet;
import IO.IOManager;
import java.util.ArrayList;
import java.util.List;

public class FusionManager {

    List<RunSet> _runSetList;
    IOManager ioManager = new IOManager();
    BaseFusionMethod baseFusionMethod;
    Condorcet condorcet;
    
    public FusionManager() {}

    public void Import() {

        _runSetList =  ioManager.readRunSetList();
    }

    public void NormalizeMinMax() {
        if (_runSetList != null) {
                System.out.println("Normalizing Min/Max...");
                for(RunSet set : _runSetList)
                    set.NormalizeSetMinMax();

            }
        }

    
    public void Fuse() {

            for(RunSet runSet : _runSetList) {
                List<Run> runList = new ArrayList<>();
                baseFusionMethod = new CombMAXBaseFusionMethod();
                runList.add(baseFusionMethod.Fuse(runSet));

                baseFusionMethod = new CombMNZBaseFusionMethod();
                runList.add(baseFusionMethod.Fuse(runSet));

                baseFusionMethod = new CombSUMBaseFusionMethod();
                runList.add(baseFusionMethod.Fuse(runSet));
                condorcet = new Condorcet();
                runList.add(condorcet.Fuse(runSet));

                RunSet result = new RunSet(runList, runSet.getName());
                ioManager.writeRunSet(result);
            }
    }

}
