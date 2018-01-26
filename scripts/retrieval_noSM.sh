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
./trec_terrier.sh -r -Dtrec.model=BB2 -Dtrec.topics=$TOPICS -Dtermpipelines=Stopword -Dtrec.results.file="BB2_noSM.res" -Dtrec.runtag="BB2_noSM" #1
./trec_terrier.sh -r -Dtrec.model=BM25 -Dtrec.topics=$TOPICS -Dtermpipelines=Stopword -Dtrec.results.file="BM25_noSM.res" -Dtrec.runtag="BM25_noSM" #2
cd $TERRIER_RESULTS
cd ..
rm -r ../../results/FusionIn/run_noSM_2
mkdir ../../results/FusionIn/run_noSM_2
cp -r results/*.res ../../results/FusionIn/run_noSM_2

cd $TERRIER_BIN
./trec_terrier.sh -r -Dtrec.model=IFB2 -Dtrec.topics=$TOPICS -Dtermpipelines=Stopword -Dtrec.results.file="DLH13_noSM.res" -Dtrec.runtag="DLH13_noSM" #3 
./trec_terrier.sh -r -Dtrec.model=Hiemstra_LM  -Dtrec.topics=$TOPICS -Dtermpipelines=Stopword -Dtrec.results.file="HiemstraLM_noSM.res" -Dtrec.runtag="HiemstraLM_noSM" #4
cd $TERRIER_RESULTS
cd ..
rm -r ../../results/FusionIn/run_noSM_4
mkdir ../../results/FusionIn/run_noSM_4
cp -r results/*.res ../../results/FusionIn/run_noSM_4

cd $TERRIER_BIN 
./trec_terrier.sh -r -Dtrec.model=IFB2 -Dtrec.topics=$TOPICS -Dtermpipelines=Stopwords -Dtrec.results.file="IFB2_noSM.res" -Dtrec.runtag="IFB2_noSM" #5
./trec_terrier.sh -r -Dtrec.model=TF_IDF -Dtrec.topics=$TOPICS -Dtermpipelines=Stopwords -Dtrec.results.file="TFIDF_noSM.res" -Dtrec.runtag="TFIDF_noSM" #6
cd $TERRIER_RESULTS
cd ..
rm -r ../../results/FusionIn/run_noSM_6
mkdir ../../results/FusionIn/run_noSM_6
cp -r results/*.res ../../results/FusionIn/run_noSM_6

cd $TERRIER_BIN
./trec_terrier.sh -r -Dtrec.model=DFIC -Dtrec.topics=$TOPICS -Dtermpipelines=Stopwords -Dtrec.results.file="DFIC_noSM.res" -Dtrec.runtag="DFIC_noSM" #7
./trec_terrier.sh -r -Dtrec.model=DFIZ -Dtrec.topics=$TOPICS -Dtermpipelines=Stopwords -Dtrec.results.file="DFIZ_noSM.res" -Dtrec.runtag="DFIZ_noSM" #8
cd $TERRIER_RESULTS
cd ..
rm -r ../../results/FusionIn/run_noSM_8
mkdir ../../results/FusionIn/run_noSM_8
cp -r results/*.res ../../results/FusionIn/run_noSM_8

cd $TERRIER_BIN
./trec_terrier.sh -r -Dtrec.model=DirichletLM -Dtrec.topics=$TOPICS -Dtermpipelines=Stopwords -Dtrec.results.file="DirichletLM_noSM.res" -Dtrec.runtag="DirichletLM_noSM" #9
./trec_terrier.sh -r -Dtrec.model=InL2 -Dtrec.topics=$TOPICS -Dtermpipelines=Stopwords -Dtrec.results.file="InL2_noSM.res" -Dtrec.runtag="InL2_noSM" #10
cd $TERRIER_RESULTS
cd ..
rm -r ../../results/FusionIn/run_noSM_10
mkdir ../../results/FusionIn/run_noSM_10
cp -r results/*.res ../../results/FusionIn/run_noSM_10


