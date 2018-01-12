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
    
    public FusionManager() {


    }

    public void Import() {
        _runSetList =  ioManager.readRunSetList();

        for(RunSet set:_runSetList)
        {
            set.printInfo();
        }
    }

    public void Fuse() {

           System.out.println("fuse()");
            for(RunSet runSet : _runSetList) {
                List<Run> runList = new ArrayList<>();
                baseFusionMethod = new CombMAXBaseFusionMethod();
                runList.add(baseFusionMethod.fuse(runSet));
                baseFusionMethod = new CombMNZBaseFusionMethod();
                runList.add(baseFusionMethod.fuse(runSet));

                baseFusionMethod = new CombSUMBaseFusionMethod();
                runList.add(baseFusionMethod.fuse(runSet));
                condorcet = new Condorcet();
                runList.add(condorcet.fuse(runSet));

                RunSet result = new RunSet(runList, runSet.Name());
                ioManager.writeRunSet(result);
            }
        System.out.println("End fuse()");
    }

}
