package Base;

import java.util.*;


//* This class contains a set of Runs.
// * @param RunSet_globalScoreMap It contains a DICTIONARY computed on multiple Runs that contains
/// * the list of run we want to merge later) for each RunLine we remember only
// * one Key (***) and the list of LineScores

public class RunSet {
    /**
     *
     */

    /*@desc
      This map contains
      KEY =  line id : documentID+queryID+topicId (***)
      VALUE = list of _scores for each RUN
     */

    HashMap<String, ArrayList<RunLineScores>> _globalScoreMap = new HashMap<String, ArrayList<RunLineScores>>();
    List<Run> _runList;
    Set<String> docKeys;
    Set<String> _queryKeys; // query - topics key

    String _name;

    //@desc
    //this constructor bulid the obj
    // computing  the map form a run list
    public RunSet(List<Run> runList, String name) {
        _runList = runList;

        for (Run run : runList)

            for (RunLine line : run) {
                if (_globalScoreMap.containsKey(line.GlobalID())) {
                    ArrayList<RunLineScores> lineList = _globalScoreMap.get(line.GlobalID());
                    lineList.add(line.getRunLineScores());
                }
                else {
                    ArrayList<RunLineScores> lineList = new ArrayList<>();
                    lineList.add(line.getRunLineScores());
                    _globalScoreMap.put(line.GlobalID(), lineList);
                }
            }

        // takes all unique 0 position item of key
        docKeys = filterUniqueSubkeys(new int[]{0});

        // takes all unique 1&2 position item of key
        _queryKeys = filterUniqueSubkeys(new int[]{1,  2});
        _name = name;

    }

    public String Name() {
        return _name;
    }

    public Set<String> Keys() {
        return _globalScoreMap.keySet();
    }

    public Set<String> DocKeys() {
        return docKeys;
    }

    public Set<String> QueryKeys() {
        return _queryKeys;
    }

    public List<Run> RunList() {
        return _runList;
    }

    protected HashSet<String> filterUniqueSubkeys(int[] positions) {

        HashSet<String> list = new HashSet<String>();

        for (String key : _globalScoreMap.keySet()) {

            String[] key_items = key.split(RunLine.sep);
            String subKey = new String();

            for (int i = 0; i < positions.length; i++) {
                subKey = subKey + key_items[positions[i]];
                if (i < (positions.length - 1))
                    subKey = subKey + RunLine.sep;
            }

            if(!list.contains(subKey))
                list.add(subKey);
        }
        return list;
    }

    public ArrayList<RunLineScores> getLineList(String key) {
        return _globalScoreMap.get(key);
    }

    public HashMap<String, ArrayList<RunLineScores>> filterOnQuery(String query) {

        Set<String> docList = this.DocKeys();

        HashMap<String, ArrayList<RunLineScores>> out = new HashMap<>();

        for (String doc : docList)
        {
            String key = doc + RunLine.sep + query;

            if (_globalScoreMap.containsKey(key)) {
                out.put(doc, this._globalScoreMap.get(key));
            }
        }

        return out;
    }

    public void printInfo() {
           System.out.println("Run " + this.Name() + " N elem:" + this._globalScoreMap.size());
           for (Run run : _runList) {
               run.printInfo();
           }

    }


}
