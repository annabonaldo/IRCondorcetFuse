package Normalization;

public class MinMax {

    private final float _minScore;
    private final float _maxScore;

    public MinMax(float minScore, float maxScore) {
        _minScore = minScore;
        _maxScore = maxScore;
    }

    public final float MinScore() { return _minScore; }
    public final float MaxScore() { return _maxScore; }
}

