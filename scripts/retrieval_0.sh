#!/bin/sh
cd ..
BASE_DIR=$PWD
TERRIER_BIN=$BASE_DIR"/terrier-core-4.2/bin/"
DATA_COLLECTION=$BASE_DIR"/data/TIPSTER/"
TOPICS=$BASE_DIR"/data/topics.351-400_trec7.txt"
TERRIER_RESULTS=$BASE_DIR"/terrier-core-4.2/var/results/"

RM_DIR=$BASE_DIR"/terrier-core-4.2/var/results"

cd $TERRIER_RESULTS
echo "Clean target directory..."
if [ $PWD = $RM_DIR ] ; then
echo "ok"
rm *
else
echo "Current directory is not Result directory" 
echo $PWD"---"$RM_DIR
fi


echo "Retrieval...."
cd $TERRIER_BIN
./trec_terrier.sh -r -Dtrec.model=BM25      -c 0.4 -Dtrec.topics=$TOPICS #1
./trec_terrier.sh -r -Dtrec.model=PL2       -c 0.4 -Dtrec.topics=$TOPICS #2
./trec_terrier.sh -r -Dtrec.model=BB2              -Dtrec.topics=$TOPICS #3 
./trec_terrier.sh -r -Dtrec.model=InL2      -c 0.4 -Dtrec.topics=$TOPICS #4 
./trec_terrier.sh -r -Dtrec.model=DFR_BM25  -c 0.4 -Dtrec.topics=$TOPICS #5

./trec_terrier.sh -r -Dtrec.model=DLH       -c 0.4 -Dtrec.topics=$TOPICS #6
./trec_terrier.sh -r -Dtrec.model=DPH       -c 0.4 -Dtrec.topics=$TOPICS #7
./trec_terrier.sh -r -Dtrec.model=IFB2      -c 0.4 -Dtrec.topics=$TOPICS #8
./trec_terrier.sh -r -Dtrec.model=Tf               -Dtrec.topics=$TOPICS #9
./trec_terrier.sh -r -Dtrec.model=TF_IDF    -c 0.4 -Dtrec.topics=$TOPICS #10
