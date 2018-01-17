package Base;

import java.util.ArrayList;
import java.util.Collections;

import Normalization.*;

// @desc Run file rappresentation
// each ArrayList entry is a  file line
public class Run extends ArrayList<RunLine> {

    private MinMax _minMax;
    private String _name;

    public Run(String name) {
        super();
        _name = name;
        normalize();
    }

    public MinMax MinMax() { return _minMax; }

    public String Name() {return _name;}

    public void computeRanks() {
        Collections.sort(this, Collections.reverseOrder());
        for(int i=0; i<this.size(); i++)
        {
            this.get(i)._scores.setRank(i);
        }
    }

    public void printInfo()
    {
        System.out.println("Run name : "+ Name()+ " lines: "+this.size());
    }

    void normalize() {

        float max = Float.NEGATIVE_INFINITY;
        float min = Float.POSITIVE_INFINITY;

        for (RunLine item : this) {
            float currScore = item.Score();
            if (currScore < min) min = currScore;
            if (currScore > max) max = currScore;
        }

        _minMax = new MinMax(min, max);

        for (RunLine item : this) {
            item.normalize(_minMax);
        }

    }

}

