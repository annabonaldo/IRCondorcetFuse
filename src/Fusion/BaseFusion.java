package Fusion;

import Base.Run;
import Base.RunLine;
import Base.RunLineScores;
import Base.RunSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * This is an abstract class for Base fusion methods.
 * Base fusion methods are really simple fusion methods that can combine RunLine from different Run instances
 * with simple operation on RunLines scores or rank (sum, avg, ...).
 * More advanced fusion methods cannot extend this class.
 */
public abstract class BaseFusion {

    /** Perform the fusion of all Runs contained into a RunSet.
     *  @param runSet is the RunSet used to compute a new fusion. The input object will not be modified.
     *  @return The Run created combining all Runs into the input RunSet together.
     */
    public Run fuse(RunSet runSet) {
        Run run = new Run(FusionName());
        Set<String> keylist = runSet.Keys();

        for(String key: keylist)
        {
            ArrayList<RunLineScores> linescorelist = runSet.getLineList(key);

            // ABSTRACT CALL
            RunLineScores linescores = fuseLine(linescorelist);


            run.add(new RunLine(key, linescores));
        }
        run.computeRanks();
        return run;
    }

    /**
     * Abstract method each fusion method need to implement.
     * @param runLineList is the list of scores for the set of RunLine
     *                   we want to fuse together in a unique RunLineScore.
     * @return The fused score. The fusion result score is a unique RunLineScore, computed combining the input ones.
     */
    protected abstract RunLineScores fuseLine(List<RunLineScores> runLineList);


    /** This polymorphic method is useful to distinguish between method used for Run generation.
     * @return The name of the applied fusion method.
     */
    protected abstract String FusionName();


}
