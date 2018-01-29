restoredefaultpath;
addpath(genpath('~/Desktop/matters'), '-end')

poolPath = '~/Desktop/ProgettoIR/IRCondorcetFuse/data/qrels.trec7.txt';

%IMPORT POOL
[pool, poolReport] = importPoolFromFileTRECFormat('FileName', poolPath, 'Identifier', ...
    'testPool', 'RelevanceGrades', 0:1, 'RelevanceDegrees',...
    {'NotRelevant', 'Relevant'}, 'Delimiter', 'space');


% run_noSM_noSW = importRunsFromDirectoryTRECFormat('Path', ...
%     '~/Desktop/ProgettoIR/IRCondorcetFuse/results/FusionOut/run_noSM_noSW_10/NORM_run_noSM_noSW_10/', ...
%     'Identifier', strcat('fusedRuns_', int2str(i)), 'Delimiter', 'space', ...
%     'DocumentOrdering', 'Matters'); 
% precision_noSM_noSW = averagePrecision(pool, run_noSM_noSW);
% MAP_noSM_noSW = mean(precision_noSM_noSW{:, 1:end});

% clearAllMemoizedCaches
% runBasic = importRunsFromDirectoryTRECFormat('Path', ...
%     '~/Desktop/ProgettoIR/IRCondorcetFuse/results/FusionOut/runBasic_10/NORM_runBasic_10/', ...
%     'Identifier', strcat('newRuns_', int2str(i)), 'Delimiter', 'space', ...
%     'DocumentOrdering', 'Matters'); 
% clearAllMemoizedCaches
% precisionBasic = averagePrecision(pool, runBasic, 'Verbose', true);
% clearAllMemoizedCaches
% MAPBasic = mean(precisionBasic{:, 1:end});


% runQE = importRunFromFileTRECFormat('FileName', ...
%     '~/Desktop/ProgettoIR/IRCondorcetFuse/results/FusionOut/runQE_10/NORM_runQE_10/DirichletLM_Bo1bfree_d_3_t_10_8.txt', ...
%     'Identifier', strcat('fusedRuns_', int2str(i)), 'Delimiter', 'space', ...
%     'DocumentOrdering', 'Matters');  
% precisionQE = averagePrecision(pool, runQE);
% MAPqe = mean(precisionQE{:, 1:end});

testNames =  {'run_noSM_noSW', 'run_noSM', 'run_noSW', 'runBasic'};

synTable = array2table(cell(1, length(testNames)));
synTable.Properties.VariableNames = testNames;

figure

for k = 1 : numel(testNames)
    
    runSet =  table();
      
   for i=2:2:10 %passo2
            
        fusionRun = struct();
        fusionPrecision = table();
        MAP = struct();
        
        myDir = strcat('~/Desktop/ProgettoIR/IRCondorcetFuse/results/FusionOut/', testNames{k}, '_', int2str(i));

        fusionRun = importRunsFromDirectoryTRECFormat('Path', myDir, ...
        'Identifier', strcat('newRun_', int2str(k), int2str(i)), 'Delimiter', 'space', ...
        'DocumentOrdering', 'Matters');  

        fusionPrecision = averagePrecision(pool, fusionRun);   

        MAP = mean(fusionPrecision{:, 1:end});
        MAP = MAP';
        MAP = array2table(MAP);

        MAP.Properties.VariableNames = {strcat('system_N', int2str(i))};
        MAP.Properties.RowNames = fusionRun.Properties.VariableNames;

        runSet = horzcat(runSet, MAP); 
        
        clearvars fusionRun fusionPrecision myDir       
        
        %runSet.Properties.VariableNames{k} = {strcat('system_N', int2str(i))};
        %MAP.Properties.RowNames = fusedRun.Properties.VariableNames;
        
   end
   
   subplot(2,2, k)
   plot(2:2:10, runSet{:, :}, 'Linewidth', 1);
   axis([2 10 0.1 0.2])
   title(strcat({'Fusions on '}, testNames{k}),'Interpreter', 'none');
   ylabel('MAP')
   xlabel('Number of randomly chosen input systems')
   legend(MAP.Properties.RowNames, 'Location', 'best');
   
   synTable{1, k} = {runSet};
      
   clearvars MAP runSet
    
end 

% filename = 'retrievalData.xlsx';
% 
% run_noSM_noSW = synTable.run_noSM_noSW{1, 1};
% writetable(run_noSM_noSW, filename, 'WriteRowNames', true, 'Sheet', 'run_noSM_noSW');
% 
% run_noSW = synTable.run_noSW{1, 1};
% writetable(run_noSW, filename, 'WriteRowNames', true, 'Sheet', 'run_noSW');
% 
% run_noSM = synTable.run_noSM{1, 1};
% writetable(run_noSM,filename, 'WriteRowNames', true, 'Sheet', 'run_noSM');
% 
% runBasic = synTable.runBasic{1, 1};
% writetable(runBasic,filename, 'WriteRowNames', true, 'Sheet', 'runBasic');
% 
