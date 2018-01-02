package Base;

import java.io.*;
import java.util.ArrayList;
import Normalization.*;

public class ResultSet extends ArrayList<ResultItem> {

    private MinMax minMax;

    public void normalizeSetMinMax() {

        float max = Float.NEGATIVE_INFINITY;
        float min = Float.POSITIVE_INFINITY;
        
        for (ResultItem item : this) {
            float currScore = item.Score();
            if (currScore < min) min = currScore;
            if (currScore > max) max = currScore;
        }
        
        minMax = new MinMax(min, max);

        for (ResultItem item : this) {
            item.NormalizeMinMax(minMax);
        }

    }

    public MinMax MinMax() { return minMax; }
}
