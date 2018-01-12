package Fusion;

import Base.Run;
import Base.RunLine;
import Base.RunLineScores;
import Base.RunSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class BaseFusionMethod {

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

    protected abstract RunLineScores fuseLine(List<RunLineScores> runLineList);

    protected abstract String FusionName();


}
