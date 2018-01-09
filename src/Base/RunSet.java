package Base;

import jdk.internal.util.xml.impl.Pair;

import java.util.*;

public class RunSet {

    /*@desc
      This map contains
      KEY =  line id : documentID+queryID+topicId
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

    public ArrayList<RunLineScores> getLineListAt(String key )
    {
        return  _runset.get(key);
    }
}

