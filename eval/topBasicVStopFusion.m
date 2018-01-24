addpath(genpath('~/Desktop/matters'), '-end')

load('~/Desktop/ProgettoIR/IRCondorcetFuse/eval/basicFusionData.mat');
load('~/Desktop/ProgettoIR/IRCondorcetFuse/eval/basicRunData.mat');

%Select top 5 basic Runs
bestRunNames = meanRunAP{1, :}';
bestRunNames=array2table(bestRunNames);
bestRunNames.Properties.RowNames = basicRunSet.Properties.VariableNames;
bestRunNames.Properties.VariableNames = {'MAP'};

bestRunNames = topkrows(bestRunNames, 5);

bestRuns = measuredRunSet{:, intersect(measuredRunSet.Properties.VariableNames,bestRunNames.Properties.RowNames)};

bestRuns = array2table(bestRuns);
bestRuns.Properties.RowNames=measuredRunSet.Properties.RowNames;
bestRuns.Properties.VariableNames=intersect(measuredRunSet.Properties.VariableNames,bestRunNames.Properties.RowNames);

%Select top 2 fusion methods
bestFusionNames = meanFusionAP{1, :}';
bestFusionNames=array2table(bestFusionNames);
bestFusionNames.Properties.RowNames = basicFusionSet.Properties.VariableNames;
bestFusionNames.Properties.VariableNames = {'MAP'};

bestFusionNames = topkrows(bestFusionNames, 2);

bestFusions = measuredFusionSet{:, intersect(measuredFusionSet.Properties.VariableNames,bestFusionNames.Properties.RowNames)};

bestFusions = array2table(bestFusions);
bestFusions.Properties.RowNames=measuredFusionSet.Properties.RowNames;
bestFusions.Properties.VariableNames=intersect(measuredFusionSet.Properties.VariableNames,bestFusionNames.Properties.RowNames);

topBest = [bestRuns bestFusions];

%plot 5 best basic runs and 2 best fusions
plot(351:400, topBest{1:50, 1:end});
title('Precision for the best retrieval methods in different topics');
ylabel('precision')
xlabel('topics')
legend(topBest.Properties.VariableNames);

%clear;


