package Fusion;
import Base.Run;
import Base.RunSet;
import IO.IOManager;
import IO.Settings;

import java.util.ArrayList;
import java.util.List;

public class FusionManager {

    List<RunSet> _runSetList;
    IOManager ioManager = new IOManager();
    BaseFusionMethod baseFusionMethod;
    Condorcet condorcet;

    public FusionManager() { }

    public void Import() {
        out("Import():: Importing data...");
        _runSetList =  ioManager.deserializeAll();

        out("Import() :: Serializing normalized data...");
        ioManager.serializeAsNormalized(_runSetList);

        if(Settings.RUN_IN_INFO)
            for(RunSet set:_runSetList) { set.printInfo(); }
        out("Import() :: Data imported correctly");
    }

    public void Fuse() {

           out("Fuse():: fusion started");
            for(RunSet runSet : _runSetList) {
                List<Run> runList = new ArrayList<>();

                if(Settings.FuseMAX) {
                    baseFusionMethod = new CombMAXBaseFusionMethod();
                    runList.add(baseFusionMethod.fuse(runSet));
                    out("Fuse():: CombMAX performed ");
                }

                if(Settings.FuseMNZ) {
                    baseFusionMethod = new CombMNZBaseFusionMethod();
                    runList.add(baseFusionMethod.fuse(runSet));
                    out("Fuse():: CombMNZ performed ");
                }

                if(Settings.FuseSUM) {
                    baseFusionMethod = new CombSUMBaseFusionMethod();
                    runList.add(baseFusionMethod.fuse(runSet));
                    out("Fuse():: CombSUM performed ");
                }

               if(Settings.FuseCONDORCET){
                   condorcet = new Condorcet();
                   runList.add(condorcet.fuse(runSet));
                   out("Fuse():: Condorcet performed ");
               }

                RunSet result = new RunSet(runList, runSet.Name());
                ioManager.serialize(result);
                out("Fuse():: fusion result serialized ");
            }
    }


    private void out(String out) {
        if(Settings.VERBOSE)
            System.out.println(out);
    }

}
