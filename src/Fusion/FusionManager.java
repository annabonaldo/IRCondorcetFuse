package Fusion;
import Base.Run;
import Base.RunSet;
import Fusion.Condorcet.CondorcetFusion;
import IO.IOManager;
import IO.Settings;

import java.util.ArrayList;
import java.util.List;

public class FusionManager {

    /** List of <code> RunSet </code> the fusion works on.
     */
    List<RunSet> _runSetList;

    /** Input-Output manager to get <code>RunSet</code> data from files (deserialization)
     * and that serialize results once fusion has been performed.
     */
    IOManager _ioManager = new IOManager();

    /** Class constructor; it calls default constructor for all its fields.
     * Importing operation are delegated to <code>IOManager </code> object.
     * @see FusionManager#_ioManager
     * @see IOManager#deserializeAll()
     */
    public FusionManager() { }

    /** Start importing operations to create <code>RunSet</code>s from files data.
     */
    public void Import() {
        out("Import():: Importing data...");
        _runSetList =  _ioManager.deserializeAll();

        out("Import() :: Serializing normalized data...");
        _ioManager.serializeAsNormalized(_runSetList);

        if(Settings.RUN_IN_INFO)
            for(RunSet set:_runSetList) { set.printInfo(); }
        out("Import() :: Data imported correctly");
    }

    /**
     * Method to perform fusion on active methods.
     * @see Settings
     * @see Settings#FuseMAX
     * @see Settings#FuseMIN
     * @see Settings#FuseMED
     * @see Settings#FuseANZ
     * @see Settings#FuseMNZ
     * @see Settings#FuseSUM
     * @see Settings#FuseCONDORCET
     * Input file are
     *
     */
    public void Fuse() {

           out("Fuse():: fusion started");
            for(RunSet runSet : _runSetList) {

                List<Run> runList = new ArrayList<>();
                System.out.println("|--> Perform all fusion on "+runSet.Name());

                if(Settings.FuseMAX) {
                    BaseFusion baseFusionMethod = new CombMAXBaseFusion();
                    runList.add(baseFusionMethod.fuse(runSet));
                    out("|  |--> Fuse():: CombMAX performed ");
                }

                if(Settings.FuseMNZ) {
                    BaseFusion baseFusionMethod = new CombMNZBaseFusion();
                    runList.add(baseFusionMethod.fuse(runSet));
                    out("|  |--> Fuse():: CombMNZ performed ");
                }

                if(Settings.FuseSUM) {
                    BaseFusion baseFusionMethod = new CombSUMBaseFusion();
                    runList.add(baseFusionMethod.fuse(runSet));
                    out("|  |--> Fuse():: CombSUM performed ");
                }

                if(Settings.FuseMED) {
                    BaseFusion baseFusionMethod = new CombMEDBaseFusion();
                    runList.add(baseFusionMethod.fuse(runSet));
                    out("|  |--> Fuse():: CombMED performed ");
                }
                if(Settings.FuseMIN) {
                    BaseFusion baseFusionMethod = new CombMINBaseFusion();
                    runList.add(baseFusionMethod.fuse(runSet));
                    out("|  |--> Fuse():: CombMIN performed ");
                }
                if(Settings.FuseANZ) {
                    BaseFusion baseFusionMethod = new CombANZBaseFusion();
                    runList.add(baseFusionMethod.fuse(runSet));
                    out("|  |--> Fuse():: CombANZ performed ");
                }

               if(Settings.FuseCONDORCET){
                   CondorcetFusion condorcet = new CondorcetFusion();
                   runList.add(condorcet.fuse(runSet));
                   out("|  |--> Fuse():: CondorcetFusion performed ");
               }

                RunSet result = new RunSet(runList, runSet.Name());
                _ioManager.serialize(result);
                out("|  |--> Fuse():: fusion result serialized ");
            }
    }

    /**  Utility method for easy console printing
     *  @param  out the string we want to print on console.
     **/
    private void out(String out) {
        if(Settings.VERBOSE)
            System.out.println(out);
    }

}
