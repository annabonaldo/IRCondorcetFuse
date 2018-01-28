package Base.Normalization;

public class MinMax extends Normalizer {

    private final float _minScore;
    private final float _maxScore;

    public MinMax(float minScore, float maxScore) {
        _minScore = minScore;
        _maxScore = maxScore;
    }

    public final float minScore() { return _minScore; }
    public final float maxScore() { return _maxScore; }

    public float normalizeScore(float currScore) {
        return ((currScore - _minScore ) / (_maxScore - _minScore));
    }
}

