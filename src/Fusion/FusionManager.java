package Fusion;
import Base.Run;
import Base.RunSet;
import Fusion.Condorcet.CondorcetFusion;
import IO.IOManager;
import IO.Settings;

import java.util.ArrayList;
import java.util.List;

public class FusionManager {

    List<RunSet> _runSetList;
    IOManager ioManager = new IOManager();
    BaseFusion baseFusionMethod;
    CondorcetFusion condorcet;

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
                System.out.println("|--> Perform all fusion on "+runSet.Name());

                if(Settings.FuseMAX) {
                    baseFusionMethod = new CombMAXBaseFusion();
                    runList.add(baseFusionMethod.fuse(runSet));
                    out("|  |--> Fuse():: CombMAX performed ");
                }

                if(Settings.FuseMNZ) {
                    baseFusionMethod = new CombMNZBaseFusion();
                    runList.add(baseFusionMethod.fuse(runSet));
                    out("|  |--> Fuse():: CombMNZ performed ");
                }

                if(Settings.FuseSUM) {
                    baseFusionMethod = new CombSUMBaseFusion();
                    runList.add(baseFusionMethod.fuse(runSet));
                    out("|  |--> Fuse():: CombSUM performed ");
                }

                if(Settings.FuseMED) {
                    baseFusionMethod = new CombMEDBaseFusion();
                    runList.add(baseFusionMethod.fuse(runSet));
                    out("|  |--> Fuse():: CombMED performed ");
                }
                if(Settings.FuseMIN) {
                    baseFusionMethod = new CombMINBaseFusion();
                    runList.add(baseFusionMethod.fuse(runSet));
                    out("|  |--> Fuse():: CombMIN performed ");
                }
                if(Settings.FuseANZ) {
                    baseFusionMethod = new CombANZBaseFusion();
                    runList.add(baseFusionMethod.fuse(runSet));
                    out("|  |--> Fuse():: CombANZ performed ");
                }

               if(Settings.FuseCONDORCET){
                   condorcet = new CondorcetFusion();
                   runList.add(condorcet.fuse(runSet));
                   out("|  |--> Fuse():: CondorcetFusion performed ");
               }

                RunSet result = new RunSet(runList, runSet.Name());
                ioManager.serialize(result);
                out("|  |--> Fuse():: fusion result serialized ");
            }
    }


    private void out(String out) {
        if(Settings.VERBOSE)
            System.out.println(out);
    }

}
