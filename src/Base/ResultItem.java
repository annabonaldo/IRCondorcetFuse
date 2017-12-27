package Base;

import java.awt.*;

public class ResultItem implements Comparable<ResultItem>{

    String    _topicID;
    String    _topicName;
    String    _queryID;
    float     _score;
    int       _iteration;
    String    _method;

    public ResultItem(String line)
    {
        String[] line_items= line.split(" ");

        _topicID   = line_items[0];
        _queryID   = line_items[1];
        _topicName     = line_items[2];

        _iteration = Integer.parseInt(line_items[3]);
        _score     = Float.parseFloat(line_items[4]);
        _method     = line_items[5];

    }


    String    TopicID()     { return _topicID;   }
    String    TopicName()   { return _topicName; }
    String    QueryId()     { return _queryID;   }
    float     Score()       { return _score;     }
    int       Iteration()   { return _iteration; }
    String    Method()      {return  _method;    }

    public void Print()
    {
        System.out.print("| topic id: ");       System.out.print( _topicID);
        System.out.print("| query id : ");      System.out.print(_queryID);
        System.out.print("| iteration: ");      System.out.print(_iteration);
        System.out.print("| topic name: ");     System.out.print(_topicName);
        System.out.print("| score: ");          System.out.print(_score);
        System.out.print("| method : ");        System.out.print(_method);
        System.out.print("\n");
    }

    public String getLine()
    {
        return  _topicID.toString()+" "+
                      _queryID.toString()+" "+
                      _topicName+" "+
                      Integer.toString(_iteration)+ " "+
                      Float.toString(_score)+" "+
                      _method;
    }

    @Override
    public int compareTo(ResultItem resultItem) {
        return Float.compare(this._score, resultItem._score);
    }
}
