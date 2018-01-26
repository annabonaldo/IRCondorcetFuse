#!/bin/sh

#Script che crea una copia per ogni file .res in results salvandolo com .txt perche' possa essere dato a matters

cd ..
BASE_DIR=$PWD
TERRIER_RESULTS=$BASE_DIR"/terrier-core-4.2/var/results/"

cd $TERRIER_RESULTS

echo $PWD

for file in *.res; do
cp -f $file `echo $file | sed 's/\(.*\.\)res/\1txt/'` ; done
#mv $file `basename $file .run`.txt; done



