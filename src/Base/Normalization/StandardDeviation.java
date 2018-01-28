package Base.Normalization;

public class StandardDeviation extends Normalizer {

    private final float _mean;
    private final float _standardDeviation;

    public StandardDeviation(float mean, float standardDeviation) {
        _mean = mean;
        _standardDeviation = standardDeviation;
    }

    public float normalizeScore(float currScore) {
        return ((currScore - _mean) / _standardDeviation);
    }
}