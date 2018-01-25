package Fusion;

import Base.RunLineScores;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna Bonaldo on 25/01/2018.
 */
public class CombMEDBaseFusion extends BaseFusion{
    /**
     * This is the FusionMethod name.
     * It is useful to distinguish between method used for Run generation.
     */
    static String fusionId = "CombMED";
    @Override
    protected RunLineScores fuseLine(List<RunLineScores> runLineList) {
        int rank = -1;
        float medNormScore = 0.0F;

        ArrayList<Float> scores =  new ArrayList<>();
        for(RunLineScores score : runLineList) {
            scores.add(score.Score());
        }

        int arraySize = scores.size()-1;
        boolean oddSize  = ((arraySize % 2) == 1);

         if(oddSize)
        {
            if(arraySize == 1) medNormScore = scores.get(0);
            else
                medNormScore = scores.get((arraySize-1)/2);

        }
        else if (arraySize!= 0)
             medNormScore = (scores.get(arraySize/2)+scores.get((arraySize/2)+1))/2;

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
