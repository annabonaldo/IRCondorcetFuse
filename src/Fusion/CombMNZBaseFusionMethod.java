package Fusion;

import Base.RunLineScores;

import java.util.List;

public class CombMNZBaseFusionMethod extends BaseFusionMethod {

    static String fusionId = "CombMNZ";
    // Fuse() implements CombMNZ fusion:
    // Combined Similarity = SUM(Individual Similarities)*  Number of Nonzero Similarities

    @Override
    protected RunLineScores FuseLine(List<RunLineScores> runLineList) {


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

    @Override
    protected String FusionName() {
        return CombMNZBaseFusionMethod.fusionId;
    }
}
