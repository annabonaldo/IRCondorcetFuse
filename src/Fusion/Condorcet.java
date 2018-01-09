package Fusion;

import Base.*;
import java.util.*;

public class Condorcet
{
    
    private RunSet _runSet;
    
    public Condorcet(RunSet runSet) {
        _runSet = runSet;
    }
    
    RunSet RunSet() { return _runSet; }
    
    public Run Fuse() {
        Set<String> documentIDs = _runSet.getDocList();
        String[] documentIDsArray = documentIDs.toArray(new String[documentIDs.size()]);
        quickSort(documentIDsArray,0,documentIDs.size()-1);


        //TODO Stub return value
        return new  Run();
    }
    
    private void quickSort(String[] documentIDs, int left, int right) {
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
    
    private boolean comparison(String doc1, String doc2) {
        /*int count = 0;
        Set<String> queryAndTopics = _runSet.getQueryAndTopicList();
        Iterator<String> iterator = queryAndTopics.iterator();
        while (iterator.hasNext()) {
            String queryAndTopic = iterator.next();
            String globalId1 = doc1 + queryAndTopic;
            String globalId2 = doc2 + queryAndTopic;
            ArrayList<RunLineScores> runLineScoresDoc1 = _runSet.getLineListAt(globalId1);
            ArrayList<RunLineScores> runLineScoresDoc2 = _runSet.getLineListAt(globalId2);
            for (RunLineScores runLineScore1 : runLineScoresDoc1) {
                for (RunLineScores runLineScore2 : runLineScoresDoc2) {
                    if (runLineScore1.Rank() > runLineScore2.Rank()) count ++;
                    else count--;
                }
            }
        }
        if (count > 0) return true;
        else return false; */
        return false;
    }
    
}
