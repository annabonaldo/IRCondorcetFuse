package Fusion.CondorcetUtils;

import Base.RunLineScores;

import java.util.*;

public class CondorcetGraph{

    HashMap<String, Set<String>> _lowerDocs;
    String _queryAndTopic;

    Doc[] _rankedDocs;


    public CondorcetGraph(String queryAndTopic,
                          HashMap<String, ArrayList<RunLineScores> > list )
    {
        _queryAndTopic = queryAndTopic;
        _lowerDocs = new HashMap<>();
        _rankedDocs = new Doc[list.size()];

        String doc1 = list.keySet().iterator().next();

        int i = 0;
        _rankedDocs[0] = new Doc(doc1,i); i++;

        for(String doc2: list.keySet())
        {
            if(doc2!=doc1)
            {
                _rankedDocs[i] = new Doc(doc1, i); i++;
                FusePair(doc1, doc2, list.get(doc2), list.get(doc2));
            }
        }
    }

    public void FusePair(String doc1, String doc2, ArrayList<RunLineScores> scores1, ArrayList<RunLineScores> scores2)
    {
        int eval =0;
        for(int i=0; i< scores1.size(); i++)
        {
            if(scores1.get(i).Rank() < scores2.get(i).Rank()) eval ++;
            if(scores1.get(i).Rank() < scores2.get(i).Rank()) eval ++;

            if(this._lowerDocs.containsKey(doc1))
                this._lowerDocs.put(doc1, new HashSet<String>());
            if(this._lowerDocs.containsKey(doc2))
                this._lowerDocs.put(doc2, new HashSet<String>());

            if(eval >= 0)
                this._lowerDocs.get(doc1).add(doc2);
            if(eval <= 0 )
                this._lowerDocs.get(doc2).add(doc1);

        }

    }


    public  String QueryAndTopic(){return _queryAndTopic; }

    // @desc
    // return true if docA isBiggerThan docB
    boolean isABiggerThanB(String docA, String docB)
    {
        return  (_lowerDocs.get(docA).contains(docB)) &&
                (!_lowerDocs.get(docB).contains(docA));
    }

}

