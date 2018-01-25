restoredefaultpath;
addpath(genpath('~/Desktop/matters'), '-end')
%addpath(genpath('/usr/local/MATLAB/R2017b/toolbox/matters'), '-end');


poolPath = '~/Desktop/ProgettoIR/IRCondorcetFuse/data/qrels.trec7.txt';
basicRunsPath = '~/Desktop/ProgettoIR/IRCondorcetFuse/results/FusionOut/runBasic_10/NORM_runBasic_10/';
basicFusionPath = '~/Desktop/ProgettoIR/IRCondorcetFuse/results/FusionOut/runBasic_10/';
%QERunsPath = '~/Desktop/ProgettoIR/IRCondorcetFuse/results/FusionOut/runQE/NORM_runQE_10';
    
%IMPORT POOL
[pool, poolReport] = importPoolFromFileTRECFormat('FileName', poolPath, 'Identifier', ...
    'testPool', 'RelevanceGrades', 0:1, 'RelevanceDegrees',...
    {'NotRelevant', 'Relevant'}, 'Delimiter', 'space');

%IMPORT 10 BASIC MODELS
[basicRunSet, basicRunReport] = importRunsFromDirectoryTRECFormat('Path', basicRunsPath, ...
    'Identifier', 'basicRuns', 'Delimiter', 'space');

save('~/Desktop/ProgettoIR/IRCondorcetFuse/eval/basicRunData_10.mat', 'pool', ...
    'poolReport', 'basicRunSet', 'basicRunReport');


%IMPORT QE RUNS
%[QERunSet, QERunReport] = importRunsFromDirectoryTRECFormat('Path', QERunsPath, ...
%    'Identifier', 'QERuns', 'Delimiter', 'space');

%save('~/Desktop/ProgettoIR/IRCondorcetFuse/eval/QERunData.mat', 'pool', 'poolReport', 'QERunSet', 'QERunReport');

%IMPORT BASIC FUSIONS ON 10 MODELS
[basicFusionSet, basicFusionReport] = importRunsFromDirectoryTRECFormat('Path', basicFusionPath, ...
    'Identifier', 'basicFusions', 'Delimiter', 'space');

save('~/Desktop/ProgettoIR/IRCondorcetFuse/eval/basicFusionData_10.mat', 'pool', ...
    'poolReport', 'basicFusionSet', 'basicFusionReport');

clear;
