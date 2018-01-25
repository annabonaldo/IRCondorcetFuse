package Base;

/** @ desc
 * this class represents only the "scoring part" of a Run Line the
 * "scoring part" is considered as made of
 * runID, rank, score
**/
public class RunLineScores {

    String    _runID;
    int       _rank;
    float     _score;
    boolean   _normalized;

    public RunLineScores(String runID, int rank, float score ) {
        _rank = rank;
        _score = score;
        _runID = runID;
        _normalized = false;
    }

    /**
     * @ return current line rank
     */
    public int Rank() {
        return _rank;
    }

    /**
     * @ return current line score
     */
    public float Score() {
        return _score;
    }

    /**
     * @ return current line parent  Run name (ID)
     */
    public String RunID() {
        return _runID;
    }

    /**
     * @param rank The updated Runk of the Runline..
     */
    public void setRank(int rank) {  _rank = rank;   }

    /**
     * Set the normalized score on Score fileld.
     * @param normalizedScore
     */
    public void NormalizeScore(float normalizedScore) {
        this._score = normalizedScore;
        _normalized = true;
    }

    /** @return return TRUE if the the NormalizeScore() method has been called on this object,
     * and normalized value of score has been set.
     */
    public boolean isNormalized() { return _normalized; }

    /**
     * Print info. Useful for debug purposes
     */
    public void PrintInline() {

        System.out.print("|| RUN "+this._runID+ "= "+ Float.toString(Score()));
    }
}
