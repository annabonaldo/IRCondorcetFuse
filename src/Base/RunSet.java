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
    List<Run> _runList;
    Set<String>  _docList;
    Set<String>  _queryAndTopicList;


    //@desc
    //this constructor bulid the obj
    // computing  the map form a run list
    public RunSet(List<Run> runList)
    {
        
        _runList = runList;

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

        // takes all unique 0 position item of key
        _docList = ComputeSubkeyList(new int[]{0});
        
        // takes all unique 1&2 position item of key
        _queryAndTopicList = ComputeSubkeyList(new int[]{1, 2});

    }

    public Set<String> getKeys()
    {
       return  _runset.keySet();
    }

    public Set<String> getDocList() { return  _docList; }

    public Set<String> getQueryAndTopicList() {return _queryAndTopicList; }
    
    public List<Run> getRunList() { return _runList; }

    protected HashSet<String> ComputeSubkeyList(int[] positions)
    {
        HashSet<String> list = new HashSet<String>();
        for(String key : _runset.keySet()) {
            String[] key_items = key.split(RunLine.sep);

            String subKey = new String();

           for(int i=0; i < positions.length; i++)
           {
               subKey = subKey + key_items[positions[i]];
               if(i < (positions.length-1))
                   subKey= subKey+RunLine.sep;
           }

            list.add(subKey);
        }
        return list;
    }

    public ArrayList<RunLineScores> getLineList(String key )
    {
        return  _runset.get(key);
    }

    public HashMap<String, ArrayList<RunLineScores>> getDocsScoreListForQueryAndTopic(String queryAndTopic)
    {
        Set<String> docList = this.getDocList();

        HashMap<String, ArrayList<RunLineScores>> out = new HashMap<>();
        for(String doc: docList)
        {
            String key = doc+RunLine.sep+queryAndTopic;
            if(_runset.containsKey(key)) {
                out.put(key, this._runset.get(key));
            }
        }

        return out;
    }


    public void NormalizeSetMinMax() {
        for(Run run : _runList) { run.normalizeRunMinMax(); }
    }

}


