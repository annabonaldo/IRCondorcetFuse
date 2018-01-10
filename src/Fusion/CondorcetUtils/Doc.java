package Fusion.CondorcetUtils;

public class Doc implements  Comparable<Doc>
{
    int _rank;
    String _docId;

    Doc(String docId, int rank)
    {
        _docId = docId;
        _rank = rank;
    }

    public void setRank(int rank)
    {
        _rank = rank;
    }

    @Override
    public int compareTo(Doc i) {
        return Integer.compare(this._rank, i._rank);
    }

}
