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
./trec_terrier.sh -r -Dtrec.model=InL2 -Dtrec.topics=$TOPICS -Dtrec.results.file="InL2.res" -Dtrec.runtag="InL2" #1
./trec_terrier.sh -r -Dtrec.model=DirichletLM -Dtrec.topics=$TOPICS -Dtrec.results.file="DirichletLM.res" -Dtrec.runtag="DirichletLM" #2
cd $TERRIER_RESULTS
cd ..
rm -r ../../results/FusionIn/runBasic_2
mkdir ../../results/FusionIn/runBasic_2
cp -r results/*.res ../../results/FusionIn/runBasic_2

cd $TERRIER_BIN
./trec_terrier.sh -r -Dtrec.model=IFB2 -Dtrec.topics=$TOPICS -Dtrec.results.file="IFB2.res" -Dtrec.runtag="IFB2" #3 
./trec_terrier.sh -r -Dtrec.model=BB2  -Dtrec.topics=$TOPICS -Dtrec.results.file="BB2.res" -Dtrec.runtag="BB2" #4
cd $TERRIER_RESULTS
cd ..
rm -r ../../results/FusionIn/runBasic_4
mkdir ../../results/FusionIn/runBasic_4
cp -r results/*.res ../../results/FusionIn/runBasic_4

cd $TERRIER_BIN 
./trec_terrier.sh -r -Dtrec.model=DirichletLM -Dtermpipelines=Stopwords -Dtrec.topics=$TOPICS -Dtrec.results.file="DirichletLM_noST.res" -Dtrec.runtag="DirichletLM_noST" #5
./trec_terrier.sh -r -Dtrec.model=IFB2 -Dtermpipelines=Stopwords -Dtrec.topics=$TOPICS -Dtrec.results.file="IFB2_noST.res" -Dtrec.runtag="IFB2_noST" #6
cd $TERRIER_RESULTS
cd ..
rm -r ../../results/FusionIn/runBasic_6
mkdir ../../results/FusionIn/runBasic_6
cp -r results/*.res ../../results/FusionIn/runBasic_6

cd $TERRIER_BIN
./trec_terrier.sh -r -Dtrec.model=BB2 -Dtermpipelines=Stopwords -Dtrec.topics=$TOPICS -Dtrec.results.file="BB2_noST.res" -Dtrec.runtag="BB2_noST" #7
./trec_terrier.sh -r -Dtrec.model=DirichletLM -Dtermpipelines=PorterStemmer -Dtrec.topics=$TOPICS -Dtrec.results.file="DirichletLM_noSL.res" -Dtrec.runtag="DirichletLM_noSL" #8
cd $TERRIER_RESULTS
cd ..
rm -r ../../results/FusionIn/runBasic_8
mkdir ../../results/FusionIn/runBasic_8
cp -r results/*.res ../../results/FusionIn/runBasic_8

cd $TERRIER_BIN
./trec_terrier.sh -r -Dtrec.model=IFB2 -Dtermpipelines=PorterStemmer -Dtrec.topics=$TOPICS -Dtrec.results.file="IFB2_noSL.res" -Dtrec.runtag="IFB2_noSL" #9
./trec_terrier.sh -r -Dtrec.model=BB2 -Dtermpipelines=PorterStemmer -Dtrec.topics=$TOPICS -Dtrec.results.file="BB2_noSL.res" -Dtrec.runtag="BB2_noSL" #10
cd $TERRIER_RESULTS
cd ..
rm -r ../../results/FusionIn/runBasic_10
mkdir ../../results/FusionIn/runBasic_10
cp -r results/*.res ../../results/FusionIn/runBasic_10


