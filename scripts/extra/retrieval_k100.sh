###############################################
####################### IMPORTANT! 
#REMEBER TO SET TERRIER FILE PROPERTIES! 
## ADD TO terrier.properties FILE the command:
## matching.retrieved_set_size=100
###############################################
###############################################

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

#RETRIEVAL CON 10 SISTEMI, SENZA QUERY EXPANSION, PARAMETRI DI DEFAULT, RUN DEPTH k=500

cd $TERRIER_BIN
./trec_terrier.sh -r -Dtrec.model=BB2 	 -Dtrec.topics=$TOPICS -Dmatching.retrieved_set_size=100 #1
./trec_terrier.sh -r -Dtrec.model=BM25 	 -Dtrec.topics=$TOPICS -Dmatching.retrieved_set_size=100 #2
cd $TERRIER_RESULTS
cd ..
rm -r ../../results/FusionIn/runBasic_k100_2
mkdir ../../results/FusionIn/runBasic_k100_2
cp -r results/*.res ../../results/FusionIn/runBasic_k100_2

cd $TERRIER_BIN
./trec_terrier.sh -r -Dtrec.model=DLH13 	 -Dtrec.topics=$TOPICS -Dmatching.retrieved_set_size=100 #3 
./trec_terrier.sh -r -Dtrec.model=Hiemstra_LM  -Dtrec.topics=$TOPICS -Dmatching.retrieved_set_size=100 #4
cd $TERRIER_RESULTS
cd ..
rm -r ../../results/FusionIn/runBasic_k100_4
mkdir ../../results/FusionIn/runBasic_k100_4
cp -r results/*.res ../../results/FusionIn/runBasic_k100_4

cd $TERRIER_BIN 
./trec_terrier.sh -r -Dtrec.model=IFB2   	 -Dtrec.topics=$TOPICS -Dmatching.retrieved_set_size=100 #5
./trec_terrier.sh -r -Dtrec.model=TF_IDF 	 -Dtrec.topics=$TOPICS -Dmatching.retrieved_set_size=100 #6
cd $TERRIER_RESULTS
cd ..
rm -r ../../results/FusionIn/runBasic_k100_6
mkdir ../../results/FusionIn/runBasic_k100_6
cp -r results/*.res ../../results/FusionIn/runBasic_k100_6

cd $TERRIER_BIN
./trec_terrier.sh -r -Dtrec.model=DFIC         -Dtrec.topics=$TOPICS -Dmatching.retrieved_set_size=100 #7
./trec_terrier.sh -r -Dtrec.model=DFIZ 	 -Dtrec.topics=$TOPICS -Dmatching.retrieved_set_size=100 #8
cd $TERRIER_RESULTS
cd ..
rm -r ../../results/FusionIn/runBasic_k100_8
mkdir ../../results/FusionIn/runBasic_k100_8
cp -r results/*.res ../../results/FusionIn/runBasic_k100_8

cd $TERRIER_BIN
./trec_terrier.sh -r -Dtrec.model=DirichletLM  -Dtrec.topics=$TOPICS -Dmatching.retrieved_set_size=100 #9
./trec_terrier.sh -r -Dtrec.model=InL2         -Dtrec.topics=$TOPICS -Dmatching.retrieved_set_size=100 #10
cd $TERRIER_RESULTS
cd ..
rm -r ../../results/FusionIn/runBasic_k100_10
mkdir ../../results/FusionIn/runBasic_k100_10
cp -r results/*.res ../../results/FusionIn/runBasic_k100_10


