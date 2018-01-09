package Base;

import java.util.ArrayList;

import Normalization.*;

// @desc Run file rappresentation
// each ArrayList entry is a  file line
public class Run extends ArrayList<RunLine> {

    private MinMax minMax;

    public void normalizeSetMinMax() {

        float max = Float.NEGATIVE_INFINITY;
        float min = Float.POSITIVE_INFINITY;
        
        for (RunLine item : this) {
            float currScore = item.Score();
            if (currScore < min) min = currScore;
            if (currScore > max) max = currScore;
        }
        
        minMax = new MinMax(min, max);

        for (RunLine item : this) {
            item.normalizeMinMax(minMax);
        }

    }

    public MinMax MinMax() { return minMax; }


}

