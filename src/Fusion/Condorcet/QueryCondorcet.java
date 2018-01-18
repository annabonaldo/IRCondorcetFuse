package Fusion.Condorcet;

import Base.RunLineScores;

import java.util.*;

public class QueryCondorcet {
    HashMap<String, Set<String>> graph;
    String _query;

    ArrayList<DocCondorcet> _docCondorcetArray = new ArrayList<DocCondorcet>();


    public QueryCondorcet(String query,
                          HashMap<String, ArrayList<RunLineScores> > list )
    {
        // remember the correspondent Query
        _query = query;
        graph = new HashMap<>();


        // Check all the document pairs
        for(String doc0name: list.keySet()) {

            if(!this.graph.containsKey(doc0name)) {
                this.graph.put(doc0name, new HashSet<String>());
                DocCondorcet doc0 = new DocCondorcet(doc0name,-1,this);
                _docCondorcetArray.add(doc0);
            }

            for (String doc1name : list.keySet()) {

                if ((doc1name != doc0name) && (!this.graph.containsKey(doc1name))){
                        this.graph.put(doc1name, new HashSet<String>());
                        DocCondorcet doc1 = new DocCondorcet(doc1name,-1,this);
                        _docCondorcetArray.add(doc1);
                }
                    DocumentPairCondorcet(doc0name, doc1name, list.get(doc0name), list.get(doc1name));

            }
        }
        for(Map.Entry<String, Set<String>> value :graph.entrySet())
        {
            System.out.print("K "+value.getKey() +":: ");
            for(String lower : value.getValue())
            {
                System.out.print(lower+ " - ");
            }
            System.out.print("\n");
        }

        FinalCondorcetResultOrdering();
    }

    public void DocumentPairCondorcet(String doc0name, String doc1name, List<RunLineScores> scores0, List<RunLineScores> scores1)
    {
        // Set documents equal at the beginning
        int eval =0;

        // get the evaluation map
        // This map should contain
        //
        // -- KEY = MethodID_1 - VALUE =  [score_for_doc0, score_for_doc1]
        Map<String, float[]> pairScores = getMethodComparationMap(scores0, scores1);


        for(Map.Entry<String, float[]> value :pairScores.entrySet())

        {
            // KEY = MethodID_1 - VALUE =  (score_for_doc0, score_for_doc1)
            // Compute the Condorcet comparing score differences

            // if score_for_doc0 < score_for_doc1
            if (value.getValue()[0] <= value.getValue()[1]) eval++; // eval > 0 == doc1 is better

            // if score_for_doc1 < score_for_doc0
            if (value.getValue()[0] >= value.getValue()[1]) eval--; // eval < 0 == doc0 is better
        }
            if(!this.graph.containsKey(doc0name))
                this.graph.put(doc0name, new HashSet<String>());
            if(!this.graph.containsKey(doc1name))
                this.graph.put(doc1name, new HashSet<String>());

            if(eval >= 0) // doc0 is better than doc1
                this.graph.get(doc1name).add(doc0name);

            if(eval <= 0 )// doc1 is better than doc0
                this.graph.get(doc0name).add(doc1name);


    }


    public  String Query(){return _query; }


    void FinalCondorcetResultOrdering()
    {
        Collections.sort(_docCondorcetArray);

        for(int i = 0; i< _docCondorcetArray.size(); i++)
        {
            _docCondorcetArray.get(i).setRank(i);
            _docCondorcetArray.get(i).setScore(_docCondorcetArray.size()-i);
        }
    }


    public  HashMap<String, Set<String>> getLowerDocsMap(){return graph; }


    public ArrayList<DocCondorcet> getCondorcetResultDocArray() {
        return _docCondorcetArray;
    }

    public Map<String, float[]> getMethodComparationMap( List<RunLineScores> scores_doc0, List<RunLineScores> scores_doc1)
    {

        final float NOT_SCORED = -1.0F;
        Map<String, float[]> runMethodsMap = new HashMap<>();


        for(RunLineScores score :scores_doc0)
        {
            // Map does not contains this Method evaluation score
            if(!runMethodsMap.containsKey(score.RunID()))
            {
                // Creare the new entry
                float[] score_pair = new float[2];
                score_pair[0] = score.Score(); // set  the score for doc 0
                score_pair[1] = NOT_SCORED;    // set void score for doc 1
                runMethodsMap.put(score.RunID(), score_pair);
            }

            else {
                float[] score_pair = runMethodsMap.get(score.RunID());
                System.out.println(
                        "ERR ! "+score.RunID() +"new value 0 = "+ score.Score()+
                        " -- actual scores = ["+ score_pair[0]+", "+score_pair[1]+"]");
                // this should be dead code
                score_pair[0] = score.Score();
            }
        }

        for(RunLineScores score :scores_doc1)
        {
            // Map does not contains this Method evaluation score
            if(!runMethodsMap.containsKey(score.RunID()))
            {
                // Create the new entry
                float[] score_pair = new float[2];
                score_pair[0] = NOT_SCORED;    // set void score for doc 0
                score_pair[1] = score.Score(); // set  the score for doc 1
                runMethodsMap.put(score.RunID(), score_pair);
            }

            else {
                // Update the actual (void) score
                float[] score_pair = runMethodsMap.get(score.RunID());
                score_pair[1] = score.Score();
            }
        }
        return  runMethodsMap;
    }



}

