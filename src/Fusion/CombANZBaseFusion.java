package Fusion;

import Base.RunLineScores;

import java.util.List;

/**
 * This class implements <em><code>CombANZ</code> </em> base fusion method.
 * <p><code>Combined Similarity = SUM(Individual Similarities) / num of NonZero Similarities </code></p>
 */
public class CombANZBaseFusion extends BaseFusion{
    /**
     * This is the FusionMethod name.
     * It is useful to distinguish between method used for Run generation.
     */
    static String fusionId = "CombANZ";


    /**
     * This method apply the CombMAX fusion:
     * <p><code>Combined Similarity = SUM(Individual Similarities) / num of NonZero Similarities </code></p>
     *
     * @param runLineList is the list of scores for the set of RunLine
     *                   we want to fuse together in a unique RunLineScore
     * @return the fusion result that is a unique RunLineScore, computed combining the input ones.
     */
    @Override
    protected RunLineScores fuseLine(List<RunLineScores> runLineList) {
        int rank = -1;
        float anzNormScore = 0.0F;
        int nonZeroScores = 0;

        //compute the Sum Normalized score values
        for(RunLineScores score : runLineList) {
            anzNormScore += score.Score();
            if(score.Score() > 0)
                nonZeroScores++;
        }
        if(nonZeroScores == 0 ) nonZeroScores =1;
        anzNormScore = anzNormScore / nonZeroScores;

        RunLineScores fusedScores = new RunLineScores(fusionId, rank, anzNormScore);
        fusedScores.NormalizeScore(anzNormScore);

        return fusedScores;
    }

    /** This method is usefull to distinguish between method used for Run generation. It
     * @return "CombANZ", that is the class fusionId.
     */
    @Override
    protected String FusionName() {
        return fusionId;
    }
}
