package Fusion;
import Base.Run;
import Base.RunLine;
import Base.RunLineScores;
import Base.RunSet;
import Fusion.CondorcetUtils.Doc;
import Fusion.CondorcetUtils.QueryCondorcet;
import IO.Settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Condorcet {

    public static final String fusionID = "CondFuse";

    public Run fuse(RunSet runSet) {

        Run run = new Run(Condorcet.fusionID);

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

            ArrayList<Doc> result = condorcet.getCondorcetResultDocArray();
            addResultToRun(run, result, query);
        }

        return run;
    }

    private void addResultToRun(Run run, ArrayList<Doc> result, String queryAndTopic) {
        for (Doc doc : result) {

            String globalID = doc.ID() + RunLine.sep + queryAndTopic;
            RunLineScores scores = new RunLineScores(Condorcet.fusionID, doc.Rank(), doc.Score());

            run.add(new RunLine(globalID, scores));
        }
    }
}