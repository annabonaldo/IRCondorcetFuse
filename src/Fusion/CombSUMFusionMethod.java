package Fusion;

import Base.RunLine;
import Base.Run;
import Base.RunLineScores;

import java.util.List;

public class CombSUMFusionMethod extends FusionMethod {

    // Fuse() implements CombSUM fusion:
    // Combined Similarity = SUM(Individual Similarities)


    @Override
    protected RunLineScores FuseLine(List<RunLineScores> runLineList) {

        String runID = "CombSUM";
        int rank = -1;
        float sumNormScore = 0.0F;

        //takes the maximum Normalized score value
        for(RunLineScores score : runLineList) {
            sumNormScore += score.NormalizedScore();
        }
        RunLineScores fusedScores = new RunLineScores(runID, rank, sumNormScore);
        fusedScores.setNormalizedScore(sumNormScore);
        return fusedScores;
    }
}
