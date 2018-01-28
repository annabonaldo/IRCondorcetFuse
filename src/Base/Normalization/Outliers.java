package Base.Normalization;

public class Outliers extends Normalizer {

    private final float _minScore;
    private final float _maxScore;
    private final int _range;

    public Outliers(float minScore, float maxScore, int range) {
        _minScore = minScore;
        _maxScore = maxScore;
        _range = range;
    }

    public final float minScore() { return _minScore; }
    public final float maxScore() { return _maxScore; }
    public final int range() { return _range; }

    public float normalizeScore(float currScore) {
        float newScore = currScore;
        if (Math.abs(currScore - _minScore) < _range || Math.abs(currScore - _maxScore) < _range) {
            newScore = 0;
        }
        return newScore;
    }

}