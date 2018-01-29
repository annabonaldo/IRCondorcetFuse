#!/bin/sh
cd ..
BASE_DIR=$PWD
TERRIER_BIN=$BASE_DIR"/terrier-core-4.2/bin/"
DATA_COLLECTION=$BASE_DIR"/data/TIPSTER/"

cd $TERRIER_BIN
echo $TERRIER_BIN
echo "Setting-up the collection...."
./trec_setup.sh $DATA_COLLECTION
echo "Indexing...."
./trec_terrier.sh -i -Dtermpipelines=PorterStemmer




