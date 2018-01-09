package Fusion;

import Base.RunLine;
import Base.Run;
import Base.RunLineScores;

import java.util.List;

public class CombMNZFusionMethod extends FusionMethod {

    // Fuse() implements CombMNZ fusion:
    // Combined Similarity = SUM(Individual Similarities)*  Number of Nonzero Similarities

    @Override
    protected RunLineScores FuseLine(List<RunLineScores> runLineList) {

        String runID = "CombMNZ";
        int rank = -1;
        float sumNormScore = 0.0F;
        int nonZeroScores = 0;

        //compute the Sum Normalized score values
        for(RunLineScores score : runLineList) {
            sumNormScore += score.NormalizedScore();
            if(score.NormalizedScore() > 0)
                nonZeroScores++;
        }
        sumNormScore *= nonZeroScores;

        RunLineScores fusedScores = new RunLineScores(runID, rank, sumNormScore);
        fusedScores.setNormalizedScore(sumNormScore);

        return fusedScores;
    }
}
