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

#RETRIEVAL CON 10 SISTEMI, SENZA QUERY EXPANSION, PARAMETRI DI DEFAULT

cd $TERRIER_BIN
./trec_terrier.sh -r -Dtrec.model=BB2 	 -Dtrec.topics=$TOPICS #1
./trec_terrier.sh -r -Dtrec.model=BM25 	 -Dtrec.topics=$TOPICS #2
./trec_terrier.sh -r -Dtrec.model=DLH13 	 -Dtrec.topics=$TOPICS #3 
./trec_terrier.sh -r -Dtrec.model=Hiemstra_LM  -Dtrec.topics=$TOPICS #4 
./trec_terrier.sh -r -Dtrec.model=IFB2   	 -Dtrec.topics=$TOPICS #5

./trec_terrier.sh -r -Dtrec.model=TF_IDF 	 -Dtrec.topics=$TOPICS #6
./trec_terrier.sh -r -Dtrec.model=DFIC         -Dtrec.topics=$TOPICS #7
./trec_terrier.sh -r -Dtrec.model=DFIZ 	 -Dtrec.topics=$TOPICS #8
./trec_terrier.sh -r -Dtrec.model=DirichletLM  -Dtrec.topics=$TOPICS #9
./trec_terrier.sh -r -Dtrec.model=InL2         -Dtrec.topics=$TOPICS #10

cd $TERRIER_RESULTS
cd ..
rm -r ../../results/FusionIn/runBasic
mkdir ../../results/FusionIn/runBasic
cp -r results/* ../../results/FusionIn/runBasic



cd $TERRIER_RESULTS
echo "Clean target directory..."
if [ $PWD = $RM_DIR ] ; then
echo "ok"
rm *
else
echo "Current directory is not Result directory" 
echo $PWD"---"$RM_DIR
fi

#RETRIEVAL CON 10 SISTEMI, CON QUERY EXPANSION, PARAMETRI DI DEFAULT

cd $TERRIER_BIN
./trec_terrier.sh -r -q -Dtrec.model=BB2 	 -Dtrec.topics=$TOPICS #1
./trec_terrier.sh -r -q -Dtrec.model=BM25 	 -Dtrec.topics=$TOPICS #2
./trec_terrier.sh -r -q -Dtrec.model=DLH13 	 -Dtrec.topics=$TOPICS #3 
./trec_terrier.sh -r -q -Dtrec.model=Hiemstra_LM  -Dtrec.topics=$TOPICS #4 
./trec_terrier.sh -r -q -Dtrec.model=IFB2   	 -Dtrec.topics=$TOPICS #5

./trec_terrier.sh -r -q -Dtrec.model=TF_IDF 	 -Dtrec.topics=$TOPICS #6
./trec_terrier.sh -r -q -Dtrec.model=DFIC         -Dtrec.topics=$TOPICS #7
./trec_terrier.sh -r -q -Dtrec.model=DFIZ 	 -Dtrec.topics=$TOPICS #8
./trec_terrier.sh -r -q -Dtrec.model=DirichletLM  -Dtrec.topics=$TOPICS #9
./trec_terrier.sh -r -q -Dtrec.model=InL2         -Dtrec.topics=$TOPICS #10

cd $TERRIER_RESULTS
cd ..
rm -r ../../results/FusionIn/runQE
mkdir ../../results/FusionIn/runQE
cp -r results/* ../../results/FusionIn/runQE 
