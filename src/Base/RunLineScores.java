package Base;

import Normalization.MinMax;

// @desc
// this class rappresents only the "scoring part" of a Run Line
// the "scoring part" is considered as made of
// runID, rank, score

// normalizedScore is added after run NORMALIZATION computaion
// !!  and is null before it


public class RunLineScores {

  //  float     _normalizedScore;
    String    _runID;
    int       _rank;
    float     _score;
    boolean   _normalized;

    public RunLineScores(String runID, int rank, float score )
    {
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

 /*   public float NormalizedScore() {
        return _score;
    }*/

    public void NormalizeScore(float normalizedScore) {
        this._score = normalizedScore;
    }

    public void setRank(int rank)
    {
        _rank = rank;
    }

}
