package Base;

import Normalization.MinMax;

// @desc rappresentation of a single line of a Run file
// each line conains:
// topicID queryID documentID rank score runID
//
// this class contains also NORMALIZED RANK after normalization computation
public class RunLine implements Comparable<RunLine> {

    public static final String sep = " ";
    String    _topicID;
    String    _queryID;
    String    _documentID;

    RunLineScores _scores;

    public RunLine (String globalID, RunLineScores scores)
    {
        _scores = scores;
        String[] fields = globalID.split(sep);
       
        _documentID = fields[0];
        _queryID = fields[1];
        _topicID = fields[2];
    }

    public RunLine(String line)
    {
        String[] line_items= line.split(sep);

        _topicID   = line_items[0];
        _queryID   = line_items[1];
        _documentID     = line_items[2];

        int    rank = Integer.parseInt(line_items[3]);
        float  score     = Float.parseFloat(line_items[4]);
        String runID = line_items[5];

        _scores = new RunLineScores(runID, rank, score);

    }

    String    TopicID()            { return _topicID; }
    String    DocumentID()         { return _documentID; }
    String    QueryID()            { return _queryID; }
    float     Score()              { return _scores.Score(); }
   // float     NormalizedScore()    { return _scores.NormalizedScore(); }
    int       Rank()               { return _scores.Rank(); }
    String    RunID()              { return _scores.RunID(); }


   // @use    GlobalID is the id we use to merge results of different runs
    String    GlobalID()                 { return DocumentID()+sep+QueryID()+sep+TopicID(); }

    public void Print()
    {
        System.out.print("| topic id: ");      System.out.print(_topicID);
        System.out.print("| query id : ");     System.out.print(_queryID);
        System.out.print("| iteration: ");     System.out.print(_scores.Rank());
        System.out.print("| topic name: ");    System.out.print(_documentID);
        System.out.print("| score: ");         System.out.print(_scores.Score());
      //  System.out.print("| norm score: ");    System.out.print(_scores.NormalizedScore());
        System.out.print("| method : ");       System.out.print(_scores.RunID());
        System.out.print("\n");
    }

    public String getLine()
    {
        return  "topic id: " + _topicID.toString() + " " +
                "query id: " + _queryID.toString() + " " +
                "document id: " + _documentID + " " +
                "rank: " + Integer.toString(_scores.Rank()) + " " +
                "score: " + Float.toString(_scores.Score()) + " " +
              //  "normalized score: " + _scores.NormalizedScore() + " " +
                "score id: " + _scores.RunID();
    }

    public RunLineScores RunLineScores() {
        return _scores;
    }

    public void normalizeMinMax(MinMax minMax) {
        _scores.NormalizeScore(minMax.normalizeScore(_scores.Score()));
    }


    @Override
    public int compareTo(RunLine runLine) {
       float f1=  this.Score();
       float f2=  runLine.Score();
       return Float.compare(f1, f2);
    }
}
