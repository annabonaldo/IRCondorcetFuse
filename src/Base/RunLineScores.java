package Base;

// @desc
// this class rappresents only the "scoring part" of a Run Line
// the "scoring part" is considered as made of
// runID, rank, score

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

    public int Rank() {
        return _rank;
    }

    public float Score() {
        return _score;
    }

    public String RunID() {
        return _runID;
    }

    public void setRank(int rank) {  _rank = rank;   }

    public void NormalizeScore(float normalizedScore) {
        this._score = normalizedScore;
        _normalized = true;
    }

    public boolean isNormalized() { return _normalized; }


    public void PrintInline() {

        System.out.print("|| RUN "+this._runID+ "= "+ Float.toString(Score()));
    }
}
