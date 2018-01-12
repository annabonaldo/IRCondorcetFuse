package Fusion;

import Base.RunLineScores;

import java.util.List;

public class CombMAXBaseFusionMethod extends BaseFusionMethod {

    static String fusionId = "CombMAX";
    // Fuse() implements CombMAX fusion:
    // Combined Similarity = MAX(Individual Similarities)
    @Override
    protected RunLineScores FuseLine(List<RunLineScores> runLineList) {


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
        return CombMAXBaseFusionMethod.fusionId;
    }
}
