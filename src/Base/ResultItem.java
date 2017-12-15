package Base;

public class ResultItem {

    String    _topicID;
    String    _topicName;
    String    _queryID;
    Float     _score;
    Integer   _iteration;
    String    _method;

    public ResultItem(String line)
    {
        String[] line_items= line.split(" ");
        _queryID   = line_items[0];
        _topicID   = line_items[1];
        _topicName = line_items[2];

        _score     = Float.parseFloat(line_items[3]);
        _iteration = Integer.parseInt(line_items[4]);
        _method    = line_items[5];

    }


    String    TopicID()     { return _topicID;   }
    String    TopicName()   { return _topicName; }
    String    QueryId()     { return _queryID;   }
    Float     Score()       { return _score;     }
    Integer   Iteration()   { return _iteration; }
    String    Method()      {return  _method;    }

}
