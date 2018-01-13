package Fusion.CondorcetUtils;

import Base.RunLineScores;

import java.util.*;

public class SingleRetirievalCondorcet {

    static  int totalScoreSizeErrors = 0;
    HashMap<String, Set<String>> _lowerDocs;
    String _queryAndTopic;

    ArrayList<Doc> _docArray = new ArrayList<Doc>();


    public SingleRetirievalCondorcet(String queryAndTopic,
                                     HashMap<String, ArrayList<RunLineScores> > list )
    {
        _queryAndTopic = queryAndTopic;
        _lowerDocs = new HashMap<>();

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
        System.out.println("TOTAL SIZE ERROR "+totalScoreSizeErrors+"on "+(list.size()-1+ "pairs !!"));
        FinalCondorcetResultOrdering();
    }

    public void DocumentPairCondorcet(String doc1name, String doc2name, ArrayList<RunLineScores> scores1, ArrayList<RunLineScores> scores2)
    {
        int eval =0;
        int min = (scores1.size() < scores2.size() ? scores1.size() : scores2.size());

        if(scores1.size()!= scores2.size())
            totalScoreSizeErrors++;

        for(int i = 0; i < min; i++)
            // @TODO IndexOutOfBound exception: scores1.size may be greater than scores2.size!
        { 
            if(scores1.get(i).Rank() < scores2.get(i).Rank()) eval ++;
            if(scores1.get(i).Rank() > scores2.get(i).Rank()) eval --;

            if(!this._lowerDocs.containsKey(doc1name))
                this._lowerDocs.put(doc1name, new HashSet<String>());
            if(!this._lowerDocs.containsKey(doc2name))
                this._lowerDocs.put(doc2name, new HashSet<String>());

            if(eval >= 0)
                this._lowerDocs.get(doc1name).add(doc2name);
            if(eval <= 0 )
                this._lowerDocs.get(doc2name).add(doc1name);

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

    void FinalCondorcetResultOrdering()
    {
        Collections.sort(_docArray);
        for(int i=0; i<_docArray.size(); i++)
        {
            _docArray.get(i).setRank(i);
            _docArray.get(i).setScore(_docArray.size()-i);
        }
    }

    public  HashMap<String, Set<String>> getLowerDocsMap(){return _lowerDocs; }


    public ArrayList<Doc> getCondorcetResultDocArray() {
        return _docArray;
    }
}

