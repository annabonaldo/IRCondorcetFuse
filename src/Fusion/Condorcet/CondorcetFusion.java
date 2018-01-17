package Fusion.Condorcet;
import Base.Run;
import Base.RunLine;
import Base.RunLineScores;
import Base.RunSet;
import IO.Settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CondorcetFusion {

    public static final String fusionID = "CondFuse";

    public Run fuse(RunSet runSet) {

        Run run = new Run(CondorcetFusion.fusionID);

        Set<String> queryKeys = runSet.QueryKeys();


        for (String query : queryKeys) {

            HashMap<String, ArrayList<RunLineScores>> filteredList =
                    runSet.filterOnQuery(query);

            if(Settings.RUN_FUSION_DETAIL)
                System.out.println("QUERY id "+query+ " -- doc n ="+filteredList.size());

            for(String doc : filteredList.keySet())
            {
                ArrayList<RunLineScores> scores = filteredList.get(doc);
                if(Settings.RUN_FUSION_DETAIL)
                    System.out.println("----------DOCUMENT id "+doc+ " run num ="+scores.size());

            }

            QueryCondorcet condorcet =
                    new QueryCondorcet(query, filteredList);

            ArrayList<DocCondorcet> result = condorcet.getCondorcetResultDocArray();
            addResultToRun(run, result, query);
        }

        return run;
    }

    /**
    Prova prova prova prova
     */
    private void addResultToRun(Run run, ArrayList<DocCondorcet> result, String queryAndTopic) {
        for (DocCondorcet doc : result) {

            String globalID = doc.ID() + RunLine.sep + queryAndTopic;
            RunLineScores scores = new RunLineScores(CondorcetFusion.fusionID, doc.Rank(), doc.Score());

            run.add(new RunLine(globalID, scores));
        }
    }
}