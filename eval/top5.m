addpath(genpath('~/Desktop/matters'), '-end')

load('~/Desktop/ProgettoIR/IRCondorcetFuse/eval/basicFusionData_10.mat');
load('~/Desktop/ProgettoIR/IRCondorcetFuse/eval/basicRunData_10.mat');

%Consider all data
meanAPAll = [meanRunAP meanFusionAP];
allRuns = [measuredRunSet measuredFusionSet];

%Select top 5 MAP
bestNames = meanAPAll{1, :}';
bestNames=array2table(bestNames);
bestNames.Properties.RowNames = allRuns.Properties.VariableNames;
bestNames.Properties.VariableNames = {'MAP'};

bestNames = topkrows(bestNames, 5);

topBest = allRuns{:, intersect(allRuns.Properties.VariableNames,bestNames.Properties.RowNames)};

topBest = array2table(topBest);
topBest.Properties.RowNames=allRuns.Properties.RowNames;
topBest.Properties.VariableNames=intersect(allRuns.Properties.VariableNames,bestNames.Properties.RowNames);

%plot 5 best models
plot(351:400, topBest{1:50, 1:end}, 'Linewidth', 1);
title('Precision for the best retrieval systems over different topics');
ylabel('precision')
xlabel('topics')
legend(topBest.Properties.VariableNames);

% filename = 'firstData.xlsx';
% writetable(meanAPAll, filename, 'WriteRowNames', true, 'Sheet', 2);

clear;
