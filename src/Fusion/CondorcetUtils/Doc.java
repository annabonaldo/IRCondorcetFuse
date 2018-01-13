package Fusion.CondorcetUtils;

public class Doc implements  Comparable<Doc>
{
    int _rank;
    int _score;
    String _docId;
    QueryCondorcet _parentCondorcet;

    Doc(String docId, int rank, QueryCondorcet parent)
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

    @Override
    public int compareTo(Doc other) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        boolean GREATERorEQUAL = _parentCondorcet.getLowerDocsMap().
                                    get(other._docId).contains(this._docId);

        boolean SMALLERorEQUAL = _parentCondorcet.getLowerDocsMap().
                                    get(this._docId).contains(other._docId);

        if(GREATERorEQUAL    && SMALLERorEQUAL)    return EQUAL;
        if(GREATERorEQUAL    && (!SMALLERorEQUAL)) return AFTER;
      //  if((!GREATERorEQUAL) && SMALLERorEQUAL)
            return BEFORE;
    }

}
