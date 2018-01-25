package Fusion;

import Base.RunLineScores;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Anna Bonaldo on 25/01/2018.
 */
public class CombMINBaseFusion extends BaseFusion{
    /**
     * This is the FusionMethod name.
     * It is useful to distinguish between method used for Run generation.
     */
    static String fusionId = "CombMIN";


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
