package Base;

import Normalization.MinMax;


public class RunLine {

    String    _topicID;
    String    _queryID;
    String    _documentID;

    RunLineScores scores;

    public RunLine(String line)
    {
        String[] line_items= line.split(" ");

        _topicID   = line_items[0];
        _queryID   = line_items[1];
        _documentID     = line_items[2];

        int    rank = Integer.parseInt(line_items[3]);
        float  score     = Float.parseFloat(line_items[4]);
        String runID = line_items[5];

        scores = new RunLineScores(runID, rank, score);

    }

    String    TopicID()            { return _topicID; }
    String    DocumentID()         { return _documentID; }
    String    QueryID()            { return _queryID; }
    float     Score()              { return scores.Score(); }
    float     NormalizedScore()    { return scores.NormalizedScore(); }
    int       Rank()               { return scores.Rank(); }
    String    RunID()              { return scores.RunID(); }


   // @use    GlobalID is the id we use to merge results of different runs
    String    GlobalID()                 { return TopicID()+DocumentID()+QueryID(); }

    public void Print()
    {
        System.out.print("| topic id: ");      System.out.print( _topicID);
        System.out.print("| query id : ");     System.out.print(_queryID);
        System.out.print("| iteration: ");     System.out.print(scores.Rank());
        System.out.print("| topic name: ");    System.out.print(_documentID);
        System.out.print("| score: ");         System.out.print(scores.Score());
        System.out.print("| norm score: ");    System.out.print(scores.NormalizedScore());
        System.out.print("| method : ");       System.out.print(scores.RunID());
        System.out.print("\n");
    }

    public String getLine()
    {
        return  _topicID.toString()+" "+
                      _queryID.toString()+" "+
                _documentID +" "+
                      Integer.toString(scores.Rank())+ " "+
                      Float.toString(scores.Score())+" "+
                scores.RunID();
    }


    public void normalizeMinMax(MinMax minMax) {
        scores.setNormalizedScore(minMax.normalizeScore(scores.Score()));
    }


}
