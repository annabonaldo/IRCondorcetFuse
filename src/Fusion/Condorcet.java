package Fusion;

import Base.*;
import java.util.Set;

public class Condorcet
{
    
    private RunSet _runSet;
    
    public Condorcet(RunSet runSet) {
        _runSet = runSet;
    }
    
    RunSet RunSet() { return _runSet; }
    
    public Run Fuse() {
        Set<String> documentIDs = _runSet.getDocumentIDs();
        String[] documentIDsArray = documentIDs.toArray(new String[documentIDs.size()]);
        quickSort(documentIDsArray,0,documentIDs.size()-1);
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
      };
      return i;
    }
    
    private boolean comparison(String doc1, String doc2) {

    }
    
}
