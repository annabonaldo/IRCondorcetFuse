package Fusion.CondorcetUtils;

import Base.RunLineScores;

import java.util.*;

public class QueryCondorcet {

    static  int totalScoreSizeErrors = 0;
    HashMap<String, Set<String>> graph;
    String _query;

    ArrayList<Doc> _docArray = new ArrayList<Doc>();


    public QueryCondorcet(String query,
                          HashMap<String, ArrayList<RunLineScores> > list )
    {
        _query = query;
        graph = new HashMap<>();

        String doc1name = list.keySet().iterator().next();
        Doc doc1 = new Doc(doc1name,-1,this);
        _docArray.add(doc1);

        for(String doc2name: list.keySet())
        {
            if(doc2name!=doc1name)
            {
                Doc doc2 = new Doc(doc2name,-1, this);
                _docArray.add(doc2);
                DocumentPairCondorcet(doc1name, doc2name, list.get(doc1name), list.get(doc2name));
            }
        }

        FinalCondorcetResultOrdering();
    }

    public void DocumentPairCondorcet(String doc1name, String doc2name, List<RunLineScores> scores1, List<RunLineScores> scores2)
    {
        int eval =0;
        Map<String, float[]> pairScores = getMethodComparationMap(scores1, scores2);


        for(Map.Entry<String, float[]> value :pairScores.entrySet())

        { 
            if(value.getValue()[0] < value.getValue()[1]) eval ++;
            if(value.getValue()[1] < value.getValue()[0]) eval --;

            if(!this.graph.containsKey(doc1name))
                this.graph.put(doc1name, new HashSet<String>());
            if(!this.graph.containsKey(doc2name))
                this.graph.put(doc2name, new HashSet<String>());

            if(eval > 0)
                this.graph.get(doc1name).add(doc2name);
            if(eval < 0 )
                this.graph.get(doc2name).add(doc1name);

        }

    }


    public  String Query(){return _query; }


    void FinalCondorcetResultOrdering()
    {
        Collections.sort(_docArray);
        for(int i=0; i<_docArray.size(); i++)
        {
            _docArray.get(i).setRank(i);
            _docArray.get(i).setScore(_docArray.size()-i);
        }
    }

    public  HashMap<String, Set<String>> getLowerDocsMap(){return graph; }


    public ArrayList<Doc> getCondorcetResultDocArray() {
        return _docArray;
    }

    public Map<String, float[]> getMethodComparationMap( List<RunLineScores> scores1, List<RunLineScores> scores2)
    {

        final float NOT_SCORED = -1.0F;
        Map<String, float[]> compatationMap = new HashMap<>();

        for(RunLineScores score :scores1)
        {
            if(!compatationMap.containsKey(score.RunID()))
            {
                float[] score_pair = new float[2];
                score_pair[0] = score.Score();
                score_pair[1] = NOT_SCORED;
                compatationMap.put(score.RunID(), score_pair);
            }

            else {
                float[] score_pair = compatationMap.get(score.RunID());
                score_pair[0] = score.Score();
            }
        }

        for(RunLineScores score :scores2)
        {
            if(!compatationMap.containsKey(score.RunID()))
            {
                float[] score_pair = new float[2];
                score_pair[0] = NOT_SCORED;
                score_pair[1] = score.Score();
                compatationMap.put(score.RunID(), score_pair);
            }

            else {
                float[] score_pair = compatationMap.get(score.RunID());
                score_pair[1] = score.Score();
            }
        }
        return  compatationMap;
    }
}

