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
./trec_terrier.sh -r -Dtrec.model=BB2 -Dtrec.topics=$TOPICS -Dtermpipelines=PorterStemmer -Dtrec.results.file="BB2_noSW.res" -Dtrec.runtag="BB2_noSW" #1
./trec_terrier.sh -r -Dtrec.model=BM25 -Dtrec.topics=$TOPICS -Dtermpipelines=PorterStemmer -Dtrec.results.file="BM25_noSW.res" -Dtrec.runtag="BM25_noSW" #2
cd $TERRIER_RESULTS
cd ..
rm -r ../../results/FusionIn/run_noSW_2
mkdir ../../results/FusionIn/run_noSW_2
cp -r results/*.res ../../results/FusionIn/run_noSW_2

cd $TERRIER_BIN
./trec_terrier.sh -r -Dtrec.model=IFB2 -Dtrec.topics=$TOPICS -Dtermpipelines=PorterStemmer -Dtrec.results.file="DLH13_noSW.res" -Dtrec.runtag="DLH13_noSW" #3 
./trec_terrier.sh -r -Dtrec.model=Hiemstra_LM  -Dtrec.topics=$TOPICS -Dtermpipelines=PorterStemmer -Dtrec.results.file="HiemstraLM_noSW.res" -Dtrec.runtag="HiemstraLM_noSW" #4
cd $TERRIER_RESULTS
cd ..
rm -r ../../results/FusionIn/run_noSW_4
mkdir ../../results/FusionIn/run_noSW_4
cp -r results/*.res ../../results/FusionIn/run_noSW_4

cd $TERRIER_BIN 
./trec_terrier.sh -r -Dtrec.model=IFB2 -Dtrec.topics=$TOPICS -Dtermpipelines=PorterStemmer -Dtrec.results.file="IFB2_noSW.res" -Dtrec.runtag="IFB2_noSW" #5
./trec_terrier.sh -r -Dtrec.model=TF_IDF -Dtrec.topics=$TOPICS -Dtermpipelines=PorterStemmer -Dtrec.results.file="TFIDF_noSW.res" -Dtrec.runtag="TFIDF_noSW" #6
cd $TERRIER_RESULTS
cd ..
rm -r ../../results/FusionIn/run_noSW_6
mkdir ../../results/FusionIn/run_noSW_6
cp -r results/*.res ../../results/FusionIn/run_noSW_6

cd $TERRIER_BIN
./trec_terrier.sh -r -Dtrec.model=DFIC -Dtrec.topics=$TOPICS -Dtermpipelines=PorterStemmer -Dtrec.results.file="DFIC_noSW.res" -Dtrec.runtag="DFIC_noSW" #7
./trec_terrier.sh -r -Dtrec.model=DFIZ -Dtrec.topics=$TOPICS -Dtermpipelines=PorterStemmer -Dtrec.results.file="DFIZ_noSW.res" -Dtrec.runtag="DFIZ_noSW" #8
cd $TERRIER_RESULTS
cd ..
rm -r ../../results/FusionIn/run_noSW_8
mkdir ../../results/FusionIn/run_noSW_8
cp -r results/*.res ../../results/FusionIn/run_noSW_8

cd $TERRIER_BIN
./trec_terrier.sh -r -Dtrec.model=DirichletLM -Dtrec.topics=$TOPICS -Dtermpipelines=PorterStemmer -Dtrec.results.file="DirichletLM_noSW.res" -Dtrec.runtag="DirichletLM_noSW" #9
./trec_terrier.sh -r -Dtrec.model=InL2 -Dtrec.topics=$TOPICS -Dtermpipelines=PorterStemmer -Dtrec.results.file="InL2_noSW.res" -Dtrec.runtag="InL2_noSW" #10
cd $TERRIER_RESULTS
cd ..
rm -r ../../results/FusionIn/run_noSW_10
mkdir ../../results/FusionIn/run_noSW_10
cp -r results/*.res ../../results/FusionIn/run_noSW_10


