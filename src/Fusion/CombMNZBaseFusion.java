package Fusion;

import Base.RunLineScores;

import java.util.List;

/**
 * This class implements <em><code>CombMAX</code> </em> base fusion method.
 * <p><code>
 * Combined Similarity = SUM(Individual Similarities)*  Number of Non-Zero Similarities </code>
 * </p>
 */
public class CombMNZBaseFusion extends BaseFusion {
    /**
     * This is the FusionMethod name.
     * It is useful to distinguish between method used for Run generation.
     */
    static String fusionId = "CombMNZ";


    /**
     * This method apply the CombMNZ fusion:
     * <p><code>Combined Similarity = SUM(Individual Similarities)*  Number of Non-Zero Similarities</code></p>
     *
     * To compute the fusion score it takes the sum of the scores it get as input and
     * multipy them for the number of non zero input scores.
     *
     * @param runLineList is the list of scores for the set of RunLine
     *                   we want to fuse together in a unique RunLineScore
     * @return the fusion result that is a unique RunLineScore, computed combining the input ones.
     */
    @Override
    protected RunLineScores fuseLine(List<RunLineScores> runLineList) {
        int rank = -1;
        float sumNormScore = 0.0F;
        int nonZeroScores = 0;

        //compute the Sum Normalized score values
        for(RunLineScores score : runLineList) {
            sumNormScore += score.Score();
            if(score.Score() > 0)
                nonZeroScores++;
        }
        sumNormScore *= nonZeroScores;

        RunLineScores fusedScores = new RunLineScores(fusionId, rank, sumNormScore);
        fusedScores.NormalizeScore(sumNormScore);

        return fusedScores;
    }

    /** This method is usefull to distinguish between method used for Run generation. It
     * @return "CombMNZ", that is the class fusionId.
     */
    @Override
    protected String FusionName() {
        return CombMNZBaseFusion.fusionId;
    }
}
