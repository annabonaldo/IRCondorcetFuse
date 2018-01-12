package Base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import Normalization.*;

// @desc Run file rappresentation
// each ArrayList entry is a  file line
public class Run extends ArrayList<RunLine> {

    private MinMax minMax;
    private String _name;

    public Run(String name)
    {
        super();
        _name = name;
        normalizeRunMinMax();
    }

    void normalizeRunMinMax() {

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

    public void computeRanks()
    {
        Collections.sort(this);
        for(int i=0; i<this.size(); i++)
        {
            this.get(i)._scores.setRank(i);
        }
    }

    public String getName() {return _name;}

    public void PrintInfo()
    {
        System.out.println("Run name : "+getName()+ " lines: "+this.size());
    }

}

