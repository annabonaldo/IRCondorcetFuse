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
    HashMap<String, ArrayList<RunLineScores>> _runScoreMap = new HashMap<String, ArrayList<RunLineScores>>();
    List<Run> _runList;
    Set<String>  _docList;
    Set<String>  _queryAndTopicList;

    String _name;

    //@desc
    //this constructor bulid the obj
    // computing  the map form a run list
    public RunSet(List<Run> runList, String name)
    {
        
        _runList = runList;

        for(Run run : runList)
            for(RunLine line: run)
            {
                if(_runScoreMap.containsKey(line.GlobalID())) {
                    ArrayList<RunLineScores> lineList = _runScoreMap.get(line.GlobalID());
                    lineList.add(line.getRunLineScores());
                }
                else {
                    ArrayList<RunLineScores> lineList = new ArrayList<>();
                    lineList.add(line.getRunLineScores());
                    _runScoreMap.put(line.GlobalID(), lineList);
                }
            }

        // takes all unique 0 position item of key
        _docList = computeSubkeyList(new int[]{0});
        
        // takes all unique 1&2 position item of key
        _queryAndTopicList = computeSubkeyList(new int[]{1, 2});
        _name = name;

    }

    public String      Name(){return _name;}

    public Set<String> Keys()
    {
       return  _runScoreMap.keySet();
    }

    public Set<String> DocList() { return  _docList; }

    public Set<String> QueryAndTopicList() {return _queryAndTopicList; }
    
    public List<Run>   RunList() { return _runList; }

    protected HashSet<String> computeSubkeyList(int[] positions)
    {
        HashSet<String> list = new HashSet<String>();
        for(String key : _runScoreMap.keySet()) {
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
        return  _runScoreMap.get(key);
    }

    public HashMap<String, ArrayList<RunLineScores>> getDocsScoreListForQueryAndTopic(String queryAndTopic)
    {
        Set<String> docList = this.DocList();

        HashMap<String, ArrayList<RunLineScores>> out = new HashMap<>();
        for(String doc: docList)
        {
            String key = doc+RunLine.sep+queryAndTopic;
            if(_runScoreMap.containsKey(key)) {
                out.put(key, this._runScoreMap.get(key));
            }
        }

        return out;
    }

    public void printInfo()
    {
        System.out.println("Run "+this.Name()+" N elem:"+this._runScoreMap.size());
        for(Run run:_runList)
        {
            run.printInfo();
        }
    }
}


