
## IRCondorcetFuse Repository 
* [doc](doc/) Contains Code Documnetation. 
* [eval](eval/)
* [results](results/)
* [scripts](scripts/)
* [src](src/)
* [terrier-core-4.2](terrier-core-4.2/) Contains some terrier files. Not all files are included into the directory. See **Configuring Retieval with Terrier** below. 

# IRCondorcetFuse
## Code Documentation 
  For the **Code Documentation** see the [doc](doc/) directory.  
  Here you get all the code documentation generated with JavaDoc. You can expolore it opening the index.html file in your browser. 

## Configuring Retieval with Terrier 
The directory [terrier-core-4.2](terrier-core-4.2/) does not contains all terrier and collection files to allow the scripts into [scripts](scripts/) working correctly. 
you need to add: 
* the remaining Terrier files 
* the Collection
* the topics files

To perform the add opertion properly you have to:
* download Terrier at <http://terrier.org/download/agree.shtml?terrier-core-4.2-bin.tar.gz> and decompress it. 
* select all the directories into terrier-core-4.2 apart from the terrier-core-4.2/var folder (it is alteady included into the project). 
* add all the folder you have selected into IRCondorcetFuse/terrier-core-4.2. 
* add the "data" folder into the IRCondorcetFuse folder (data contains the TIPSTER Collection and the ground-truth and topics files)
