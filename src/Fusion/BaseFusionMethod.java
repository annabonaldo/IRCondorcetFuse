package Fusion;

import Base.Run;
import Base.RunLine;
import Base.RunLineScores;
import Base.RunSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class BaseFusionMethod {

    public Run Fuse(RunSet runSet) {
        Run run = new Run();
        Set<String> keylist = runSet.getKeys();

        for(String key: keylist)
        {
            ArrayList<RunLineScores> linescorelist = runSet.getLineList(key);

            // ABSTRACT CALL
            RunLineScores linescores = FuseLine(linescorelist);


            run.add(new RunLine(key, linescores));
        }
        run.ComputeRanks();
        return run;
    }

    protected abstract RunLineScores FuseLine(List<RunLineScores> runLineList);


}
