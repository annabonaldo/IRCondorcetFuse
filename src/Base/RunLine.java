package Base;

import Base.Normalization.*;

/** rappresentation of a single line of a Run file
 * each line conains:topicID queryID documentID rank score runID
 * this class contains also NORMALIZED RANK after normalization computation
 **/
public class RunLine implements Comparable<RunLine> {

    public static final String sep = " ";
    /** Contains the topic id string (we get it from run file)
     */
    String    _topicID;
    /**Contains the query id string
     */
    String    _queryID;
    /**Is the document id string (we get it from run file)
     */
    String    _documentID;
    /** Is an RunLineScore object that contains all scores and rank information for current document (_documentID) and current topic
     * (_topicID - and _queryID, but this last is always the same!)
     * @see RunLineScores
     */
    RunLineScores _scores;

    /** Class constructor. To build a new RunLine you must give
     * information about query, topic and dcoument (<code>RunLine.GlobalID()</code>   and about
     * score and rank they get for the current retrieval (scores).
     * @param globalID The global id is the string that includes all RunLine ids. It is build as:
     *                 <code>globalID = "documentID"+ sep + "queryID" + sep + "topicID" </code>
     *                 sep is a defined separator
     *                 @see RunLine#sep
     * @param scores The score object contains all information about current document scores
     *               and rank for curret query and topic.
     *               @see RunLineScores
     *               @see RunLine#GlobalID()
     * */
    public RunLine (String globalID, RunLineScores scores) {
        _scores = scores;
        String[] fields = globalID.split(sep);
        _documentID = fields[0];
        _queryID = fields[1];
        _topicID = fields[2];
    }

    /**
     * Class constructor.
     * You can build a RunLine using this constructor for each line of a Terrier run file.
     *
     * @param line It is the string containing all RunLine info.
     *             The correct format of line is:
     * <code> "topicID" + sep + "queryID" + sep +"documentID"+ sep + "score" + sep + "rank"+ "RunID "</code>
     */
    public RunLine(String line) {
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
    int       Rank()               { return _scores.Rank(); }
    String    RunID()              { return _scores.RunID(); }

    /**GlobalID is the id we use to merge results of different runs
    * @return return string made of:
     * <code>globalID = "documentID"+ sep + "queryID" + sep + "topicID" </code>
     *
     */
    String    GlobalID()                 { return DocumentID()+sep+QueryID()+sep+ TopicID(); }

    /**
     * This method is usefull for debug purposes.
     * It print on command line all information in RunLine
     * (so you can check the object actual status)
     */
    public void printRunInfo()
    {
        System.out.print("| topic id: ");      System.out.print(_topicID);
        System.out.print("| query id : ");     System.out.print(_queryID);
        System.out.print("| iteration: ");     System.out.print(_scores.Rank());
        System.out.print("| topic name: ");    System.out.print(_documentID);
        System.out.print("| score: ");         System.out.print(_scores.Score());
        System.out.print("| method : ");       System.out.print(_scores.RunID());
        System.out.print("\n");
    }

    /**
     * You can use it to print a RunLine to file in Terrier format.
     * @return The object rappresentation in Terrier run line format. This one is:
     * <code> "topicID" + sep + "queryID" + sep +"documentID"+ sep + "score" + sep + "rank"+ "RunID "</code>
     * It is the same format we use on <code>RunLine(String)</code> constructor to build the object.
     * @see RunLine#RunLine(String)
     */
    public String getRunAsLine() {
        return  _topicID.toString() + " " +
                _queryID.toString() + " " +
                _documentID + " " +
                Integer.toString(_scores.Rank()) + " " +
                Float.toString(_scores.Score()) + " " +
                _scores.RunID();
    }

    /**
     * @return The inner object
     * @see RunLine#_scores
     * @see RunLineScores
     */
    public RunLineScores getRunLineScores() {
        return _scores;
    }

    /**
     * Perform normalization on this RunLine scores, calling normalization on inner
     * @param normalizer object that manage normalization data and execution.
     * @see RunLineScores
     * @see RunLine#_scores .
     * @see Run#_minMax
     * @see RunLineScores#NormalizeScore(float)
     * @see Normalizer
     */
    public void normalize(Normalizer normalizer) {
        _scores.NormalizeScore(normalizer.normalizeScore(_scores.Score()));
    }

    /** Allow ordering on
     * <code>Run</code> object. object.
     * We order them into a <code>Run</code> object.
     * We order them on decreasing score and ascending rank documents of the same
     * group of query-topic.
     * @see Run object.

     **/
    @Override
    public int compareTo(RunLine runLine) {
        String value0 = this._topicID.concat(Float.toString(Score()));
        String value1 = runLine._topicID.concat(Float.toString(runLine.Score()));
        return value0.compareTo(value1);
    }

    /**
     * Setter for RunLine rank value. This is the position of current document in retrieval
     * run for current query-topic.
     * @param rank new rank value.
     * @see RunLine#_scores
     * @see RunLineScores#_rank
     * @see RunLineScores#setRank(int)
     */
    public void setRank(int rank) {
        this.getRunLineScores().setRank(rank);
    }
}
