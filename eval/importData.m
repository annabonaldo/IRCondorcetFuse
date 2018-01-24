restoredefaultpath;
addpath(genpath('~/Desktop/matters'), '-end')
%addpath(genpath('/usr/local/MATLAB/R2017b/toolbox/matters'), '-end');


poolPath = '~/Desktop/ProgettoIR/IRCondorcetFuse/data/qrels.trec7.txt';
basicRunsPath = '~/Desktop/ProgettoIR/IRCondorcetFuse/results/FusionOut/runBasic/NORM_runBasic';
basicFusionPath = '~/Desktop/ProgettoIR/IRCondorcetFuse/results/FusionOut/runBasic';
QERunsPath = '~/Desktop/ProgettoIR/IRCondorcetFuse/results/FusionOut/runQE/NORM_runQE';

%[pool, poolReport, runSet, runSetReport] = importAndSaveCollectionFromDirectoryTRECFormat...
    %('CollectionIdentifier', 'testColl', 'OutputPath', output, ...
    %'PoolFileName', poolName, 'RelevanceDegrees', {'NotRelevant', 'Relevant'}, ...
    %'RelevanceGrades', 0:1, 'PoolDelimiter', 'space', ...
    %'RunSetPath', {runsPath}, 'DocumentOrdering', 'TrecEvalLexDesc', ...
    %'RunSetDelimiter', 'space');
    
%IMPORT POOL
[pool, poolReport] = importPoolFromFileTRECFormat('FileName', poolPath, 'Identifier', ...
    'testPool', 'RelevanceGrades', 0:1, 'RelevanceDegrees',...
    {'NotRelevant', 'Relevant'}, 'Delimiter', 'space');

%IMPORT BASIC RUNS
[basicRunSet, basicRunReport] = importRunsFromDirectoryTRECFormat('Path', basicRunsPath, ...
    'Identifier', 'basicRuns', 'Delimiter', 'space', ...
    'DocumentOrdering', 'Matters');

save('~/Desktop/ProgettoIR/IRCondorcetFuse/eval/basicRunData.mat', 'pool', ...
    'poolReport', 'basicRunSet', 'basicRunReport');


%IMPORT QE RUNS
%[QERunSet, QERunReport] = importRunsFromDirectoryTRECFormat('Path', QERunsPath, ...
 %   'Identifier', 'QERuns', 'Delimiter', 'space');
    %'DocumentOrdering', 'Matters');

%save('~/Desktop/ProgettoIR/IRCondorcetFuse/eval/QERunData.mat', 'pool', 'poolReport', 'QERunSet', 'QERunReport');

%IMPORT BASIC FUSIONS
[basicFusionSet, basicFusionReport] = importRunsFromDirectoryTRECFormat('Path', basicFusionPath, ...
    'Identifier', 'basicFusions', 'Delimiter', 'space', ...
    'DocumentOrdering', 'Matters');

save('~/Desktop/ProgettoIR/IRCondorcetFuse/eval/basicFusionData.mat', 'pool', ...
    'poolReport', 'basicFusionSet', 'basicFusionReport');

clear;
