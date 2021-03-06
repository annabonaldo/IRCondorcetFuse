
# IRCondorcetFuse Repository 
* :file_folder: [javadoc](javadoc/)  
   Contains Code Documentation. 
* :file_folder: [eval](eval/) 
   Contains matlab scripts for fusion result evaluation. Evaluation scripts are based on [MATTERS MatLab Toolkit](http://matters.dei.unipd.it/) . 
* :open_file_folder: [results](results/)  
   - :open_file_folder: [FusionIn](results/FusionIn/)  
   Contains all the run set used as input for fusion.  
   A run set is a folder containing some *.res* files. One *file.res* is a run created with Terrier. 
   To add a new run set create a folder here and add all run files into it (see the following example of run set Input_RunSet_example).
        * :open_file_folder: *Input_RunSet_example*
          + :page_facing_up: Run1.res
          + :page_facing_up: ..  
          + :page_facing_up: Run_n.res   
          
    + :open_file_folder: [FusionOut](results/FusionOut/)  
    If you run the fusion program, the same structure of directory [FusionIn](results/FusionIn/) will be produced here. 
    The result of fusion performed in one runset into [FusionIn](results/FusionIn/), for example **Input_RunSet_example**
    will be placed into a **FusionOut/Input_RunSet_example** folder. 
    Each fusion method will create a run file named with the fusion method id.  
    :paperclip: Result are in *.txt* format for easier evaluation file processing. 
     * Input_RunSet_example
          + :file_folder: **NORM_Input_RunSet_example**   
          This folder contains the normalized input runs saved as *.txt* files. 
          
          
          + :page_facing_up: ***CombMAX.txt*** Fusion run made with CombMAX fusion method. 
          + :page_facing_up: ***CombSUM.txt*** Fusion run made with CombMAX  fusion method. 
          + :page_facing_up: ***CombMXN.txt*** Fusion run made with CombMAX  fusion method. 
          + :page_facing_up: ***CondFuse.txt*** Fusion run made with Condorcet fusion method. 
    
* :file_folder: [scripts](scripts/)  
   Contains script to get retrieval runs using Terrier. 
   Collection must be placed on folder data/Tipster to be found correctly by scripts. 
   
    + :repeat: [index_basic.sh](scripts/index_basic.sh)
    Indexing collection **with stemmer (PorterStemmer) and stoplist**. 
    + :repeat: [index_noSM.sh](scripts/index_noSM.sh)
    Indexing collection **without** stemmer and **with stoplist**. 
    + :repeat: [index_noSW.sh](scripts/index_noSW.sh)
    Indexing collection **with stemmer** (PorterStemmer) and **without** stoplist. 
    + :repeat: [index_noSMnoSW.sh](scripts/index_noSMnoSW.sh)
    Indexing collection **without** stemmer and **without** stoplist. 
    + :repeat: [retrieval_basic.sh](scripts/retrieval_basic.sh)
     Launch retrieval on indexing made with  **with stemmer (PorterStemmer) and stoplist**. 
    + :repeat: [retrieval_noSM.sh](scripts/retrieval_noSM.sh)
      Launch retrieval on indexing made with  **without** stemmer and **with stoplist**
    + :repeat: [retrieval_noSW.sh](scripts/retrieval_noSW.sh)
    Launch retrieval on indexing made with **with stemmer** (PorterStemmer) and **without** stoplist. 
    + :repeat: [retrieval_noSM_noSW.sh](scripts/retrieval_noSM_noSW.sh)
    Launch retrieval on indexing made with **without** stemmer and **without** stoplist.
   
* :file_folder: [src](src/)  
   Contains Java source code. 
* :file_folder: [terrier-core-4.2](terrier-core-4.2/)  
    Contains some terrier files. Not all files are included into the directory.   
    See **Configuring Retieval with Terrier** below. 


# Code Documentation 
  For the **Code Documentation** see the [javadoc](javadoc/) directory.  
  Here you get all the code documentation generated with JavaDoc. You can expolore it opening the [Doc Main Page](javadoc/index.html) file in your browser. 

# Configuring Retieval with Terrier 
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
