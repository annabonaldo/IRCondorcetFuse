package Fusion;

import Base.RunLineScores;

import java.util.List;

/**
 * This class implements <em><code>CombSUM</code> </em> base fusion method.
 * <p><code>
 * Combined Similarity = SUM(Individual Similarities) </code>
 * </p>
 */
public class CombSUMBaseFusion extends BaseFusion {

    /**
     * this is the FusionMethod name.
     * It is useful to distinguish between method used for Run generation.
     */
    static String fusionId = "CombSUM";

    @Override
    protected RunLineScores fuseLine(List<RunLineScores> runLineList) {

        int rank = -1;
        float sumNormScore = 0.0F;

        //takes the maximum Normalized score value
        for(RunLineScores score : runLineList) {
            sumNormScore += score.Score();
        }
        RunLineScores fusedScores = new RunLineScores(fusionId, rank, sumNormScore);
        fusedScores.NormalizeScore(sumNormScore);
        return fusedScores;
    }

    @Override
    protected String FusionName() {
        return CombSUMBaseFusion.fusionId;
    }


}
