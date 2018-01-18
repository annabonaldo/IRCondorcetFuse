package Fusion.Condorcet;

public class DocCondorcet implements Comparable<DocCondorcet>
{
    int _rank;
    int _score;
    String _docId;
    QueryCondorcet _parentCondorcet;


    DocCondorcet(String docId, int rank, QueryCondorcet parent)
    {
        _docId = docId;
        _rank = rank;
        _score = -1;
        _parentCondorcet = parent;
    }

    public void setRank(int rank)
    {
        _rank = rank;
    }

    public void setScore(int score)
    {
        _score = score;
    }

    public String ID() { return  _docId; }

    public int Rank() { return _rank;}

    public float Score() {return _score; }

    public  boolean isBiggerThan(DocCondorcet other)
    {
        return _parentCondorcet.getLowerDocsMap().get(this._docId).contains(other._docId);

    }

    public  boolean isLowerThan(DocCondorcet other)
    {
        return _parentCondorcet.getLowerDocsMap().get(other._docId).contains(this._docId);
    }

    @Override
    public int compareTo(DocCondorcet docCondorcet) {
        if(this == docCondorcet || this._docId == docCondorcet._docId) return 0;
        int EVAL = 0;
        boolean thisIsLower = _parentCondorcet.getLowerDocsMap().
                                    get(docCondorcet._docId).contains(this._docId);

        boolean thisIsGreater = _parentCondorcet.getLowerDocsMap().
                                    get(this._docId).contains(docCondorcet._docId);

        if(thisIsLower)
        {
            for(String lower :_parentCondorcet.getLowerDocsMap().get(docCondorcet._docId) )
            {
                if(!_parentCondorcet.getLowerDocsMap().get(this._docId).contains(lower))
                    EVAL++;
            }
        }

        if(thisIsGreater) {
            for (String el : _parentCondorcet.getLowerDocsMap().get(this._docId)) {
                if (!_parentCondorcet.getLowerDocsMap().get(docCondorcet._docId).contains(el))
                    EVAL--;
            }
        }

        // System.out.println(this._docId+ " comparedTo "+ docCondorcet._docId+ " distance = "+resEVAL);
        return  EVAL;

    }

}
