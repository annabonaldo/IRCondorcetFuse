package Base;

import java.util.*;


//@desc
// this class A DICTIONARY computed on multiple Runs
// (the list of run we want to merge later)

// for each RunLine we remember only
// one Key (***) and the list of LineScores

public class RunSet {

    /*@desc
      This map contains
      KEY =  line id : documentID+queryID+topicId (***)
      VALUE = list of _scores for each RUN
     */
    HashMap<String, ArrayList<RunLineScores>> _runset;


    //@desc
    //this constructor bulid the obj
    // computing  the map form a run list
    public RunSet(List<Run> runList)
    {

        for(Run run : runList)
            for(RunLine line: run)
            {
                if(_runset.containsKey(line.GlobalID())) {
                    ArrayList<RunLineScores> lineList = _runset.get(line.GlobalID());
                    lineList.add(line.RunLineScores());
                }
                else {
                    ArrayList<RunLineScores> lineList = new ArrayList<>();
                    lineList.add(line.RunLineScores());
                    _runset.put(line.GlobalID(), lineList);
                }
            }

    }

    public Set<String> getKeys()
    {
       return  _runset.keySet();
    }
    
    public Set<String> getDocumentsId() {
        
        Set<String> documentIDs = new HashSet<String>();
        for(String key : _runset.keySet()) {
            String[] key_items = key.split(RunLine.sep);
            documentIDs.add(key_items[1]);
        }
        return documentIDs;
    }

    public ArrayList<RunLineScores> getLineListAt(String key )
    {
        return  _runset.get(key);
    }
    
}


