package Fusion;

import Base.RunLineScores;

import java.util.List;

/**
 * Created by Anna Bonaldo on 25/01/2018.
 */
public class CombANZBaseFusion extends BaseFusion{
    /**
     * This is the FusionMethod name.
     * It is useful to distinguish between method used for Run generation.
     */
    static String fusionId = "CombANZ";


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