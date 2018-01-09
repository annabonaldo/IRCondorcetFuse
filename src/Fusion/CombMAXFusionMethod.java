package Fusion;

import Base.RunLine;
import Base.Run;
import Base.RunLineScores;

import java.util.List;

public class CombMAXFusionMethod extends FusionMethod {

    @Override
    protected RunLineScores FuseLine(List<RunLineScores> runLineList) {

        String runID = "CombMAX";
        int rank = -1;
        float maxNormScore = 0.0F;

        //takes the maximum Normalized score value
        for(RunLineScores score : runLineList) {
            if (score.NormalizedScore() > maxNormScore)
                maxNormScore = score.NormalizedScore();
        }
        RunLineScores fusedScores = new RunLineScores(runID, rank, maxNormScore);
        fusedScores.setNormalizedScore(maxNormScore);
        return fusedScores;
    }
}
