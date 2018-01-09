package Base;

public class RunLineScores {

    float     _normalizedScore;
    String    _runID;
    int       _rank;
    float     _score;

    RunLineScores(String runID, int rank, float score )
    {
        _rank = rank;
        _score = score;
        _runID = runID;
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

    public float NormalizedScore() {
        return _normalizedScore;
    }

    public void setNormalizedScore(float _normalizedScore) {
        this._normalizedScore = _normalizedScore;
    }

}
