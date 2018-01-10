package Fusion;
import Base.Run;
import Base.RunLineScores;
import Base.RunSet;
import Fusion.CondorcetUtils.CondorcetGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

class Condorcet
{
    public Run Fuse(RunSet runSet) {
        Run run = new Run();
        Set<String> queryAndTopicList = runSet.getQueryAndTopicList();

        for(String queryAndTopic :queryAndTopicList )
        {
            HashMap<String, ArrayList<RunLineScores>> filteredList =
                    runSet.getDocsScoreListForQueryAndTopic(queryAndTopic);

            CondorcetGraph graph = new CondorcetGraph(queryAndTopic, filteredList);


        }


        return run;
    }


    // quickSort(documentIDsArray,0,documentIDs.size()-1);
 /*   private void quickSort(String[] documentIDs, int left, int right) {
        int index = partition(documentIDs, left, right);

        if (left < index - 1) quickSort(documentIDs, left, index - 1);
        if (index < right) quickSort(documentIDs, index, right);
    }

    private int partition(String[] documentIDs, int left, int right) {
        int i = left, j = right;
        String tmp;
        String pivot = documentIDs[(left + right) / 2];

        while (i <= j) {
            while (comparison(documentIDs[i], pivot)) i++;
            while (comparison(documentIDs[j], pivot)) j--;

            if (i <= j) {

                tmp = documentIDs[i];
                documentIDs[i] = documentIDs[j];
                documentIDs[j] = tmp;
                i++;
                j--;

            }
        }
        return i;
    }
*/
}
