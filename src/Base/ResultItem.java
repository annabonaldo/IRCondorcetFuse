package Base;

import Normalization.MinMax;

public class ResultItem implements Comparable<ResultItem>{

    String    _topicID;
    String    _queryID;
    String    _documentID;

    int       _rank;
    float     _score;
    float     _normalizedScore;
    String    _runID;

    public ResultItem(String line)
    {
        String[] line_items= line.split(" ");

        _topicID   = line_items[0];
        _queryID   = line_items[1];
        _documentID     = line_items[2];

        _rank = Integer.parseInt(line_items[3]);
        _score     = Float.parseFloat(line_items[4]);
        _runID = line_items[5];

    }

    String    TopicID()            { return _topicID; }
    String    DocumentID()         { return _documentID; }
    String    QueryID()            { return _queryID; }
    float     Score()              { return _score; }
    float     normalizedScore()    { return _normalizedScore; }
    int       Rank()               { return _rank; }
    String    RunID()              { return _runID; }

    public void Print()
    {
        System.out.print("| topic id: ");      System.out.print( _topicID);
        System.out.print("| query id : ");     System.out.print(_queryID);
        System.out.print("| iteration: ");     System.out.print(_rank);
        System.out.print("| topic name: ");    System.out.print(_documentID);
        System.out.print("| score: ");         System.out.print(_score);
        System.out.print("| norm score: ");    System.out.print(_normalizedScore);
        System.out.print("| method : ");       System.out.print(_runID);
        System.out.print("\n");
    }

    public String getLine()
    {
        return  _topicID.toString()+" "+
                      _queryID.toString()+" "+
                _documentID +" "+
                      Integer.toString(_rank)+ " "+
                      Float.toString(_score)+" "+
                _runID;
    }

    @Override
    public int compareTo(ResultItem resultItem) {
        return Float.compare(this._score, resultItem._score);
    }

    public void normalizeMinMax(MinMax minMax) {
        _normalizedScore = minMax.normalizeScore(_score);
    }
}
