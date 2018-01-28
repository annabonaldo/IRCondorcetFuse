package Base;

import java.util.*;


/** This class contains a set of Runs.
*/
public class RunSet {

/**It contains a DICTIONARY computed on multiple Runs that contains
 * the list of run we want to merge later) for each RunLine we remember only
 * one Key
 * @see RunLine#GlobalID()
 * and the list of LineScores.
 * This map contains
 * <ul>
    <li><em><code> KEY</code>  </em>
        <code>=  line id : documentID +Runline.sep+ queryID +Runline.sep+ topicId</li></code>
    <li><em><code> VALUE</code>  </em>
    <code>= list of _scores for each Run</li></code>
    </ul>
 @see RunLine#sep
 @see Run
 */
    HashMap<String, ArrayList<RunLineScores>> _globalScoreMap = new HashMap<String, ArrayList<RunLineScores>>();

    /**List of runs
     */
    List<Run> _runList;

    /**
     * List of document that are mentioned into Runs in current <code>RunSet</code>.
     * This data structure is computed <em>only at object construction time </em>.
     * This ensures correct results and program efficiency.
     * @see RunSet##docKeys() it is a constant time call.
     * */
    Set<String> docKeys;
    /**
     * List of <code>query - topic </code> pairs that are mentioned into
     * Runs in current <code>RunSet</code>.
     * This data structure is computed <em>only at object construction time </em>.
     * This ensures correct results and program efficiency.
     * @see RunSet#QueryKeys() it is a constant time call.
     */
    Set<String> _queryKeys; // query - topics key

    String _name;

    /** Class constructor.
     * @param  runList list of  <code>Run</code> object.
     * @param  name <code>RunSet</code> identifier.
     */
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

        _runList.forEach(Run::normalize);

    }

    /**
     *
     * @return <code>RunSet</code> identifier.
     */
    public String Name() {
        return _name;
    }

    /**
     * @return Union of all <code>RunLine</code> global identifier for <code>Run </code> objects in
     * this.
     * @see RunLine#GlobalID() 
     */
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
