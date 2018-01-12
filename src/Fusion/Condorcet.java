package Fusion;
import Base.Run;
import Base.RunLine;
import Base.RunLineScores;
import Base.RunSet;
import Fusion.CondorcetUtils.Doc;
import Fusion.CondorcetUtils.SingleRetirievalCondorcet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Condorcet {

    public static final String fusionID = "CondFuse";

    public Run Fuse(RunSet runSet) {
        Run run = new Run(Condorcet.fusionID);
        Set<String> queryAndTopicList = runSet.getQueryAndTopicList();

        for (String queryAndTopic : queryAndTopicList) {
            HashMap<String, ArrayList<RunLineScores>> filteredList =
                    runSet.getDocsScoreListForQueryAndTopic(queryAndTopic);

            SingleRetirievalCondorcet condorcet =
                    new SingleRetirievalCondorcet(queryAndTopic, filteredList);

            ArrayList<Doc> result = condorcet.getCondorcetResultDocArray();
            addResultToRun(run, result, queryAndTopic);
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