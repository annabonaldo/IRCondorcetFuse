package Fusion;

import Base.RunLineScores;

import java.util.List;

/**
 * This class implements <em><code>CombMAX</code> </em> base fusion method.
 * <p><code>
 * Combined Similarity = MAX(Individual Similarities)</code>
 * </p>
 */
public class CombMAXBaseFusion extends BaseFusion {

    /**
     * this is the FusionMethod name.
     * It is useful to distinguish between method used for Run generation.
     */
    static String fusionId = "CombMAX";

    @Override
    protected RunLineScores fuseLine(List<RunLineScores> runLineList) {
        int rank = -1;
        float maxNormScore = 0.0F;

        //takes the maximum Normalized score value
        for(RunLineScores score : runLineList) {
            if (score.Score() > maxNormScore)
                maxNormScore = score.Score();
        }
        RunLineScores fusedScores = new RunLineScores(fusionId, rank, maxNormScore);
        fusedScores.NormalizeScore(maxNormScore);
        return fusedScores;
    }

    @Override
    protected String FusionName() {
        return CombMAXBaseFusion.fusionId;
    }
}
