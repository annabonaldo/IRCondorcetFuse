package Fusion;

import Base.RunLineScores;

import java.util.List;

public class CombSUMBaseFusionMethod extends BaseFusionMethod {

    // Fuse() implements CombSUM fusion:
    // Combined Similarity = SUM(Individual Similarities)

    static String fusionId = "CombSUM";

    @Override
    protected RunLineScores FuseLine(List<RunLineScores> runLineList) {

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
        return CombSUMBaseFusionMethod.fusionId;
    }


}
