package Fusion;

import Base.RunLineScores;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements <em><code>CombMED</code> </em> base fusion method.
 * <p><code>
 * Combined Similarity = MEDIAN(Individual Similarities)</code>
 * </p>
 */
public class CombMEDBaseFusion extends BaseFusion{
    /**
     * This is the FusionMethod name.
     * It is useful to distinguish between method used for Run generation.
     */
    static String fusionId = "CombMED";

    /**
     * Line fusion implementation for CombMED method
     * @param runLineList is the list of scores for the set of RunLine
     *                   we want to fuse together in a unique RunLineScore.
     * @return the computed <code> RunLineScore </code> that is the fusion result
     */
    @Override
    protected RunLineScores fuseLine(List<RunLineScores> runLineList) {
        int rank = -1;
        float medNormScore = 0.0F;

        ArrayList<Float> scores =  new ArrayList<>();
        for(RunLineScores score : runLineList) {
            scores.add(score.Score());
        }

        int arraySize = scores.size();
        boolean oddSize  = ((arraySize % 2) == 1);

         if(oddSize)
        {
            if(arraySize == 1) medNormScore = scores.get(0);
            else
                medNormScore = scores.get((arraySize-1)/2);

        }
        else {
             if (arraySize!= 0) {
                 int i = (arraySize-1)/2;
                 int j = i+1;
                 medNormScore = (scores.get(i) + scores.get(j)) / 2.0F;
             }
         }

        RunLineScores fusedScores = new RunLineScores(fusionId, rank, medNormScore);
        fusedScores.NormalizeScore(medNormScore);

        return fusedScores;
    }

    /** This method is usefull to distinguish between method used for Run generation. It
     * @return "CombMED", that is the class fusionId.
     */
    @Override
    protected String FusionName() {
        return fusionId;
    }}
