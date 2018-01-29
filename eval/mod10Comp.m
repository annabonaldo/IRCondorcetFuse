addpath(genpath('~/Desktop/matters'), '-end')

poolPath = '~/Desktop/ProgettoIR/IRCondorcetFuse/data/qrels.trec7.txt';
RunsPath = '~/Desktop/ProgettoIR/IRCondorcetFuse/results/FusionOut/';
  
%IMPORT POOL
[pool, poolReport] = importPoolFromFileTRECFormat('FileName', poolPath, 'Identifier', ...
    'testPool', 'RelevanceGrades', 0:1, 'RelevanceDegrees',...
    {'NotRelevant', 'Relevant'}, 'Delimiter', 'space');


%IMPORT MODELS

folderNames = {'run_noSM_noSW_10/NORM_run_noSM_noSW_10', 'run_noSW_10/NORM_run_noSW_10',...
    'run_noSM_10/NORM_run_noSM_10', 'runBasic_10/NORM_runBasic_10', };

runsNames =  {'run_noSM_noSW', 'run_noSW', 'run_noSM', 'runBasic'};

runSet =  table(); 

for k=1 : numel(runsNames)    
       
    MAP = struct();
        
    myDir = strcat(RunsPath, folderNames{k});

    currentRun = importRunsFromDirectoryTRECFormat('Path', myDir, ...
        'Identifier', strcat('currRun_', int2str(k), int2str(i)), ...
        'Delimiter', 'space', 'DocumentOrdering', 'Matters');  

    currentPrecision = averagePrecision(pool, currentRun);   

    MAP = mean(currentPrecision{:, 1:end});
    MAP = MAP';
    MAP = array2table(MAP);

    MAP.Properties.VariableNames = {strcat('MAP_', runsNames{k})};

    runSet = horzcat(runSet, MAP); 
    
    if k==numel(runsNames) 
        runSet.Properties.RowNames = currentRun.Properties.VariableNames;
    end
        
    clearvars currentRun currentPrecision myDir MAP
end

% filename = 'retrievalData.xlsx';
% writetable(runSet, filename, 'WriteRowNames', true, 'Sheet', 'modelsComp');

%clear;