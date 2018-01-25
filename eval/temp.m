restoredefaultpath;
addpath(genpath('~/Desktop/matters'), '-end')

poolPath = '~/Desktop/ProgettoIR/IRCondorcetFuse/data/qrels.trec7.txt';

%IMPORT POOL
[pool, poolReport] = importPoolFromFileTRECFormat('FileName', poolPath, 'Identifier', ...
    'testPool', 'RelevanceGrades', 0:1, 'RelevanceDegrees',...
    {'NotRelevant', 'Relevant'}, 'Delimiter', 'space');

runBasic = importRunFromFileTRECFormat('FileName', ...
    '~/Desktop/ProgettoIR/IRCondorcetFuse/results/FusionOut/runBasic_2/CondFuse.txt', ...
    'Identifier', strcat('fusedRuns_', int2str(i)), 'Delimiter', 'space', ...
    'DocumentOrdering', 'Matters'); 
precisionBasic = averagePrecision(pool, runBasic);
MAPbasic = mean(precisionBasic{:, 1:end});

runQE = importRunFromFileTRECFormat('FileName', ...
    '~/Desktop/ProgettoIR/IRCondorcetFuse/results/FusionOut/runQE_2/CombMNZ.txt', ...
    'Identifier', strcat('fusedRuns_', int2str(i)), 'Delimiter', 'space', ...
    'DocumentOrdering', 'Matters');  
precisionQE = averagePrecision(pool, runQE);
MAPqe = mean(precisionQE{:, 1:end});
    

testNames = {'runBasic_k100', 'runQE', 'runQE_k100', 'runQE_k100'};

figure

for k = 1 : numel(testNames)
    
    runSet =  table;
      
   for i=2:2:10 %passo2
            
        fusionRun = struct;
        fusionPrecision = table;
        MAP = struct;
        
        myDir = strcat('~/Desktop/ProgettoIR/IRCondorcetFuse/results/FusionOut/', testNames{k}, '_', int2str(i));

        fusionRun = importRunsFromDirectoryTRECFormat('Path', myDir, ...
        'Identifier', strcat('fusedRuns_', int2str(i)), 'Delimiter', 'space', ...
        'DocumentOrdering', 'Matters');  

        fusionPrecision = averagePrecision(pool, fusionRun);   

        MAP = mean(fusionPrecision{:, 1:end});
        MAP = MAP';
        MAP = array2table(MAP);

        MAP.Properties.VariableNames = {strcat('system_N', int2str(i))};
        MAP.Properties.RowNames = fusionRun.Properties.VariableNames;

        runSet = horzcat(runSet, MAP); 
        
        %runSet.Properties.VariableNames{k} = {strcat('system_N', int2str(i))};
        %MAP.Properties.RowNames = fusedRun.Properties.VariableNames;
        
   end
   
   subplot(2,2, k)
   plot(2:2:10, runSet{:, :});
   title(strcat({'Fusions on '}, testNames{k}));
   ylabel('MAP')
   xlabel('Number of randomly chosen input systems')
   legend(MAP.Properties.RowNames);
    
end


  