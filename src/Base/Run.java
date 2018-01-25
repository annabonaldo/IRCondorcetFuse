package Base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Base.Normalization.*;

/**
This class is a retrieval run. Each Run class is made of a list of RunLine.
   @field  _minMax is the value-pair used in Run normalization process (see normalize() method)
   @filed  _name is the run name that is the name of the file used to build the Run object.
 */
public class Run extends ArrayList<RunLine> {

    /**
     is the value-pair used in Run normalization process @see Base.Run#method normalize()
        */
    private MinMax _minMax;

    /**
    is the run name and corresponds to the run-file name, extension included.
            */
    private String _name;

    /**
     * *Class contstructor.
     * @param name is the file name from where run data came from.
     */
    public Run(String name) {
        super();
        _name = name;
        normalize();
    }

    /**  @return the MinMax object used in Run instance normalization.
     */
    public MinMax MinMax() { return _minMax; }

    /**
     *
     * @return the Run name
     */
    public String Name() {return _name;}

    /**
     * This method re-computes the Run. It sort all the RunLines in the instance in a decreasing order
     * and then set RunLine ranks following items order (from the major score to the minor one).
     */
    public void computeRanks() {
        Collections.sort(this, Comparator.reverseOrder());
        if(this.size()> 0) {
            String curTopic = this.get(0)._topicID;
            int rank = 0;
            for(RunLine line: this)
            {
                if(line._topicID.equals(curTopic)) {
                    line.setRank(rank);
                    rank  = rank+1;
                }
                else {
                    curTopic = line._topicID;
                    rank = 0;
                    line.setRank(rank);
                }
            }
        }
    }

    /**
     * This method is useful for debug purposes. It prints to command line some Run
     * instance information.
     * The output string gives the name and the size (the number of RunLine elements) of the instance.
     */
    public void printInfo()
    {
        System.out.println("Run name : "+ Name()+ " lines: "+this.size());
    }

    /**
     * Perform Run normalization using minimum and maximum score values
     */
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

