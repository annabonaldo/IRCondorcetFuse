package Fusion;

import Base.RunLineScores;

import java.util.List;
import java.util.zip.Inflater;
/**
 * This class implements <em><code>CombMIN</code> </em> base fusion method.
 * <p><code>
 * Combined Similarity = MIN(Individual Similarities)</code>
 * </p>
 */
public class CombMINBaseFusion extends BaseFusion{
    /**
     * This is the FusionMethod name.
     * It is useful to distinguish between method used for Run generation.
     */
    static String fusionId = "CombMIN";


    /**
     * This method apply the CombMIN fusion:
     * <p><code>Combined Similarity = MIN(Individual Similarities)</code></p>
     *
     * @param runLineList is the list of scores for the set of RunLine
     *                   we want to fuse together in a unique RunLineScore
     * @return the fusion result that is a unique RunLineScore, computed combining the input ones.
     */
    @Override
    protected RunLineScores fuseLine(List<RunLineScores> runLineList) {
        int rank = -1;

        float minNormScore = Float.MAX_VALUE;

        //compute Minimum Normalized score value
        for(RunLineScores score : runLineList) {
            if (score.Score() < minNormScore)
                minNormScore = score.Score();
        }

        RunLineScores fusedScores = new RunLineScores(fusionId, rank, minNormScore);
        fusedScores.NormalizeScore(minNormScore);

        return fusedScores;
    }

    /** This method is usefull to distinguish between method used for Run generation. It
     * @return "CombMIN", that is the class fusionId.
     */
    @Override
    protected String FusionName() {
        return fusionId;
    }
}
