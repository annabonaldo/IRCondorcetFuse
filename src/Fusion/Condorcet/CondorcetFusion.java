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

    /** Fusion method identifier.
     * It is useful to specify method we used to
     *  crete a fused run once we have serialized it to file */
    public static final String fusionID = "CondFuse";

    /** Start fusion on input parameter.
     * @param runSet set of <code>Run</code>s we want to fuse.
     * @return The <code>Run</code> object created as fusion result.
     */
    public Run fuse(RunSet runSet) {

        // New void Condorcet Run
        Run run = new Run(CondorcetFusion.fusionID);

        // Get all queries into runSet
        Set<String> queryKeys = runSet.QueryKeys();

        // Compute fusion for each query
        for (String query : queryKeys) {

            // get the RunLines for current query
            HashMap<String, ArrayList<RunLineScores>> filteredList =
                    runSet.filterOnQuery(query);

            if(Settings.RUN_FUSION_DETAIL)
                System.out.println("QUERY id "+query+ " -- doc n ="+filteredList.size());


            QueryCondorcet condorcet =
                    new QueryCondorcet(query, filteredList);

            ArrayList<DocCondorcet> result = condorcet.getCondorcetResultDocArray();
            addResultToRun(run, result, query);
        }

        return run;
    }

    /**
     * Utility method to add a new <code>RunLine</code> to output  <code>Run</code>.
     * @param run fused run. We want to add some <code>RunLine</code> to it,
     *            once we have computed them.
     * @param result it is the data structure we that contains information
     *               computed by condorcet fusion for each document for a given query-topic.
     *               In this method we translate this information to a <code>RunLine</code> object
     *               and then we add it to the final <code>Run</code> object.
     * @param queryAndTopic it is the query-topic pair wa want to work on. In this method we work on
     *                      adding result for all documents of this query-topic pair.
     */
    private void addResultToRun(Run run, ArrayList<DocCondorcet> result, String queryAndTopic) {
        for (DocCondorcet doc : result) {

            String globalID = doc.ID() + RunLine.sep + queryAndTopic;
            RunLineScores scores = new RunLineScores(CondorcetFusion.fusionID, doc.Rank(), doc.Score());

            run.add(new RunLine(globalID, scores));
        }
    }
}