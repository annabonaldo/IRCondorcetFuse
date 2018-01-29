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

    /**
     * This method apply the CombSUM fusion:
     * <p><code>Combined Similarity = SUM(Individual Similarities)</code></p>
     *
     * @param runLineList is the list of scores for the set of RunLine
     *                   we want to fuse together in a unique RunLineScore
     * @return the fusion result that is a unique RunLineScore, computed combining the input ones.
     */
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
